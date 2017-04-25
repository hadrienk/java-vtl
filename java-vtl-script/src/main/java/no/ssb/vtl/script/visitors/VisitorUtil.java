package no.ssb.vtl.script.visitors;

import org.antlr.v4.runtime.tree.TerminalNode;

public final class VisitorUtil {

    private static final String QUOTE_CHAR = "(?<!\")\"(?!\")";
    private static final String ESCAPED_QUOTE_CHAR = "\"\"";

    private VisitorUtil() {
    }

    public static String stripQuotes(TerminalNode stringConstant) {

        if (stringConstant != null && stringConstant.getText() != null) {
            return stringConstant.getText().replaceAll(QUOTE_CHAR, "").replaceAll(ESCAPED_QUOTE_CHAR, "\"");
        }

        return null;
    }

}
