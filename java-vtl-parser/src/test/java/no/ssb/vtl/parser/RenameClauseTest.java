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

public class RenameClauseTest {

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
    public void testRename() throws Exception {

        String expression = "" +
                "rename varID1 to varID2, varID3.varID4 to varID5";
        String parseTree = parse(expression, "joinRenameExpression", grammar);

        // TODO: Check this.
        // @formatter:off
        assertThat(filterWhiteSpaces(parseTree)).isEqualTo(filterWhiteSpaces("" +
                "(" +
                    "joinRenameExpression:1 rename (" +
                        "joinRenameParameter:1 (" +
                            "joinComponentReference:2 (" +
                                "varID:1 varID 1" +
                            ")" +
                        ") to (" +
                            "varID:1 varID 2" +
                        ")" +
                    ") , (" +
                        "joinRenameParameter:1 (" +
                            "joinComponentReference:1 (" +
                                "varID:1 varID 3" +
                            ") . (" +
                                "varID:1 varID 4" +
                            ")" +
                        ") to (" +
                            "varID:1 varID 5" +
                        ")" +
                    ")" +
                ")"));
        // @formatter:on

    }

}
