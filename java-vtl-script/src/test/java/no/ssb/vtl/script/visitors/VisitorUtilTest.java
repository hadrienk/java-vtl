package no.ssb.vtl.script.visitors;

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

        token.setText("test");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("test");

        token.setText("\"test\"");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("test");

        token.setText("\"'test'\"");
        assertThat(VisitorUtil.stripQuotes(terminalNode)).isEqualTo("'test'");
    }

}