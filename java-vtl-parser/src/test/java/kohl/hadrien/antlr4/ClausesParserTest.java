package kohl.hadrien.antlr4;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.io.Resources;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;
import org.assertj.core.api.SoftAssertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.charset.Charset;

public class ClausesParserTest {

  private static Grammar grammar;
  @ClassRule
  public static ExternalResource grammarResource = new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      URL grammarURL = getResource(this.getClass(), "Clauses.g4");
      String grammarString = Resources.toString(grammarURL, Charset.defaultCharset());
      grammar = new Grammar(grammarString);
    }
  };

  @Test
  public void testRenameWithRole() throws Exception {

    SoftAssertions softly = new SoftAssertions();

    String expectedTree;
    String actualTree;

    String renameAsIdentifier = "[rename componentName as string role = IDENTIFIER]";
    softly.assertThat(filterWhiteSpaces(parse(renameAsIdentifier)))
        .isEqualTo(filterWhiteSpaces(""
            + "("
            + "  clause:1 ["
            + "             ("
            + "               rename:1 rename (renameParam:1(component:1componentName)as(string:1string)(role:1role=IDENTIFIER))"
            + "             )"
            + "           ]"
            + ")"));

    String renameAsMeasure = "[rename componentName as string role = MEASURE]";
    softly.assertThat(filterWhiteSpaces(parse(renameAsMeasure)))
        .isEqualTo(filterWhiteSpaces(""
            + "("
            + "  clause:1 ["
            + "             ("
            + "               rename:1 rename (renameParam:1(component:1componentName)as(string:1string)(role:1role=MEASURE))"
            + "             )"
            + "           ]"
            + ")"));

    String renameAsAttribute = "[rename componentName as string role = ATTRIBUTE]";
    softly.assertThat(filterWhiteSpaces(parse(renameAsAttribute)))
        .isEqualTo(filterWhiteSpaces(""
        + "("
        + "  clause:1 ["
        + "             ("
        + "               rename:1 rename (renameParam:1(component:1componentName)as(string:1string)(role:1role=ATTRIBUTE))"
        + "             )"
        + "           ]"
        + ")"));

    softly.assertAll();
  }

  @Test
  public void testMultipleRenames() throws Exception {
    String expression = "[rename "
        + "componentName as string, "
        + "componentName as string, "
        + "componentName as string"
        + "]";

    String actualTree = parse(expression);
    String expectedTree = ""
        + "("
        + "  clause:1 ["
        + "             ("
        + "               rename:1 rename (renameParam:1 (component:1 componentName) as (string:1 string)),"
        + "                               (renameParam:1 (component:1 componentName) as (string:1 string)),"
        + "                               (renameParam:1 (component:1 componentName) as (string:1 string))"
        + "             )"
        + "           ]"
        + ")";
    assertThat(filterWhiteSpaces(actualTree)).isEqualTo(
        filterWhiteSpaces(expectedTree));
  }

  @Test
  public void testMultipleRenamesWithRoles() throws Exception {

    String expression = "[rename "
        + "componentName as string role = IDENTIFIER, "
        + "componentName as string role = MEASURE, "
        + "componentName as string role = ATTRIBUTE"
        + "]";
    assertThat(filterWhiteSpaces(parse(expression)))
        .isEqualTo(filterWhiteSpaces(""
            + "("
            + "  clause:1 ["
            + "             ("
            + "               rename:1 rename (renameParam:1 (component:1 componentName) as (string:1 string) (role:1 role = IDENTIFIER)),"
            + "                               (renameParam:1 (component:1 componentName) as (string:1 string) (role:1 role = MEASURE)),"
            + "                               (renameParam:1 (component:1 componentName) as (string:1 string) (role:1 role = ATTRIBUTE))"
            + "             )"
            + "           ]"
            + ")"));
  }

  // TODO: Build a more robust way to test.
  public String parse(String expression) {
    LexerInterpreter lexerInterpreter = grammar.createLexerInterpreter(
        new ANTLRInputStream(expression)
    );
    GrammarParserInterpreter parserInterpreter = grammar.createGrammarParserInterpreter(
        new CommonTokenStream(lexerInterpreter)
    );

    Rule clause = grammar.getRule("clause");
    parserInterpreter.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());
    ParserRuleContext parse = parserInterpreter.parse(clause.index);
    return parse.toStringTree(parserInterpreter);
  }

  String filterWhiteSpaces(String string) {
    return string.replaceAll("\\s+", "");
  }
}
