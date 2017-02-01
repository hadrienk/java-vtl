package no.ssb.vtl.parser;

import com.google.common.io.Resources;
import org.antlr.v4.tool.Grammar;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.*;
import static com.google.common.io.Resources.*;
import static no.ssb.vtl.parser.ParserTestHelper.*;
import static org.assertj.core.api.Assertions.*;

public class KeepClauseTest {

    private static Grammar grammar;
    @ClassRule
    public static ExternalResource grammarResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            URL grammarURL = getResource(this.getClass(), "VTL.g4");
            String grammarString = Resources.toString(grammarURL, Charset.defaultCharset());
            grammar = new Grammar(checkNotNull(grammarString));
        }
    };

    @Test
    public void testJoinKeep() throws Exception {
        String expression = "" +
                "keep varID1, varID2, varID3.varID4";
        String parseTree = parse(expression, "joinKeepExpression", grammar);

        // TODO: Check this.
        assertThat(filterWhiteSpaces(parseTree)).isEqualTo(filterWhiteSpaces(
                "(joinKeepExpression:1 keep (joinKeepRef:2 (varID:1 varID 1)) , (joinKeepRef:2 (varID:1 varID 2)) , (joinKeepRef:1 (varID:1 varID 3) . (varID:1 varID 4)))"));
    }

}
