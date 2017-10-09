package no.ssb.vtl.script.visitors;

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

import org.antlr.v4.runtime.tree.TerminalNode;

import static com.google.common.base.Preconditions.checkNotNull;

public final class VisitorUtil {

    private static final String QUOTE_CHAR = "(?<!\")\"(?!\")";
    private static final String ESCAPED_QUOTE_CHAR = "\"\"";

    private VisitorUtil() {
    }

    public static String stripQuotes(String string) {
        checkNotNull(string);
        return string.replaceAll(QUOTE_CHAR, "").replaceAll(ESCAPED_QUOTE_CHAR, "\"");
    }

    public static String stripQuotes(TerminalNode stringConstant) {

        if (stringConstant != null && stringConstant.getText() != null) {
            return stripQuotes(stringConstant.getText());
        }
        return null;
    }

}
