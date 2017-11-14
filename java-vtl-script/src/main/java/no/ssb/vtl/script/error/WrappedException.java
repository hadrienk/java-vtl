package no.ssb.vtl.script.error;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hadrien on 13/12/2016.
 */
@Deprecated
public class WrappedException extends RecognitionException implements PositionableError {

    private final Exception cause;
    private int line;
    private int column;

    public WrappedException(Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx, Exception cause) {
        super(recognizer, input, ctx);
        this.cause = checkNotNull(cause);
    }

    public WrappedException(String message, Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx, Exception cause) {
        super(message, recognizer, input, ctx);
        this.cause = checkNotNull(cause);
    }

    @Override
    public Exception getCause() {
        return cause;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int getLineNumber() {
        return this.line;
    }

    @Override
    public int getColumnNumber() {
        return this.column;
    }

    @Override
    public int getStartLine() {
        return 0;
    }

    @Override
    public int getStopLine() {
        return 0;
    }

    @Override
    public int getStartColumn() {
        return 0;
    }

    @Override
    public int getStopColumn() {
        return 0;
    }
}
