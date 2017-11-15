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

import com.google.common.collect.Lists;
import no.ssb.vtl.script.error.VTLScriptException;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SyntaxErrorListenerTest {

    @Test
    public void testSyntaxErrorWithoutContext() throws Exception {
        List<VTLScriptException> errors = Lists.newArrayList();
        SyntaxErrorListener listener = new SyntaxErrorListener(errors::add);
        listener.syntaxError(null, null, 123, 321, "vtl error", null);

        assertThat(errors).isNotEmpty();
        VTLScriptException exception = errors.get(0);
        assertThat(exception)
                .hasMessageContaining("vtl error");

        assertThat(exception.getStartLine()).isEqualTo(123);
        assertThat(exception.getLineNumber()).isEqualTo(123);

        assertThat(exception.getStartColumn()).isEqualTo(321);
        assertThat(exception.getColumnNumber()).isEqualTo(321);

    }

    @Test
    public void testSyntaxErrorWithContext() throws Exception {
        List<VTLScriptException> errors = Lists.newArrayList();
        SyntaxErrorListener listener = new SyntaxErrorListener(errors::add);

        ParserRuleContext ruleContext = new ParserRuleContext();
        ruleContext.start = new CommonToken(0, "start") {{
            setLine(789);
            setCharPositionInLine(987);
        }};
        ruleContext.stop = new CommonToken(1, "stop") {{
            setLine(456);
            setCharPositionInLine(654);
        }};

        RecognitionException recognitionError = new RecognitionException(
                "recognition error", null, null, ruleContext
        );
        listener.syntaxError(null, null, 123, 321, "vtl error", recognitionError);

        assertThat(errors).isNotEmpty();
        VTLScriptException exception = errors.get(0);
        assertThat(exception)
                .hasMessageContaining("vtl error");

        assertThat(exception.getStartLine()).isEqualTo(789);
        assertThat(exception.getLineNumber()).isEqualTo(789);

        assertThat(exception.getStartColumn()).isEqualTo(987);
        assertThat(exception.getColumnNumber()).isEqualTo(987);


        assertThat(exception.getStopLine()).isEqualTo(456);
        assertThat(exception.getStopColumn()).isEqualTo(654);



    }
}
