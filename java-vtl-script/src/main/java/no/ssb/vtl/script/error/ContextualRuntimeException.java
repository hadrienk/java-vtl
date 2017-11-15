package no.ssb.vtl.script.error;

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

import org.antlr.v4.runtime.ParserRuleContext;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * A {@link RuntimeException} that contains the context in which it happened.
 */
public class ContextualRuntimeException extends RuntimeException {

    private final ParserRuleContext context;

    public ContextualRuntimeException(ParserRuleContext context) {
        this.context = checkNotNull(context);
    }

    public ContextualRuntimeException(String message, ParserRuleContext context) {
        super(message);
        this.context = checkNotNull(context);
    }

    public ContextualRuntimeException(String message, Throwable cause, ParserRuleContext context) {
        super(message, cause);
        this.context = checkNotNull(context);
    }

    public ContextualRuntimeException(Throwable cause, ParserRuleContext context) {
        super(cause.getMessage(), cause);
        this.context = checkNotNull(context);
    }

    public ContextualRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ParserRuleContext context) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.context = checkNotNull(context);
    }

    public static void checkArgument(ParserRuleContext context, boolean expression) throws ContextualRuntimeException {
        if (!expression) {
            throw new ContextualRuntimeException(context);
        }
    }

    public static void checkArgument(ParserRuleContext context, boolean expression, String template, Object... arguments) throws ContextualRuntimeException {
        if (!expression) {
            throw new ContextualRuntimeException(format(template, arguments), context);
        }
    }

    public ParserRuleContext getContext() {
        return context;
    }
}
