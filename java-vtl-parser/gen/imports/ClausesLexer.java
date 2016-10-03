// Generated from /Users/hadrien/Projects/java-vtl/java-vtl-parser/src/main/antlr4/imports/Clauses.g4 by ANTLR 4.5.3
package imports;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClausesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		WS=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "'rename'", "','", "'as'", "'role'", "'='", "'IDENTIFIER'", 
		"'MEASURE'", "'ATTRIBUTE'", "'filter'", "'keep'", "'calc'", "'attrcalc'", 
		"'aggregate'", "'booleanExpression'", "'varId'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ClausesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Clauses.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24\u009b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\2\2\24\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\3\2\3\4\2\13\f\"\"\u009a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5)\3\2\2"+
		"\2\7+\3\2\2\2\t\62\3\2\2\2\13\64\3\2\2\2\r\67\3\2\2\2\17<\3\2\2\2\21>"+
		"\3\2\2\2\23I\3\2\2\2\25Q\3\2\2\2\27[\3\2\2\2\31b\3\2\2\2\33g\3\2\2\2\35"+
		"l\3\2\2\2\37u\3\2\2\2!\177\3\2\2\2#\u0091\3\2\2\2%\u0097\3\2\2\2\'(\7"+
		"]\2\2(\4\3\2\2\2)*\7_\2\2*\6\3\2\2\2+,\7t\2\2,-\7g\2\2-.\7p\2\2./\7c\2"+
		"\2/\60\7o\2\2\60\61\7g\2\2\61\b\3\2\2\2\62\63\7.\2\2\63\n\3\2\2\2\64\65"+
		"\7c\2\2\65\66\7u\2\2\66\f\3\2\2\2\678\7t\2\289\7q\2\29:\7n\2\2:;\7g\2"+
		"\2;\16\3\2\2\2<=\7?\2\2=\20\3\2\2\2>?\7K\2\2?@\7F\2\2@A\7G\2\2AB\7P\2"+
		"\2BC\7V\2\2CD\7K\2\2DE\7H\2\2EF\7K\2\2FG\7G\2\2GH\7T\2\2H\22\3\2\2\2I"+
		"J\7O\2\2JK\7G\2\2KL\7C\2\2LM\7U\2\2MN\7W\2\2NO\7T\2\2OP\7G\2\2P\24\3\2"+
		"\2\2QR\7C\2\2RS\7V\2\2ST\7V\2\2TU\7T\2\2UV\7K\2\2VW\7D\2\2WX\7W\2\2XY"+
		"\7V\2\2YZ\7G\2\2Z\26\3\2\2\2[\\\7h\2\2\\]\7k\2\2]^\7n\2\2^_\7v\2\2_`\7"+
		"g\2\2`a\7t\2\2a\30\3\2\2\2bc\7m\2\2cd\7g\2\2de\7g\2\2ef\7r\2\2f\32\3\2"+
		"\2\2gh\7e\2\2hi\7c\2\2ij\7n\2\2jk\7e\2\2k\34\3\2\2\2lm\7c\2\2mn\7v\2\2"+
		"no\7v\2\2op\7t\2\2pq\7e\2\2qr\7c\2\2rs\7n\2\2st\7e\2\2t\36\3\2\2\2uv\7"+
		"c\2\2vw\7i\2\2wx\7i\2\2xy\7t\2\2yz\7g\2\2z{\7i\2\2{|\7c\2\2|}\7v\2\2}"+
		"~\7g\2\2~ \3\2\2\2\177\u0080\7d\2\2\u0080\u0081\7q\2\2\u0081\u0082\7q"+
		"\2\2\u0082\u0083\7n\2\2\u0083\u0084\7g\2\2\u0084\u0085\7c\2\2\u0085\u0086"+
		"\7p\2\2\u0086\u0087\7G\2\2\u0087\u0088\7z\2\2\u0088\u0089\7r\2\2\u0089"+
		"\u008a\7t\2\2\u008a\u008b\7g\2\2\u008b\u008c\7u\2\2\u008c\u008d\7u\2\2"+
		"\u008d\u008e\7k\2\2\u008e\u008f\7q\2\2\u008f\u0090\7p\2\2\u0090\"\3\2"+
		"\2\2\u0091\u0092\7x\2\2\u0092\u0093\7c\2\2\u0093\u0094\7t\2\2\u0094\u0095"+
		"\7K\2\2\u0095\u0096\7f\2\2\u0096$\3\2\2\2\u0097\u0098\t\2\2\2\u0098\u0099"+
		"\3\2\2\2\u0099\u009a\b\23\2\2\u009a&\3\2\2\2\3\2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}