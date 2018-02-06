package no.ssb.vtl.script.support;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.script.error.VTLScriptException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An error listener that converts syntax errors to {@link no.ssb.vtl.script.error.VTLScriptException}
 * and pass them to a consumer.
 */
public class SyntaxErrorListener extends BaseErrorListener {

    private final Consumer<VTLScriptException> errorConsumer;

    public SyntaxErrorListener(Consumer<VTLScriptException> errorConsumer) {
        this.errorConsumer = checkNotNull(errorConsumer);
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int startLine, int startColumn, String msg, RecognitionException e) {
        VTLScriptException vtlScriptException;
        // Use the context from the RecognitionException if available.
        if (e != null && e.getCtx() != null) {
            vtlScriptException = new VTLScriptException(msg, (ParserRuleContext) e.getCtx());
        } else {
            int stopColumn = startColumn;
            if (offendingSymbol instanceof Token) {
                Token symbol = (Token) offendingSymbol;
                int start = symbol.getStartIndex();
                int stop = symbol.getStopIndex();
                if (start >= 0 && stop >= 0) {
                    stopColumn = startColumn + (stop - start) + 1;
                }
                vtlScriptException = new VTLScriptException(msg, startLine, startColumn, startLine, stopColumn);
            } else {
                vtlScriptException = new VTLScriptException(msg, startLine, startColumn);
            }
        }
        errorConsumer.accept(vtlScriptException);
    }
}
