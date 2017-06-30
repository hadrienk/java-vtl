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

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class VisitorUtilTest {

    @Test
    public void stripQuotes() throws Exception {
        CommonToken token = new CommonToken(Token.DEFAULT_CHANNEL, null);
        TerminalNodeImpl terminalNode = new TerminalNodeImpl(token);

        assertThat(VisitorUtil.stripQuotes(null)).isNull();

        assertThat(VisitorUtil.stripQuotes(terminalNode)).isNull();

        //VTL 1.1 Part 1, line 2907
        token.setText("");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("");

        token.setText("test");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("test");

        token.setText("\"test\"");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("test");

        token.setText("\"'test'\"");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("'test'");
    
        token.setText("\"a\"\"b\""); //escaped quote
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("a\"b");
    }

}
