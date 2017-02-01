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

public class FilterClauseTest {
    
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
    public void testJoinWithFilter() throws Exception {
        String expression = "filter true";
        
        String parseTree = parse(expression, "joinFilterExpression", grammar);
    
        assertThat(filterWhiteSpaces(parseTree)).isEqualTo(
                filterWhiteSpaces("(joinFilterExpression:1 filter (booleanExpression:3(booleanEquallity:1(datasetExpression:5(exprAtom:1(variableRef:2(varID:1true)))))))"));
    }
    
}
