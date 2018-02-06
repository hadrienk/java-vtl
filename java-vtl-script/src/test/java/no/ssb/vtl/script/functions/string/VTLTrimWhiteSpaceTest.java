package no.ssb.vtl.script.functions.string;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.base.Strings;
import com.google.common.primitives.Chars;
import no.ssb.vtl.model.VTLString;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class VTLTrimWhiteSpaceTest {

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void testTrimWhiteSpaces() throws Exception {
        VTLLeftTrim leftTrim = VTLLeftTrim.getInstance();
        VTLRightTrim rightTrim = VTLRightTrim.getInstance();
        VTLTrim trim = VTLTrim.getInstance();

        // @formatter:off
        List<Character> whiteCharacters = Chars.asList(
            '\u0009', // character tabulation
                '\n', // line feed
            '\u000B', // line tabulation
            '\u000C', // form feed
                '\r', // carriage return
            '\u0020', // space
            '\u0085', // next line
            '\u00A0', // no-break space
            '\u1680', // ogham space mark
            '\u2000', // en quad
            '\u2001', // em quad
            '\u2002', // en space
            '\u2003', // em space
            '\u2004', // three-per-em space
            '\u2005', // four-per-em space
            '\u2006', // six-per-em
            '\u2007', // figure space
            '\u2008', // punctuation space
            '\u2009', // thin space
            '\u200A', // hair space
            '\u2028', // line separator
            '\u2029', // paragraph separator
            '\u202F', // narrow no-break space
            '\u205F', // medium mathematical space
            '\u3000'  // ideographic space
        );
        // @formatter:on

        for (Character whiteCharacter : whiteCharacters) {
            VTLString expected = VTLString.of("test");
            String white = Strings.repeat(whiteCharacter.toString(), 10);
            VTLString test = VTLString.of(white + expected + white);

            VTLString leftRightResult = leftTrim.invoke(asList(rightTrim.invoke(asList(test))));
            VTLString trimResult = trim.invoke(asList(test));

            softly.assertThat(expected).isEqualTo(leftRightResult);
            softly.assertThat(expected).isEqualTo(trimResult);

            softly.assertThat(expected.get()).isEqualTo(leftRightResult.get());
            softly.assertThat(expected.get()).isEqualTo(trimResult.get());

        }
    }
}
