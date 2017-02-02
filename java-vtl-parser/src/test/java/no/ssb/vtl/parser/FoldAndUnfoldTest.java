package no.ssb.vtl.parser;

import com.google.common.io.Resources;
import org.antlr.v4.tool.Grammar;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Resources.getResource;
import static no.ssb.vtl.parser.ParserTestHelper.filterWhiteSpaces;
import static no.ssb.vtl.parser.ParserTestHelper.parse;

public class FoldAndUnfoldTest {
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
    public void testJoinFold() throws Exception {
        String rule = "joinFoldExpression";

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            String parseTree;

            parseTree = parse("fold \"varID1\", \"varID2\" to dataset.component, component", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinFoldExpression:1fold(foldUnfoldElements:1\"varID1\",\"varID2\")to(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)),(joinFoldUnfoldRef:2(componentID:1component)))"));

            parseTree = parse("fold \"varID1\" to component, dataset.component", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinFoldExpression:1fold(foldUnfoldElements:1\"varID1\")to(joinFoldUnfoldRef:2(componentID:1component)),(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)))"));

            parseTree = parse("fold \"varID1\" to component, component", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinFoldExpression:1fold(foldUnfoldElements:1\"varID1\")to(joinFoldUnfoldRef:2(componentID:1component)),(joinFoldUnfoldRef:2(componentID:1component)))"));

            parseTree = parse("fold \"varID1\" to dataset.component, dataset.component", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinFoldExpression:1fold(foldUnfoldElements:1\"varID1\")to(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)),(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)))"));

            softly.assertThatThrownBy(() -> parse("fold to dataset.component, component", rule, grammar));
            softly.assertThatThrownBy(() -> parse("fold \"varID1\" to component", rule, grammar));
            softly.assertThatThrownBy(() -> parse("fold \"varID1\" to ,component", rule, grammar));
        }


    }

    @Test
    public void testJoinUnfold() throws Exception {
        String rule = "joinUnfoldExpression";

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            String parseTree;

            parseTree = parse("unfold dataset.component, component to \"varID1\", \"varID2\"", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinUnfoldExpression:1unfold(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)),(joinFoldUnfoldRef:2(componentID:1component))to(foldUnfoldElements:1\"varID1\",\"varID2\"))"));

            parseTree = parse("unfold component, dataset.component to \"varID1\"", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinUnfoldExpression:1unfold(joinFoldUnfoldRef:2(componentID:1component)),(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component))to(foldUnfoldElements:1\"varID1\"))"));

            parseTree = parse("unfold dataset.component, dataset.component to \"varID1\"", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinUnfoldExpression:1unfold(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component)),(joinFoldUnfoldRef:1(varID:1dataset).(componentID:1component))to(foldUnfoldElements:1\"varID1\"))"));

            parseTree = parse("unfold component, component to \"varID1\"", rule, grammar);
            softly.assertThat(
                    filterWhiteSpaces(parseTree)
            ).isEqualTo(filterWhiteSpaces("(joinUnfoldExpression:1unfold(joinFoldUnfoldRef:2(componentID:1component)),(joinFoldUnfoldRef:2(componentID:1component))to(foldUnfoldElements:1\"varID1\"))"));

            softly.assertThatThrownBy(() -> parse("unfold dataset.component, component to ", rule, grammar));
            softly.assertThatThrownBy(() -> parse("unfold dataset.component, component, component to \"varID1\"", rule, grammar));
            softly.assertThatThrownBy(() -> parse("unfold component to \"varID1\"", rule, grammar));
            softly.assertThatThrownBy(() -> parse("unfold ,component to \"varID1\"", rule, grammar));
        }
    }
}
