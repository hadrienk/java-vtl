// Generated from /Users/hadrien/Projects/java-vtl/java-vtl-parser/src/main/antlr4/imports/Clauses.g4 by ANTLR 4.5.3
package imports;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClausesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		WS=18;
	public static final int
		RULE_clause = 0, RULE_rename = 1, RULE_renameParam = 2, RULE_role = 3, 
		RULE_filter = 4, RULE_keep = 5, RULE_calc = 6, RULE_attrcalc = 7, RULE_aggregate = 8, 
		RULE_booleanExpression = 9, RULE_varID = 10;
	public static final String[] ruleNames = {
		"clause", "rename", "renameParam", "role", "filter", "keep", "calc", "attrcalc", 
		"aggregate", "booleanExpression", "varID"
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

	@Override
	public String getGrammarFileName() { return "Clauses.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ClausesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ClauseContext extends ParserRuleContext {
		public RenameContext rename() {
			return getRuleContext(RenameContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public KeepContext keep() {
			return getRuleContext(KeepContext.class,0);
		}
		public CalcContext calc() {
			return getRuleContext(CalcContext.class,0);
		}
		public AttrcalcContext attrcalc() {
			return getRuleContext(AttrcalcContext.class,0);
		}
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(T__0);
			setState(29);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(23);
				rename();
				}
				break;
			case T__10:
				{
				setState(24);
				filter();
				}
				break;
			case T__11:
				{
				setState(25);
				keep();
				}
				break;
			case T__12:
				{
				setState(26);
				calc();
				}
				break;
			case T__13:
				{
				setState(27);
				attrcalc();
				}
				break;
			case T__14:
				{
				setState(28);
				aggregate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(31);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RenameContext extends ParserRuleContext {
		public List<RenameParamContext> renameParam() {
			return getRuleContexts(RenameParamContext.class);
		}
		public RenameParamContext renameParam(int i) {
			return getRuleContext(RenameParamContext.class,i);
		}
		public RenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterRename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitRename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitRename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameContext rename() throws RecognitionException {
		RenameContext _localctx = new RenameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_rename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__2);
			setState(34);
			renameParam();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(35);
				match(T__3);
				setState(36);
				renameParam();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RenameParamContext extends ParserRuleContext {
		public VarIDContext from;
		public VarIDContext to;
		public List<VarIDContext> varID() {
			return getRuleContexts(VarIDContext.class);
		}
		public VarIDContext varID(int i) {
			return getRuleContext(VarIDContext.class,i);
		}
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public RenameParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterRenameParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitRenameParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitRenameParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameParamContext renameParam() throws RecognitionException {
		RenameParamContext _localctx = new RenameParamContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_renameParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			((RenameParamContext)_localctx).from = varID();
			setState(43);
			match(T__4);
			setState(44);
			((RenameParamContext)_localctx).to = varID();
			setState(48);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(45);
				match(T__5);
				setState(46);
				match(T__6);
				setState(47);
				role();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoleContext extends ParserRuleContext {
		public RoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_role; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleContext role() throws RecognitionException {
		RoleContext _localctx = new RoleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_role);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__10);
			setState(53);
			booleanExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeepContext extends ParserRuleContext {
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public KeepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterKeep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitKeep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitKeep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeepContext keep() throws RecognitionException {
		KeepContext _localctx = new KeepContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_keep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__11);
			setState(56);
			booleanExpression();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(57);
				match(T__3);
				setState(58);
				booleanExpression();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalcContext extends ParserRuleContext {
		public CalcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterCalc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitCalc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitCalc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcContext calc() throws RecognitionException {
		CalcContext _localctx = new CalcContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_calc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrcalcContext extends ParserRuleContext {
		public AttrcalcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrcalc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterAttrcalc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitAttrcalc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitAttrcalc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrcalcContext attrcalc() throws RecognitionException {
		AttrcalcContext _localctx = new AttrcalcContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attrcalc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateContext extends ParserRuleContext {
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitAggregate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitAggregate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanExpressionContext extends ParserRuleContext {
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitBooleanExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_booleanExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarIDContext extends ParserRuleContext {
		public VarIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).enterVarID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClausesListener ) ((ClausesListener)listener).exitVarID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClausesVisitor ) return ((ClausesVisitor<? extends T>)visitor).visitVarID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarIDContext varID() throws RecognitionException {
		VarIDContext _localctx = new VarIDContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\24M\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2 \n\2\3\2\3\2\3\3\3\3\3\3\3\3\7"+
		"\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\63\n\4\3\5\3\5\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\7\7>\n\7\f\7\16\7A\13\7\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\n\f"+
		"I\2\30\3\2\2\2\4#\3\2\2\2\6,\3\2\2\2\b\64\3\2\2\2\n\66\3\2\2\2\f9\3\2"+
		"\2\2\16B\3\2\2\2\20D\3\2\2\2\22F\3\2\2\2\24H\3\2\2\2\26J\3\2\2\2\30\37"+
		"\7\3\2\2\31 \5\4\3\2\32 \5\n\6\2\33 \5\f\7\2\34 \5\16\b\2\35 \5\20\t\2"+
		"\36 \5\22\n\2\37\31\3\2\2\2\37\32\3\2\2\2\37\33\3\2\2\2\37\34\3\2\2\2"+
		"\37\35\3\2\2\2\37\36\3\2\2\2 !\3\2\2\2!\"\7\4\2\2\"\3\3\2\2\2#$\7\5\2"+
		"\2$)\5\6\4\2%&\7\6\2\2&(\5\6\4\2\'%\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2"+
		"\2\2*\5\3\2\2\2+)\3\2\2\2,-\5\26\f\2-.\7\7\2\2.\62\5\26\f\2/\60\7\b\2"+
		"\2\60\61\7\t\2\2\61\63\5\b\5\2\62/\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2\2"+
		"\64\65\t\2\2\2\65\t\3\2\2\2\66\67\7\r\2\2\678\5\24\13\28\13\3\2\2\29:"+
		"\7\16\2\2:?\5\24\13\2;<\7\6\2\2<>\5\24\13\2=;\3\2\2\2>A\3\2\2\2?=\3\2"+
		"\2\2?@\3\2\2\2@\r\3\2\2\2A?\3\2\2\2BC\7\17\2\2C\17\3\2\2\2DE\7\20\2\2"+
		"E\21\3\2\2\2FG\7\21\2\2G\23\3\2\2\2HI\7\22\2\2I\25\3\2\2\2JK\7\23\2\2"+
		"K\27\3\2\2\2\6\37)\62?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}