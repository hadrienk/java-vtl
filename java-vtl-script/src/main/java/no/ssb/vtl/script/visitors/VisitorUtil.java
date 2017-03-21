package no.ssb.vtl.script.visitors;

public final class VisitorUtil {

    public static final char QUOTE_CHAR = '\"';

    private VisitorUtil() {
    }

    public static boolean isQuoted(String str) {
        return str.charAt(0) == QUOTE_CHAR && str.charAt(str.length() - 1) == QUOTE_CHAR;
    }

}
