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

public class DropClauseTest {

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
    public void testJoinDrop() throws Exception {
        String expression = "" +
                "drop varID1, varID2, varID3.varID4";
        String parseTree = parse(expression, "joinDropExpression", grammar);

        // TODO: Check this.
        assertThat(filterWhiteSpaces(parseTree)).isEqualTo(filterWhiteSpaces(
                "(joinDropExpression:1drop(componentRef:2(variableRef:1(identifier:2varID1))),(componentRef:2(variableRef:1(identifier:2varID2))),(componentRef:1(datasetRef:1(variableRef:1(identifier:2varID3))).(variableRef:1(identifier:2varID4))))"));
    }

}
