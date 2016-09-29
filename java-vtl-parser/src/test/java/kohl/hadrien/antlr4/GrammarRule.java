package kohl.hadrien.antlr4;

import static com.google.common.io.Resources.getResource;

import com.google.common.io.Resources;

import org.antlr.runtime.RecognitionException;
import org.antlr.v4.tool.Grammar;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class GrammarRule extends Grammar implements TestRule {

  public GrammarRule(String path) throws RecognitionException, IOException {
    super("");
    URL grammerURL = getResource(this.getClass(), path);
    String grammarString = Resources.toString(grammerURL, Charset.defaultCharset());
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return null;
  }
}
