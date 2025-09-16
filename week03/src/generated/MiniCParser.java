// Generated from /Users/chu-ilhyeon/CPI/week03/src/MiniC.g4 by ANTLR 4.13.2
package generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MiniCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, VOID=20, INT=21, WHILE=22, IF=23, ELSE=24, RETURN=25, 
		OR=26, AND=27, LE=28, GE=29, EQ=30, NE=31, IDENT=32, LITERAL=33, DecimalConstant=34, 
		OctalConstant=35, HexadecimalConstant=36, WS=37;
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_var_decl = 2, RULE_type_spec = 3, 
		RULE_fun_decl = 4, RULE_params = 5, RULE_param = 6, RULE_stmt = 7, RULE_expr_stmt = 8, 
		RULE_while_stmt = 9, RULE_compound_stmt = 10, RULE_local_decl = 11, RULE_if_stmt = 12, 
		RULE_return_stmt = 13, RULE_expr = 14, RULE_args = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "decl", "var_decl", "type_spec", "fun_decl", "params", "param", 
			"stmt", "expr_stmt", "while_stmt", "compound_stmt", "local_decl", "if_stmt", 
			"return_stmt", "expr", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'['", "']'", "'('", "')'", "','", "'{'", "'}'", 
			"'-'", "'+'", "'--'", "'++'", "'*'", "'/'", "'%'", "'<'", "'>'", "'!'", 
			"'void'", "'int'", "'while'", "'if'", "'else'", "'return'", "'or'", "'and'", 
			"'<='", "'>='", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "VOID", "INT", "WHILE", 
			"IF", "ELSE", "RETURN", "OR", "AND", "LE", "GE", "EQ", "NE", "IDENT", 
			"LITERAL", "DecimalConstant", "OctalConstant", "HexadecimalConstant", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "MiniC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				decl();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VOID || _la==INT );
			System.out.println("202300000 Rule 0");
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Fun_declContext fun_decl() {
			return getRuleContext(Fun_declContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				var_decl();
				System.out.println("202300000 Rule 1-1");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				fun_decl();
				System.out.println("202300000 Rule 1-2");
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Var_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitVar_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitVar_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_decl);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				type_spec();
				setState(48);
				match(IDENT);
				setState(49);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				type_spec();
				setState(52);
				match(IDENT);
				setState(53);
				match(T__1);
				setState(54);
				match(LITERAL);
				setState(55);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				type_spec();
				setState(58);
				match(IDENT);
				setState(59);
				match(T__2);
				setState(60);
				match(LITERAL);
				setState(61);
				match(T__3);
				setState(62);
				match(T__0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Type_specContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public Type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterType_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitType_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitType_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_specContext type_spec() throws RecognitionException {
		Type_specContext _localctx = new Type_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_spec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !(_la==VOID || _la==INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Fun_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Fun_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterFun_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitFun_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitFun_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fun_declContext fun_decl() throws RecognitionException {
		Fun_declContext _localctx = new Fun_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			type_spec();
			setState(69);
			match(IDENT);
			setState(70);
			match(T__4);
			setState(71);
			params();
			setState(72);
			match(T__5);
			setState(73);
			compound_stmt();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				param();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(76);
					match(T__6);
					setState(77);
					param();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(VOID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				type_spec();
				setState(88);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				type_spec();
				setState(91);
				match(IDENT);
				setState(92);
				match(T__2);
				setState(93);
				match(T__3);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__18:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				expr_stmt();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				compound_stmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				while_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				return_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitExpr_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			expr(0);
			setState(105);
			match(T__0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniCParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitWhile_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitWhile_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(WHILE);
			setState(108);
			match(T__4);
			setState(109);
			expr(0);
			setState(110);
			match(T__5);
			setState(111);
			stmt();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_stmtContext extends ParserRuleContext {
		public List<Local_declContext> local_decl() {
			return getRuleContexts(Local_declContext.class);
		}
		public Local_declContext local_decl(int i) {
			return getRuleContext(Local_declContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitCompound_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitCompound_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compound_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__7);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VOID || _la==INT) {
				{
				{
				setState(114);
				local_decl();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 12931579168L) != 0)) {
				{
				{
				setState(120);
				stmt();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(T__8);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Local_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Local_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterLocal_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitLocal_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitLocal_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_declContext local_decl() throws RecognitionException {
		Local_declContext _localctx = new Local_declContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_local_decl);
		try {
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				type_spec();
				setState(129);
				match(IDENT);
				setState(130);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				type_spec();
				setState(133);
				match(IDENT);
				setState(134);
				match(T__1);
				setState(135);
				match(LITERAL);
				setState(136);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				type_spec();
				setState(139);
				match(IDENT);
				setState(140);
				match(T__2);
				setState(141);
				match(LITERAL);
				setState(142);
				match(T__3);
				setState(143);
				match(T__0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniCParser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_if_stmt);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(IF);
				setState(148);
				match(T__4);
				setState(149);
				expr(0);
				setState(150);
				match(T__5);
				setState(151);
				stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(IF);
				setState(154);
				match(T__4);
				setState(155);
				expr(0);
				setState(156);
				match(T__5);
				setState(157);
				stmt();
				setState(158);
				match(ELSE);
				setState(159);
				stmt();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitReturn_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_return_stmt);
		try {
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				match(RETURN);
				setState(164);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(RETURN);
				setState(166);
				expr(0);
				setState(167);
				match(T__0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniCParser.EQ, 0); }
		public TerminalNode NE() { return getToken(MiniCParser.NE, 0); }
		public TerminalNode LE() { return getToken(MiniCParser.LE, 0); }
		public TerminalNode GE() { return getToken(MiniCParser.GE, 0); }
		public TerminalNode AND() { return getToken(MiniCParser.AND, 0); }
		public TerminalNode OR() { return getToken(MiniCParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(172);
				match(LITERAL);
				}
				break;
			case 2:
				{
				setState(173);
				match(T__4);
				setState(174);
				expr(0);
				setState(175);
				match(T__5);
				}
				break;
			case 3:
				{
				setState(177);
				match(IDENT);
				}
				break;
			case 4:
				{
				setState(178);
				match(IDENT);
				setState(179);
				match(T__2);
				setState(180);
				expr(0);
				setState(181);
				match(T__3);
				}
				break;
			case 5:
				{
				setState(183);
				match(IDENT);
				setState(184);
				match(T__4);
				setState(185);
				args();
				setState(186);
				match(T__5);
				}
				break;
			case 6:
				{
				setState(188);
				match(T__9);
				setState(189);
				expr(20);
				}
				break;
			case 7:
				{
				setState(190);
				match(T__10);
				setState(191);
				expr(19);
				}
				break;
			case 8:
				{
				setState(192);
				match(T__11);
				setState(193);
				expr(18);
				}
				break;
			case 9:
				{
				setState(194);
				match(T__12);
				setState(195);
				expr(17);
				}
				break;
			case 10:
				{
				setState(196);
				match(T__18);
				setState(197);
				expr(5);
				}
				break;
			case 11:
				{
				setState(198);
				match(IDENT);
				setState(199);
				match(T__1);
				setState(200);
				expr(2);
				}
				break;
			case 12:
				{
				setState(201);
				match(IDENT);
				setState(202);
				match(T__2);
				setState(203);
				expr(0);
				setState(204);
				match(T__3);
				setState(205);
				match(T__1);
				setState(206);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(251);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(249);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(211);
						match(T__13);
						setState(212);
						expr(17);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(214);
						match(T__14);
						setState(215);
						expr(16);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(217);
						match(T__15);
						setState(218);
						expr(15);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(219);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(220);
						match(T__10);
						setState(221);
						expr(14);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(222);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(223);
						match(T__9);
						setState(224);
						expr(13);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(225);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(226);
						match(EQ);
						setState(227);
						expr(12);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(229);
						match(NE);
						setState(230);
						expr(11);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(231);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(232);
						match(LE);
						setState(233);
						expr(10);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(235);
						match(T__16);
						setState(236);
						expr(9);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(237);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(238);
						match(GE);
						setState(239);
						expr(8);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(240);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(241);
						match(T__17);
						setState(242);
						expr(7);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(244);
						match(AND);
						setState(245);
						expr(5);
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(247);
						match(OR);
						setState(248);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(253);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_args);
		int _la;
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__18:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				expr(0);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(255);
					match(T__6);
					setState(256);
					expr(0);
					}
					}
					setState(261);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 4);
		case 12:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u010a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0004\u0000\"\b\u0000\u000b\u0000\f\u0000#\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001.\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005O\b\u0005\n\u0005"+
		"\f\u0005R\t\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006`\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007g\b\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005"+
		"\nt\b\n\n\n\f\nw\t\n\u0001\n\u0005\nz\b\n\n\n\f\n}\t\n\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0092\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00a2"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00aa\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00d1\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u00fa\b\u000e\n\u000e\f\u000e\u00fd\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u0102\b\u000f\n\u000f\f\u000f\u0105\t\u000f"+
		"\u0001\u000f\u0003\u000f\u0108\b\u000f\u0001\u000f\u0000\u0001\u001c\u0010"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e\u0000\u0001\u0001\u0000\u0014\u0015\u0125\u0000!\u0001\u0000"+
		"\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u0004@\u0001\u0000\u0000\u0000"+
		"\u0006B\u0001\u0000\u0000\u0000\bD\u0001\u0000\u0000\u0000\nU\u0001\u0000"+
		"\u0000\u0000\f_\u0001\u0000\u0000\u0000\u000ef\u0001\u0000\u0000\u0000"+
		"\u0010h\u0001\u0000\u0000\u0000\u0012k\u0001\u0000\u0000\u0000\u0014q"+
		"\u0001\u0000\u0000\u0000\u0016\u0091\u0001\u0000\u0000\u0000\u0018\u00a1"+
		"\u0001\u0000\u0000\u0000\u001a\u00a9\u0001\u0000\u0000\u0000\u001c\u00d0"+
		"\u0001\u0000\u0000\u0000\u001e\u0107\u0001\u0000\u0000\u0000 \"\u0003"+
		"\u0002\u0001\u0000! \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000"+
		"#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000"+
		"\u0000%&\u0006\u0000\uffff\uffff\u0000&\u0001\u0001\u0000\u0000\u0000"+
		"\'(\u0003\u0004\u0002\u0000()\u0006\u0001\uffff\uffff\u0000).\u0001\u0000"+
		"\u0000\u0000*+\u0003\b\u0004\u0000+,\u0006\u0001\uffff\uffff\u0000,.\u0001"+
		"\u0000\u0000\u0000-\'\u0001\u0000\u0000\u0000-*\u0001\u0000\u0000\u0000"+
		".\u0003\u0001\u0000\u0000\u0000/0\u0003\u0006\u0003\u000001\u0005 \u0000"+
		"\u000012\u0005\u0001\u0000\u00002A\u0001\u0000\u0000\u000034\u0003\u0006"+
		"\u0003\u000045\u0005 \u0000\u000056\u0005\u0002\u0000\u000067\u0005!\u0000"+
		"\u000078\u0005\u0001\u0000\u00008A\u0001\u0000\u0000\u00009:\u0003\u0006"+
		"\u0003\u0000:;\u0005 \u0000\u0000;<\u0005\u0003\u0000\u0000<=\u0005!\u0000"+
		"\u0000=>\u0005\u0004\u0000\u0000>?\u0005\u0001\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@/\u0001\u0000\u0000\u0000@3\u0001\u0000\u0000\u0000@9\u0001"+
		"\u0000\u0000\u0000A\u0005\u0001\u0000\u0000\u0000BC\u0007\u0000\u0000"+
		"\u0000C\u0007\u0001\u0000\u0000\u0000DE\u0003\u0006\u0003\u0000EF\u0005"+
		" \u0000\u0000FG\u0005\u0005\u0000\u0000GH\u0003\n\u0005\u0000HI\u0005"+
		"\u0006\u0000\u0000IJ\u0003\u0014\n\u0000J\t\u0001\u0000\u0000\u0000KP"+
		"\u0003\f\u0006\u0000LM\u0005\u0007\u0000\u0000MO\u0003\f\u0006\u0000N"+
		"L\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000"+
		"\u0000PQ\u0001\u0000\u0000\u0000QV\u0001\u0000\u0000\u0000RP\u0001\u0000"+
		"\u0000\u0000SV\u0005\u0014\u0000\u0000TV\u0001\u0000\u0000\u0000UK\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UT\u0001\u0000\u0000\u0000"+
		"V\u000b\u0001\u0000\u0000\u0000WX\u0003\u0006\u0003\u0000XY\u0005 \u0000"+
		"\u0000Y`\u0001\u0000\u0000\u0000Z[\u0003\u0006\u0003\u0000[\\\u0005 \u0000"+
		"\u0000\\]\u0005\u0003\u0000\u0000]^\u0005\u0004\u0000\u0000^`\u0001\u0000"+
		"\u0000\u0000_W\u0001\u0000\u0000\u0000_Z\u0001\u0000\u0000\u0000`\r\u0001"+
		"\u0000\u0000\u0000ag\u0003\u0010\b\u0000bg\u0003\u0014\n\u0000cg\u0003"+
		"\u0018\f\u0000dg\u0003\u0012\t\u0000eg\u0003\u001a\r\u0000fa\u0001\u0000"+
		"\u0000\u0000fb\u0001\u0000\u0000\u0000fc\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000g\u000f\u0001\u0000\u0000"+
		"\u0000hi\u0003\u001c\u000e\u0000ij\u0005\u0001\u0000\u0000j\u0011\u0001"+
		"\u0000\u0000\u0000kl\u0005\u0016\u0000\u0000lm\u0005\u0005\u0000\u0000"+
		"mn\u0003\u001c\u000e\u0000no\u0005\u0006\u0000\u0000op\u0003\u000e\u0007"+
		"\u0000p\u0013\u0001\u0000\u0000\u0000qu\u0005\b\u0000\u0000rt\u0003\u0016"+
		"\u000b\u0000sr\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001"+
		"\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v{\u0001\u0000\u0000\u0000"+
		"wu\u0001\u0000\u0000\u0000xz\u0003\u000e\u0007\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|~\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005\t\u0000\u0000\u007f\u0015\u0001\u0000\u0000\u0000\u0080\u0081\u0003"+
		"\u0006\u0003\u0000\u0081\u0082\u0005 \u0000\u0000\u0082\u0083\u0005\u0001"+
		"\u0000\u0000\u0083\u0092\u0001\u0000\u0000\u0000\u0084\u0085\u0003\u0006"+
		"\u0003\u0000\u0085\u0086\u0005 \u0000\u0000\u0086\u0087\u0005\u0002\u0000"+
		"\u0000\u0087\u0088\u0005!\u0000\u0000\u0088\u0089\u0005\u0001\u0000\u0000"+
		"\u0089\u0092\u0001\u0000\u0000\u0000\u008a\u008b\u0003\u0006\u0003\u0000"+
		"\u008b\u008c\u0005 \u0000\u0000\u008c\u008d\u0005\u0003\u0000\u0000\u008d"+
		"\u008e\u0005!\u0000\u0000\u008e\u008f\u0005\u0004\u0000\u0000\u008f\u0090"+
		"\u0005\u0001\u0000\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u0080"+
		"\u0001\u0000\u0000\u0000\u0091\u0084\u0001\u0000\u0000\u0000\u0091\u008a"+
		"\u0001\u0000\u0000\u0000\u0092\u0017\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0005\u0017\u0000\u0000\u0094\u0095\u0005\u0005\u0000\u0000\u0095\u0096"+
		"\u0003\u001c\u000e\u0000\u0096\u0097\u0005\u0006\u0000\u0000\u0097\u0098"+
		"\u0003\u000e\u0007\u0000\u0098\u00a2\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0005\u0017\u0000\u0000\u009a\u009b\u0005\u0005\u0000\u0000\u009b\u009c"+
		"\u0003\u001c\u000e\u0000\u009c\u009d\u0005\u0006\u0000\u0000\u009d\u009e"+
		"\u0003\u000e\u0007\u0000\u009e\u009f\u0005\u0018\u0000\u0000\u009f\u00a0"+
		"\u0003\u000e\u0007\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u0093"+
		"\u0001\u0000\u0000\u0000\u00a1\u0099\u0001\u0000\u0000\u0000\u00a2\u0019"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u0019\u0000\u0000\u00a4\u00aa"+
		"\u0005\u0001\u0000\u0000\u00a5\u00a6\u0005\u0019\u0000\u0000\u00a6\u00a7"+
		"\u0003\u001c\u000e\u0000\u00a7\u00a8\u0005\u0001\u0000\u0000\u00a8\u00aa"+
		"\u0001\u0000\u0000\u0000\u00a9\u00a3\u0001\u0000\u0000\u0000\u00a9\u00a5"+
		"\u0001\u0000\u0000\u0000\u00aa\u001b\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0006\u000e\uffff\uffff\u0000\u00ac\u00d1\u0005!\u0000\u0000\u00ad\u00ae"+
		"\u0005\u0005\u0000\u0000\u00ae\u00af\u0003\u001c\u000e\u0000\u00af\u00b0"+
		"\u0005\u0006\u0000\u0000\u00b0\u00d1\u0001\u0000\u0000\u0000\u00b1\u00d1"+
		"\u0005 \u0000\u0000\u00b2\u00b3\u0005 \u0000\u0000\u00b3\u00b4\u0005\u0003"+
		"\u0000\u0000\u00b4\u00b5\u0003\u001c\u000e\u0000\u00b5\u00b6\u0005\u0004"+
		"\u0000\u0000\u00b6\u00d1\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005 \u0000"+
		"\u0000\u00b8\u00b9\u0005\u0005\u0000\u0000\u00b9\u00ba\u0003\u001e\u000f"+
		"\u0000\u00ba\u00bb\u0005\u0006\u0000\u0000\u00bb\u00d1\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0005\n\u0000\u0000\u00bd\u00d1\u0003\u001c\u000e\u0014"+
		"\u00be\u00bf\u0005\u000b\u0000\u0000\u00bf\u00d1\u0003\u001c\u000e\u0013"+
		"\u00c0\u00c1\u0005\f\u0000\u0000\u00c1\u00d1\u0003\u001c\u000e\u0012\u00c2"+
		"\u00c3\u0005\r\u0000\u0000\u00c3\u00d1\u0003\u001c\u000e\u0011\u00c4\u00c5"+
		"\u0005\u0013\u0000\u0000\u00c5\u00d1\u0003\u001c\u000e\u0005\u00c6\u00c7"+
		"\u0005 \u0000\u0000\u00c7\u00c8\u0005\u0002\u0000\u0000\u00c8\u00d1\u0003"+
		"\u001c\u000e\u0002\u00c9\u00ca\u0005 \u0000\u0000\u00ca\u00cb\u0005\u0003"+
		"\u0000\u0000\u00cb\u00cc\u0003\u001c\u000e\u0000\u00cc\u00cd\u0005\u0004"+
		"\u0000\u0000\u00cd\u00ce\u0005\u0002\u0000\u0000\u00ce\u00cf\u0003\u001c"+
		"\u000e\u0001\u00cf\u00d1\u0001\u0000\u0000\u0000\u00d0\u00ab\u0001\u0000"+
		"\u0000\u0000\u00d0\u00ad\u0001\u0000\u0000\u0000\u00d0\u00b1\u0001\u0000"+
		"\u0000\u0000\u00d0\u00b2\u0001\u0000\u0000\u0000\u00d0\u00b7\u0001\u0000"+
		"\u0000\u0000\u00d0\u00bc\u0001\u0000\u0000\u0000\u00d0\u00be\u0001\u0000"+
		"\u0000\u0000\u00d0\u00c0\u0001\u0000\u0000\u0000\u00d0\u00c2\u0001\u0000"+
		"\u0000\u0000\u00d0\u00c4\u0001\u0000\u0000\u0000\u00d0\u00c6\u0001\u0000"+
		"\u0000\u0000\u00d0\u00c9\u0001\u0000\u0000\u0000\u00d1\u00fb\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\n\u0010\u0000\u0000\u00d3\u00d4\u0005\u000e\u0000"+
		"\u0000\u00d4\u00fa\u0003\u001c\u000e\u0011\u00d5\u00d6\n\u000f\u0000\u0000"+
		"\u00d6\u00d7\u0005\u000f\u0000\u0000\u00d7\u00fa\u0003\u001c\u000e\u0010"+
		"\u00d8\u00d9\n\u000e\u0000\u0000\u00d9\u00da\u0005\u0010\u0000\u0000\u00da"+
		"\u00fa\u0003\u001c\u000e\u000f\u00db\u00dc\n\r\u0000\u0000\u00dc\u00dd"+
		"\u0005\u000b\u0000\u0000\u00dd\u00fa\u0003\u001c\u000e\u000e\u00de\u00df"+
		"\n\f\u0000\u0000\u00df\u00e0\u0005\n\u0000\u0000\u00e0\u00fa\u0003\u001c"+
		"\u000e\r\u00e1\u00e2\n\u000b\u0000\u0000\u00e2\u00e3\u0005\u001e\u0000"+
		"\u0000\u00e3\u00fa\u0003\u001c\u000e\f\u00e4\u00e5\n\n\u0000\u0000\u00e5"+
		"\u00e6\u0005\u001f\u0000\u0000\u00e6\u00fa\u0003\u001c\u000e\u000b\u00e7"+
		"\u00e8\n\t\u0000\u0000\u00e8\u00e9\u0005\u001c\u0000\u0000\u00e9\u00fa"+
		"\u0003\u001c\u000e\n\u00ea\u00eb\n\b\u0000\u0000\u00eb\u00ec\u0005\u0011"+
		"\u0000\u0000\u00ec\u00fa\u0003\u001c\u000e\t\u00ed\u00ee\n\u0007\u0000"+
		"\u0000\u00ee\u00ef\u0005\u001d\u0000\u0000\u00ef\u00fa\u0003\u001c\u000e"+
		"\b\u00f0\u00f1\n\u0006\u0000\u0000\u00f1\u00f2\u0005\u0012\u0000\u0000"+
		"\u00f2\u00fa\u0003\u001c\u000e\u0007\u00f3\u00f4\n\u0004\u0000\u0000\u00f4"+
		"\u00f5\u0005\u001b\u0000\u0000\u00f5\u00fa\u0003\u001c\u000e\u0005\u00f6"+
		"\u00f7\n\u0003\u0000\u0000\u00f7\u00f8\u0005\u001a\u0000\u0000\u00f8\u00fa"+
		"\u0003\u001c\u000e\u0004\u00f9\u00d2\u0001\u0000\u0000\u0000\u00f9\u00d5"+
		"\u0001\u0000\u0000\u0000\u00f9\u00d8\u0001\u0000\u0000\u0000\u00f9\u00db"+
		"\u0001\u0000\u0000\u0000\u00f9\u00de\u0001\u0000\u0000\u0000\u00f9\u00e1"+
		"\u0001\u0000\u0000\u0000\u00f9\u00e4\u0001\u0000\u0000\u0000\u00f9\u00e7"+
		"\u0001\u0000\u0000\u0000\u00f9\u00ea\u0001\u0000\u0000\u0000\u00f9\u00ed"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f0\u0001\u0000\u0000\u0000\u00f9\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f6\u0001\u0000\u0000\u0000\u00fa\u00fd"+
		"\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0001\u0000\u0000\u0000\u00fc\u001d\u0001\u0000\u0000\u0000\u00fd\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fe\u0103\u0003\u001c\u000e\u0000\u00ff\u0100"+
		"\u0005\u0007\u0000\u0000\u0100\u0102\u0003\u001c\u000e\u0000\u0101\u00ff"+
		"\u0001\u0000\u0000\u0000\u0102\u0105\u0001\u0000\u0000\u0000\u0103\u0101"+
		"\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0108"+
		"\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0106\u0108"+
		"\u0001\u0000\u0000\u0000\u0107\u00fe\u0001\u0000\u0000\u0000\u0107\u0106"+
		"\u0001\u0000\u0000\u0000\u0108\u001f\u0001\u0000\u0000\u0000\u0011#-@"+
		"PU_fu{\u0091\u00a1\u00a9\u00d0\u00f9\u00fb\u0103\u0107";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}