package kohl.hadrien.vtl.script.error;

import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import javax.script.ScriptException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hadrien on 13/12/2016.
 */
public class WrappedException extends RecognitionException {

    private final ScriptException cause = null;

    public WrappedException(Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx, ScriptException cause) {
        super(recognizer, input, ctx);
        cause = checkNotNull(cause);
    }

    public WrappedException(String message, Recognizer<?, ?> recognizer, IntStream input, ParserRuleContext ctx, ScriptException cause) {
        super(message, recognizer, input, ctx);
        cause = checkNotNull(cause);
    }

    public ScriptException getCause() {
        return cause;
    }
}
