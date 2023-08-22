// Generated from synan/PrevParser.g4 by ANTLR 4.8


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;

	import prev.phase.lexan.LexAn.PrevToken;
	import prev.data.ast.tree.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.type.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, TAB=3, NONE=4, TRUE=5, FALSE=6, INTCONST=7, CHARACTERCONST=8, 
		STRINGCONST=9, POINTER=10, LEFTBRAC=11, RIGHTBRAC=12, LEFTCURL=13, RIGHTCURL=14, 
		LEFTSQR=15, RIGHTSQR=16, DOT=17, COMMA=18, COL=19, SEMCOL=20, AND=21, 
		OR=22, EXCL=23, EQL=24, NEQ=25, LT=26, GT=27, LTE=28, GTE=29, STAR=30, 
		SLASH=31, MOD=32, PLUS=33, MINUS=34, CARET=35, ASIGN=36, BOOLEAN=37, CHAR=38, 
		DEL=39, DO=40, ELSE=41, FUNCTION=42, IF=43, INTEGER=44, NEW=45, THEN=46, 
		TYPE=47, VARIABLE=48, VOID=49, WHERE=50, WHILE=51, IDENTIFIER=52, ERROR=53;
	public static final int
		RULE_source = 0, RULE_type_decl = 1, RULE_var_decl = 2, RULE_func_decl = 3, 
		RULE_decl = 4, RULE_decl_ = 5, RULE_atomicType = 6, RULE_namedType = 7, 
		RULE_arrayType = 8, RULE_pointerType = 9, RULE_recordType = 10, RULE_enclosedType = 11, 
		RULE_type = 12, RULE_type_ = 13, RULE_expr = 14, RULE_orexpr = 15, RULE_a = 16, 
		RULE_andexpr = 17, RULE_b = 18, RULE_relationalexpr = 19, RULE_c = 20, 
		RULE_c1 = 21, RULE_aditiveexpr = 22, RULE_d = 23, RULE_multiplicativeexpr = 24, 
		RULE_prefixexpr = 25, RULE_f = 26, RULE_postfixexpr = 27, RULE_primaryexpr = 28, 
		RULE_idExpr = 29, RULE_funcCall = 30, RULE_args = 31, RULE_bracExpr = 32, 
		RULE_castExpr = 33, RULE_expr1 = 34, RULE_expr3 = 35, RULE_expr4 = 36, 
		RULE_expr5 = 37, RULE_whereexpr = 38, RULE_cnst = 39, RULE_stmt = 40, 
		RULE_stmt_ = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "type_decl", "var_decl", "func_decl", "decl", "decl_", "atomicType", 
			"namedType", "arrayType", "pointerType", "recordType", "enclosedType", 
			"type", "type_", "expr", "orexpr", "a", "andexpr", "b", "relationalexpr", 
			"c", "c1", "aditiveexpr", "d", "multiplicativeexpr", "prefixexpr", "f", 
			"postfixexpr", "primaryexpr", "idExpr", "funcCall", "args", "bracExpr", 
			"castExpr", "expr1", "expr3", "expr4", "expr5", "whereexpr", "cnst", 
			"stmt", "stmt_"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'none'", "'true'", "'false'", null, null, null, 
			"'nil'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'.'", "','", "':'", 
			"';'", "'&'", "'|'", "'!'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'^'", "'='", "'boolean'", "'char'", 
			"'del'", "'do'", "'else'", "'fun'", "'if'", "'integer'", "'new'", "'then'", 
			"'typ'", "'var'", "'void'", "'where'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "WS", "TAB", "NONE", "TRUE", "FALSE", "INTCONST", "CHARACTERCONST", 
			"STRINGCONST", "POINTER", "LEFTBRAC", "RIGHTBRAC", "LEFTCURL", "RIGHTCURL", 
			"LEFTSQR", "RIGHTSQR", "DOT", "COMMA", "COL", "SEMCOL", "AND", "OR", 
			"EXCL", "EQL", "NEQ", "LT", "GT", "LTE", "GTE", "STAR", "SLASH", "MOD", 
			"PLUS", "MINUS", "CARET", "ASIGN", "BOOLEAN", "CHAR", "DEL", "DO", "ELSE", 
			"FUNCTION", "IF", "INTEGER", "NEW", "THEN", "TYPE", "VARIABLE", "VOID", 
			"WHERE", "WHILE", "IDENTIFIER", "ERROR"
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
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourceContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstDecl> dcls = new Vector<AstDecl>();;
		public DeclContext decl;
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				((SourceContext)_localctx).decl = decl();
				 
						_localctx.dcls.add(((SourceContext)_localctx).decl.ast);
						((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(_localctx.dcls);
					
				}
				}
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << TYPE) | (1L << VARIABLE))) != 0) );
			setState(91);
			match(EOF);
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

	public static class Type_declContext extends ParserRuleContext {
		public AstTypeDecl ast;
		public Token typ;
		public Token name;
		public TypeContext type;
		public TerminalNode ASIGN() { return getToken(PrevParser.ASIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(PrevParser.TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public Type_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_decl; }
	}

	public final Type_declContext type_decl() throws RecognitionException {
		Type_declContext _localctx = new Type_declContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			((Type_declContext)_localctx).typ = match(TYPE);
			setState(94);
			((Type_declContext)_localctx).name = match(IDENTIFIER);
			setState(95);
			match(ASIGN);
			setState(96);
			((Type_declContext)_localctx).type = type();

					Location loc = new Location(((PrevToken) ((Type_declContext)_localctx).typ).location(), ((Type_declContext)_localctx).type.ast.location());
					((Type_declContext)_localctx).ast =  new AstTypeDecl(loc, ((Type_declContext)_localctx).name.getText(), ((Type_declContext)_localctx).type.ast);
				
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

	public static class Var_declContext extends ParserRuleContext {
		public AstVarDecl ast;
		public Token var;
		public Token name;
		public TypeContext type;
		public TerminalNode COL() { return getToken(PrevParser.COL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(PrevParser.VARIABLE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			((Var_declContext)_localctx).var = match(VARIABLE);
			setState(100);
			((Var_declContext)_localctx).name = match(IDENTIFIER);
			setState(101);
			match(COL);
			setState(102);
			((Var_declContext)_localctx).type = type();

					Location loc = new Location(((PrevToken) ((Var_declContext)_localctx).var).location(), ((Var_declContext)_localctx).type.ast.location());
					((Var_declContext)_localctx).ast =  new AstVarDecl(loc, ((Var_declContext)_localctx).name.getText(), ((Var_declContext)_localctx).type.ast);
				
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

	public static class Func_declContext extends ParserRuleContext {
		public AstFunDecl ast;
		public Token fun;
		public Token name;
		public Decl_Context decl_;
		public TypeContext type;
		public ExprContext expr;
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public Decl_Context decl_() {
			return getRuleContext(Decl_Context.class,0);
		}
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public TerminalNode COL() { return getToken(PrevParser.COL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASIGN() { return getToken(PrevParser.ASIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FUNCTION() { return getToken(PrevParser.FUNCTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((Func_declContext)_localctx).fun = match(FUNCTION);
			setState(106);
			((Func_declContext)_localctx).name = match(IDENTIFIER);
			setState(107);
			match(LEFTBRAC);
			setState(108);
			((Func_declContext)_localctx).decl_ = decl_();
			setState(109);
			match(RIGHTBRAC);
			setState(110);
			match(COL);
			setState(111);
			((Func_declContext)_localctx).type = type();
			setState(112);
			match(ASIGN);
			setState(113);
			((Func_declContext)_localctx).expr = expr(0);

					
					Location loc = new Location(((PrevToken) ((Func_declContext)_localctx).fun).location(), ((Func_declContext)_localctx).type.ast.location());
					((Func_declContext)_localctx).ast =  new AstFunDecl(loc, ((Func_declContext)_localctx).name.getText(), ((Func_declContext)_localctx).decl_.ast, ((Func_declContext)_localctx).type.ast, ((Func_declContext)_localctx).expr.ast);
					
				
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

	public static class DeclContext extends ParserRuleContext {
		public AstDecl ast;
		public Type_declContext type_decl;
		public Var_declContext var_decl;
		public Func_declContext func_decl;
		public Type_declContext type_decl() {
			return getRuleContext(Type_declContext.class,0);
		}
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Func_declContext func_decl() {
			return getRuleContext(Func_declContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decl);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((DeclContext)_localctx).type_decl = type_decl();
				 ((DeclContext)_localctx).ast =  ((DeclContext)_localctx).type_decl.ast; 
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				((DeclContext)_localctx).var_decl = var_decl();
				 ((DeclContext)_localctx).ast =  ((DeclContext)_localctx).var_decl.ast; 
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				((DeclContext)_localctx).func_decl = func_decl();
				 ((DeclContext)_localctx).ast =  ((DeclContext)_localctx).func_decl.ast;
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

	public static class Decl_Context extends ParserRuleContext {
		public AstTrees<AstParDecl> ast;
		public Vector<AstParDecl> parDecls = new Vector<AstParDecl>();;
		public Token name1;
		public TypeContext t1;
		public Token comma;
		public Token name2;
		public TypeContext t2;
		public List<TerminalNode> COL() { return getTokens(PrevParser.COL); }
		public TerminalNode COL(int i) {
			return getToken(PrevParser.COL, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public Decl_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_; }
	}

	public final Decl_Context decl_() throws RecognitionException {
		Decl_Context _localctx = new Decl_Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_decl_);
		int _la;
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				((Decl_Context)_localctx).name1 = match(IDENTIFIER);
				setState(128);
				match(COL);
				setState(129);
				((Decl_Context)_localctx).t1 = type();

						Location parLoc = new Location(((PrevToken) ((Decl_Context)_localctx).name1).location(), ((Decl_Context)_localctx).t1.ast.location());
						
						_localctx.parDecls.add(new AstParDecl(parLoc, ((Decl_Context)_localctx).name1.getText(), ((Decl_Context)_localctx).t1.ast));
					
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(131);
					((Decl_Context)_localctx).comma = match(COMMA);
					setState(132);
					((Decl_Context)_localctx).name2 = match(IDENTIFIER);
					setState(133);
					match(COL);
					setState(134);
					((Decl_Context)_localctx).t2 = type();

							Location parLoc1 = new Location(((PrevToken) ((Decl_Context)_localctx).comma).location(), ((Decl_Context)_localctx).t2.ast.location());
							_localctx.parDecls.add(new AstParDecl(parLoc1, ((Decl_Context)_localctx).name2.getText(), ((Decl_Context)_localctx).t2.ast));
						
					}
					}
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}

						
						((Decl_Context)_localctx).ast =  new AstTrees<AstParDecl>(new Vector<AstParDecl>(_localctx.parDecls));
					
				}
				break;
			case RIGHTBRAC:
				enterOuterAlt(_localctx, 2);
				{
				 
						
						((Decl_Context)_localctx).ast =  new AstTrees<AstParDecl>(); 
						
						
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

	public static class AtomicTypeContext extends ParserRuleContext {
		public AstAtomType ast;
		public Token aType;
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode INTEGER() { return getToken(PrevParser.INTEGER, 0); }
		public TerminalNode BOOLEAN() { return getToken(PrevParser.BOOLEAN, 0); }
		public AtomicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicType; }
	}

	public final AtomicTypeContext atomicType() throws RecognitionException {
		AtomicTypeContext _localctx = new AtomicTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atomicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			((AtomicTypeContext)_localctx).aType = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << CHAR) | (1L << INTEGER) | (1L << VOID))) != 0)) ) {
				((AtomicTypeContext)_localctx).aType = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

					
					Location loc = ((PrevToken) ((AtomicTypeContext)_localctx).aType).location();
					AstAtomType.Type tokenType = AstAtomType.Type.valueOf(((AtomicTypeContext)_localctx).aType.getText().toUpperCase());
					((AtomicTypeContext)_localctx).ast =  new AstAtomType(loc, tokenType);
					
				
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

	public static class NamedTypeContext extends ParserRuleContext {
		public AstNameType ast;
		public Token id;
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public NamedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedType; }
	}

	public final NamedTypeContext namedType() throws RecognitionException {
		NamedTypeContext _localctx = new NamedTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_namedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			((NamedTypeContext)_localctx).id = match(IDENTIFIER);

					Location loc = ((PrevToken) ((NamedTypeContext)_localctx).id).location();
					((NamedTypeContext)_localctx).ast =  new AstNameType(loc, ((NamedTypeContext)_localctx).id.getText());
				
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public AstArrType ast;
		public ExprContext expr;
		public Token rsqr;
		public AtomicTypeContext atomicType;
		public NamedTypeContext namedType;
		public RecordTypeContext recordType;
		public EnclosedTypeContext enclosedType;
		public ArrayTypeContext arrType;
		public TerminalNode LEFTSQR() { return getToken(PrevParser.LEFTSQR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AtomicTypeContext atomicType() {
			return getRuleContext(AtomicTypeContext.class,0);
		}
		public TerminalNode RIGHTSQR() { return getToken(PrevParser.RIGHTSQR, 0); }
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public EnclosedTypeContext enclosedType() {
			return getRuleContext(EnclosedTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arrayType);
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(LEFTSQR);
				setState(154);
				((ArrayTypeContext)_localctx).expr = expr(0);
				setState(155);
				((ArrayTypeContext)_localctx).rsqr = match(RIGHTSQR);
				setState(156);
				((ArrayTypeContext)_localctx).atomicType = atomicType();

						Location location = new Location(((ArrayTypeContext)_localctx).atomicType.ast.location(), ((PrevToken) ((ArrayTypeContext)_localctx).rsqr).location());
						((ArrayTypeContext)_localctx).ast =  new AstArrType(location, ((ArrayTypeContext)_localctx).atomicType.ast, ((ArrayTypeContext)_localctx).expr.ast);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(LEFTSQR);
				setState(160);
				((ArrayTypeContext)_localctx).expr = expr(0);
				setState(161);
				((ArrayTypeContext)_localctx).rsqr = match(RIGHTSQR);
				setState(162);
				((ArrayTypeContext)_localctx).namedType = namedType();

						Location location = new Location(((ArrayTypeContext)_localctx).namedType.ast.location(), ((PrevToken) ((ArrayTypeContext)_localctx).rsqr).location());
						((ArrayTypeContext)_localctx).ast =  new AstArrType(location, ((ArrayTypeContext)_localctx).namedType.ast, ((ArrayTypeContext)_localctx).expr.ast);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				match(LEFTSQR);
				setState(166);
				((ArrayTypeContext)_localctx).expr = expr(0);
				setState(167);
				((ArrayTypeContext)_localctx).rsqr = match(RIGHTSQR);
				setState(168);
				((ArrayTypeContext)_localctx).recordType = recordType();

						Location location = new Location(((ArrayTypeContext)_localctx).recordType.ast.location(), ((PrevToken) ((ArrayTypeContext)_localctx).rsqr).location());
						((ArrayTypeContext)_localctx).ast =  new AstArrType(location, ((ArrayTypeContext)_localctx).recordType.ast, ((ArrayTypeContext)_localctx).expr.ast);
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				match(LEFTSQR);
				setState(172);
				((ArrayTypeContext)_localctx).expr = expr(0);
				setState(173);
				((ArrayTypeContext)_localctx).rsqr = match(RIGHTSQR);
				setState(174);
				((ArrayTypeContext)_localctx).enclosedType = enclosedType();

						Location location = new Location(((ArrayTypeContext)_localctx).enclosedType.ast.location(), ((PrevToken) ((ArrayTypeContext)_localctx).rsqr).location());
						((ArrayTypeContext)_localctx).ast =  new AstArrType(location, ((ArrayTypeContext)_localctx).enclosedType.ast, ((ArrayTypeContext)_localctx).expr.ast);
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(177);
				match(LEFTSQR);
				setState(178);
				((ArrayTypeContext)_localctx).expr = expr(0);
				setState(179);
				((ArrayTypeContext)_localctx).rsqr = match(RIGHTSQR);
				setState(180);
				((ArrayTypeContext)_localctx).arrType = arrayType();

						Location location = new Location(((ArrayTypeContext)_localctx).arrType.ast.location(), ((PrevToken) ((ArrayTypeContext)_localctx).rsqr).location());
						((ArrayTypeContext)_localctx).ast =  new AstArrType(location, ((ArrayTypeContext)_localctx).arrType.ast, ((ArrayTypeContext)_localctx).expr.ast);
					
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

	public static class PointerTypeContext extends ParserRuleContext {
		public AstPtrType ast;
		public Token car;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode CARET() { return getToken(PrevParser.CARET, 0); }
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			((PointerTypeContext)_localctx).car = match(CARET);
			setState(186);
			((PointerTypeContext)_localctx).type = type();

					Location loc = new Location(((PrevToken) ((PointerTypeContext)_localctx).car).location(), ((PointerTypeContext)_localctx).type.ast.location());
					((PointerTypeContext)_localctx).ast =  new AstPtrType(loc, ((PointerTypeContext)_localctx).type.ast);
				
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

	public static class RecordTypeContext extends ParserRuleContext {
		public AstRecType ast;
		public Vector<AstCompDecl> allRecs = new Vector<AstCompDecl>();;
		public Token lcurl;
		public Token id;
		public TypeContext type;
		public Type_Context type_;
		public Token rcurl;
		public TerminalNode COL() { return getToken(PrevParser.COL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode LEFTCURL() { return getToken(PrevParser.LEFTCURL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode RIGHTCURL() { return getToken(PrevParser.RIGHTCURL, 0); }
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			((RecordTypeContext)_localctx).lcurl = match(LEFTCURL);
			setState(190);
			((RecordTypeContext)_localctx).id = match(IDENTIFIER);
			setState(191);
			match(COL);
			setState(192);
			((RecordTypeContext)_localctx).type = type();

					
					Location loc = new Location(((PrevToken) ((RecordTypeContext)_localctx).id).location(), ((RecordTypeContext)_localctx).type.ast.location());
					AstCompDecl comp = new AstCompDecl(loc, ((RecordTypeContext)_localctx).id.getText(), ((RecordTypeContext)_localctx).type.ast);
					_localctx.allRecs.add(comp);

				
			setState(194);
			((RecordTypeContext)_localctx).type_ = type_();

					
					
				
			setState(196);
			((RecordTypeContext)_localctx).rcurl = match(RIGHTCURL);

					
					Location recordLocation = new Location(((PrevToken) ((RecordTypeContext)_localctx).lcurl).location(), ((PrevToken) ((RecordTypeContext)_localctx).rcurl).location());
					
					
					if(((RecordTypeContext)_localctx).type_.ast != null) {
						_localctx.allRecs.addAll(((RecordTypeContext)_localctx).type_.ast);
					}
					((RecordTypeContext)_localctx).ast =  new AstRecType(recordLocation, new AstTrees<AstCompDecl>(_localctx.allRecs));
				
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

	public static class EnclosedTypeContext extends ParserRuleContext {
		public AstType ast;
		public Token lbrac;
		public TypeContext type;
		public Token rbrac;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public EnclosedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enclosedType; }
	}

	public final EnclosedTypeContext enclosedType() throws RecognitionException {
		EnclosedTypeContext _localctx = new EnclosedTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_enclosedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			((EnclosedTypeContext)_localctx).lbrac = match(LEFTBRAC);
			setState(200);
			((EnclosedTypeContext)_localctx).type = type();
			setState(201);
			((EnclosedTypeContext)_localctx).rbrac = match(RIGHTBRAC);

					Location loc = new Location(((PrevToken) ((EnclosedTypeContext)_localctx).lbrac).location(), ((PrevToken) ((EnclosedTypeContext)_localctx).rbrac).location());
					((EnclosedTypeContext)_localctx).ast =  ((EnclosedTypeContext)_localctx).type.ast;
					_localctx.ast.relocate(loc);
				
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

	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public AtomicTypeContext atomicType;
		public NamedTypeContext namedType;
		public ArrayTypeContext arrayType;
		public PointerTypeContext pointerType;
		public RecordTypeContext recordType;
		public EnclosedTypeContext enclosedType;
		public AtomicTypeContext atomicType() {
			return getRuleContext(AtomicTypeContext.class,0);
		}
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public EnclosedTypeContext enclosedType() {
			return getRuleContext(EnclosedTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case CHAR:
			case INTEGER:
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				((TypeContext)_localctx).atomicType = atomicType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).atomicType.ast; 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				((TypeContext)_localctx).namedType = namedType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).namedType.ast; 
				}
				break;
			case LEFTSQR:
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				((TypeContext)_localctx).arrayType = arrayType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).arrayType.ast; 
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				((TypeContext)_localctx).pointerType = pointerType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).pointerType.ast; 
				}
				break;
			case LEFTCURL:
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				((TypeContext)_localctx).recordType = recordType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).recordType.ast; 
				}
				break;
			case LEFTBRAC:
				enterOuterAlt(_localctx, 6);
				{
				setState(219);
				((TypeContext)_localctx).enclosedType = enclosedType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).enclosedType.ast; 
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

	public static class Type_Context extends ParserRuleContext {
		public Vector<AstCompDecl> ast;
		public Vector<AstCompDecl> moreRecords = new Vector<AstCompDecl>();;
		public Token comma;
		public Token id;
		public TypeContext type;
		public List<TerminalNode> COL() { return getTokens(PrevParser.COL); }
		public TerminalNode COL(int i) {
			return getToken(PrevParser.COL, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_type_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(224);
				((Type_Context)_localctx).comma = match(COMMA);
				setState(225);
				((Type_Context)_localctx).id = match(IDENTIFIER);
				setState(226);
				match(COL);
				setState(227);
				((Type_Context)_localctx).type = type();

						Location loc = new Location(((PrevToken) ((Type_Context)_localctx).comma).location(), ((Type_Context)_localctx).type.ast.location());
						AstCompDecl comp = new AstCompDecl(loc, ((Type_Context)_localctx).id	.getText(), ((Type_Context)_localctx).type.ast);
						_localctx.moreRecords.add(comp);
						((Type_Context)_localctx).ast =  _localctx.moreRecords;
					
				}
				}
				setState(234);
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

	public static class ExprContext extends ParserRuleContext {
		public AstExpr ast;
		public Vector<AstExpr> allExpr = new Vector<AstExpr>();;
		public ExprContext e1;
		public Stmt_Context stmt_;
		public AContext a;
		public OrexprContext orexpr;
		public WhereexprContext whereexpr;
		public Stmt_Context stmt_() {
			return getRuleContext(Stmt_Context.class,0);
		}
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(PrevParser.WHERE, 0); }
		public WhereexprContext whereexpr() {
			return getRuleContext(WhereexprContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
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
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFTCURL:
				{
				setState(236);
				((ExprContext)_localctx).stmt_ = stmt_();

						((ExprContext)_localctx).ast =  ((ExprContext)_localctx).stmt_.ast;
					
				}
				break;
			case NONE:
			case TRUE:
			case FALSE:
			case INTCONST:
			case CHARACTERCONST:
			case STRINGCONST:
			case POINTER:
			case LEFTBRAC:
			case EXCL:
			case PLUS:
			case MINUS:
			case CARET:
			case DEL:
			case NEW:
			case IDENTIFIER:
				{
				setState(239);
				((ExprContext)_localctx).a = a();

						_localctx.allExpr.add(((ExprContext)_localctx).a.ast);
					
				setState(241);
				((ExprContext)_localctx).orexpr = orexpr(((ExprContext)_localctx).a.ast);

						
						((ExprContext)_localctx).ast =  ((ExprContext)_localctx).orexpr.ast;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(253);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(246);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(247);
					match(WHERE);
					setState(248);
					((ExprContext)_localctx).whereexpr = whereexpr();

					          		Location whereLoc = new Location( ((ExprContext)_localctx).e1.ast.location(), ((ExprContext)_localctx).whereexpr.ast.location());
					          		((ExprContext)_localctx).ast =  new AstWhereExpr(whereLoc, ((ExprContext)_localctx).e1.ast, ((ExprContext)_localctx).whereexpr.ast);

					          	
					}
					} 
				}
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class OrexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AstExpr subTree;
		public Vector<AstExpr> allExpr = new Vector<AstExpr>();;
		public AContext a;
		public OrexprContext orexpr;
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public OrexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public OrexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_orexpr; }
	}

	public final OrexprContext orexpr(AstExpr leftTree) throws RecognitionException {
		OrexprContext _localctx = new OrexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 30, RULE_orexpr);
		try {
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(OR);
				setState(257);
				((OrexprContext)_localctx).a = a();

						Location location = new Location(_localctx.leftTree.location(), ((OrexprContext)_localctx).a.ast.location());
						((OrexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.OR, _localctx.leftTree, ((OrexprContext)_localctx).a.ast);
						_localctx.allExpr.add(_localctx.subTree);
					
				setState(259);
				((OrexprContext)_localctx).orexpr = orexpr(_localctx.subTree);

						
						((OrexprContext)_localctx).ast =  ((OrexprContext)_localctx).orexpr.ast;
						
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

						((OrexprContext)_localctx).ast = _localctx.leftTree;
					
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

	public static class AContext extends ParserRuleContext {
		public AstExpr ast;
		public BContext b;
		public AndexprContext andexpr;
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public AndexprContext andexpr() {
			return getRuleContext(AndexprContext.class,0);
		}
		public AContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a; }
	}

	public final AContext a() throws RecognitionException {
		AContext _localctx = new AContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_a);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			((AContext)_localctx).b = b();
			setState(266);
			((AContext)_localctx).andexpr = andexpr(((AContext)_localctx).b.ast);

					((AContext)_localctx).ast =  ((AContext)_localctx).andexpr.ast;
				
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

	public static class AndexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AstExpr subTree;
		public Vector<AstExpr> allExpr = new Vector<AstExpr>();;
		public BContext b;
		public AndexprContext andexpr;
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public AndexprContext andexpr() {
			return getRuleContext(AndexprContext.class,0);
		}
		public AndexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AndexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_andexpr; }
	}

	public final AndexprContext andexpr(AstExpr leftTree) throws RecognitionException {
		AndexprContext _localctx = new AndexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 34, RULE_andexpr);
		try {
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				match(AND);
				setState(270);
				((AndexprContext)_localctx).b = b();

						Location location = new Location(_localctx.leftTree.location(), ((AndexprContext)_localctx).b.ast.location());
						((AndexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.AND, _localctx.leftTree, ((AndexprContext)_localctx).b.ast);
						_localctx.allExpr.add(_localctx.subTree);
					
				setState(272);
				((AndexprContext)_localctx).andexpr = andexpr(_localctx.subTree);

						((AndexprContext)_localctx).ast =  ((AndexprContext)_localctx).andexpr.ast;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

						((AndexprContext)_localctx).ast = _localctx.leftTree;
					
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

	public static class BContext extends ParserRuleContext {
		public AstExpr ast;
		public CContext c;
		public RelationalexprContext relationalexpr;
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public RelationalexprContext relationalexpr() {
			return getRuleContext(RelationalexprContext.class,0);
		}
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_b);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			((BContext)_localctx).c = c();
			setState(279);
			((BContext)_localctx).relationalexpr = relationalexpr(((BContext)_localctx).c.ast);

					((BContext)_localctx).ast =  ((BContext)_localctx).relationalexpr.ast;
				
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

	public static class RelationalexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public CContext c;
		public TerminalNode EQL() { return getToken(PrevParser.EQL, 0); }
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public TerminalNode NEQ() { return getToken(PrevParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(PrevParser.LT, 0); }
		public TerminalNode GT() { return getToken(PrevParser.GT, 0); }
		public TerminalNode LTE() { return getToken(PrevParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(PrevParser.GTE, 0); }
		public RelationalexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RelationalexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_relationalexpr; }
	}

	public final RelationalexprContext relationalexpr(AstExpr leftTree) throws RecognitionException {
		RelationalexprContext _localctx = new RelationalexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 38, RULE_relationalexpr);
		try {
			setState(307);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				match(EQL);
				setState(283);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.EQU, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(286);
				match(NEQ);
				setState(287);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.NEQ, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
				match(LT);
				setState(291);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.LTH, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(294);
				match(GT);
				setState(295);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.GTH, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(298);
				match(LTE);
				setState(299);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.LEQ, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(302);
				match(GTE);
				setState(303);
				((RelationalexprContext)_localctx).c = c();
				 
						Location location = new Location(_localctx.leftTree.location(), ((RelationalexprContext)_localctx).c.ast.location());
						((RelationalexprContext)_localctx).ast =  new AstBinExpr(location, AstBinExpr.Oper.GEQ, _localctx.leftTree, ((RelationalexprContext)_localctx).c.ast);
					
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{

						((RelationalexprContext)_localctx).ast = _localctx.leftTree;
					
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

	public static class CContext extends ParserRuleContext {
		public AstExpr ast;
		public DContext d;
		public C1Context c1;
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public C1Context c1() {
			return getRuleContext(C1Context.class,0);
		}
		public CContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c; }
	}

	public final CContext c() throws RecognitionException {
		CContext _localctx = new CContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_c);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			((CContext)_localctx).d = d();
			setState(310);
			((CContext)_localctx).c1 = c1(((CContext)_localctx).d.ast);

					((CContext)_localctx).ast =  ((CContext)_localctx).c1.ast;
				
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

	public static class C1Context extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AditiveexprContext aditiveexpr;
		public AditiveexprContext aditiveexpr() {
			return getRuleContext(AditiveexprContext.class,0);
		}
		public C1Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public C1Context(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_c1; }
	}

	public final C1Context c1(AstExpr leftTree) throws RecognitionException {
		C1Context _localctx = new C1Context(_ctx, getState(), leftTree);
		enterRule(_localctx, 42, RULE_c1);
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				((C1Context)_localctx).aditiveexpr = aditiveexpr(_localctx.leftTree);

						((C1Context)_localctx).ast =  ((C1Context)_localctx).aditiveexpr.ast;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

						((C1Context)_localctx).ast = _localctx.leftTree;
					
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

	public static class AditiveexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AstExpr subTree;
		public DContext d;
		public C1Context c1;
		public TerminalNode PLUS() { return getToken(PrevParser.PLUS, 0); }
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public C1Context c1() {
			return getRuleContext(C1Context.class,0);
		}
		public TerminalNode MINUS() { return getToken(PrevParser.MINUS, 0); }
		public AditiveexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AditiveexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_aditiveexpr; }
	}

	public final AditiveexprContext aditiveexpr(AstExpr leftTree) throws RecognitionException {
		AditiveexprContext _localctx = new AditiveexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 44, RULE_aditiveexpr);
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(PLUS);
				setState(320);
				((AditiveexprContext)_localctx).d = d();
				 
						Location location = new Location(_localctx.leftTree.location(), ((AditiveexprContext)_localctx).d.ast.location());
						((AditiveexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.ADD, _localctx.leftTree, ((AditiveexprContext)_localctx).d.ast);
					
				setState(322);
				((AditiveexprContext)_localctx).c1 = c1(_localctx.subTree);

						((AditiveexprContext)_localctx).ast =  ((AditiveexprContext)_localctx).c1.ast;
					
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(MINUS);
				setState(326);
				((AditiveexprContext)_localctx).d = d();
				 
						Location location = new Location(_localctx.leftTree.location(), ((AditiveexprContext)_localctx).d.ast.location());
						((AditiveexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.SUB, _localctx.leftTree, ((AditiveexprContext)_localctx).d.ast);
					
				setState(328);
				((AditiveexprContext)_localctx).c1 = c1(_localctx.subTree);

						((AditiveexprContext)_localctx).ast =  ((AditiveexprContext)_localctx).c1.ast;
					
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

	public static class DContext extends ParserRuleContext {
		public AstExpr ast;
		public PrefixexprContext prefixexpr;
		public MultiplicativeexprContext multiplicativeexpr;
		public PrefixexprContext prefixexpr() {
			return getRuleContext(PrefixexprContext.class,0);
		}
		public MultiplicativeexprContext multiplicativeexpr() {
			return getRuleContext(MultiplicativeexprContext.class,0);
		}
		public DContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d; }
	}

	public final DContext d() throws RecognitionException {
		DContext _localctx = new DContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_d);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			((DContext)_localctx).prefixexpr = prefixexpr();
			setState(334);
			((DContext)_localctx).multiplicativeexpr = multiplicativeexpr(((DContext)_localctx).prefixexpr.ast);

					((DContext)_localctx).ast =  ((DContext)_localctx).multiplicativeexpr.ast;
				
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

	public static class MultiplicativeexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AstExpr subTree;
		public PrefixexprContext prefixexpr;
		public MultiplicativeexprContext multiplicativeexpr;
		public TerminalNode STAR() { return getToken(PrevParser.STAR, 0); }
		public PrefixexprContext prefixexpr() {
			return getRuleContext(PrefixexprContext.class,0);
		}
		public MultiplicativeexprContext multiplicativeexpr() {
			return getRuleContext(MultiplicativeexprContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(PrevParser.SLASH, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public MultiplicativeexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MultiplicativeexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeexpr; }
	}

	public final MultiplicativeexprContext multiplicativeexpr(AstExpr leftTree) throws RecognitionException {
		MultiplicativeexprContext _localctx = new MultiplicativeexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 48, RULE_multiplicativeexpr);
		try {
			setState(356);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(337);
				match(STAR);
				setState(338);
				((MultiplicativeexprContext)_localctx).prefixexpr = prefixexpr();
				 
						Location location = new Location(_localctx.leftTree.location(), ((MultiplicativeexprContext)_localctx).prefixexpr.ast.location());
						((MultiplicativeexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.MUL, _localctx.leftTree, ((MultiplicativeexprContext)_localctx).prefixexpr.ast);
					
				setState(340);
				((MultiplicativeexprContext)_localctx).multiplicativeexpr = multiplicativeexpr(_localctx.subTree);

						((MultiplicativeexprContext)_localctx).ast =  ((MultiplicativeexprContext)_localctx).multiplicativeexpr.ast;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				match(SLASH);
				setState(344);
				((MultiplicativeexprContext)_localctx).prefixexpr = prefixexpr();
				 
						Location location = new Location(_localctx.leftTree.location(), ((MultiplicativeexprContext)_localctx).prefixexpr.ast.location());
						((MultiplicativeexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.DIV, _localctx.leftTree, ((MultiplicativeexprContext)_localctx).prefixexpr.ast);
					
				setState(346);
				((MultiplicativeexprContext)_localctx).multiplicativeexpr = multiplicativeexpr(_localctx.subTree);

						((MultiplicativeexprContext)_localctx).ast =  ((MultiplicativeexprContext)_localctx).multiplicativeexpr.ast;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(349);
				match(MOD);
				setState(350);
				((MultiplicativeexprContext)_localctx).prefixexpr = prefixexpr();
				 
						Location location = new Location(_localctx.leftTree.location(), ((MultiplicativeexprContext)_localctx).prefixexpr.ast.location());
						((MultiplicativeexprContext)_localctx).subTree =  new AstBinExpr(location, AstBinExpr.Oper.MOD, _localctx.leftTree, ((MultiplicativeexprContext)_localctx).prefixexpr.ast);
					
				setState(352);
				((MultiplicativeexprContext)_localctx).multiplicativeexpr = multiplicativeexpr(_localctx.subTree);

						((MultiplicativeexprContext)_localctx).ast =  ((MultiplicativeexprContext)_localctx).multiplicativeexpr.ast;
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{

						((MultiplicativeexprContext)_localctx).ast = _localctx.leftTree;
					
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

	public static class PrefixexprContext extends ParserRuleContext {
		public AstExpr ast;
		public FContext f;
		public Token zac;
		public PrefixexprContext prefixexpr;
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public PrefixexprContext prefixexpr() {
			return getRuleContext(PrefixexprContext.class,0);
		}
		public TerminalNode EXCL() { return getToken(PrevParser.EXCL, 0); }
		public TerminalNode PLUS() { return getToken(PrevParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PrevParser.MINUS, 0); }
		public TerminalNode CARET() { return getToken(PrevParser.CARET, 0); }
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public PrefixexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixexpr; }
	}

	public final PrefixexprContext prefixexpr() throws RecognitionException {
		PrefixexprContext _localctx = new PrefixexprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_prefixexpr);
		try {
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
			case TRUE:
			case FALSE:
			case INTCONST:
			case CHARACTERCONST:
			case STRINGCONST:
			case POINTER:
			case LEFTBRAC:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				((PrefixexprContext)_localctx).f = f();

						
						((PrefixexprContext)_localctx).ast =  ((PrefixexprContext)_localctx).f.ast;
					
				}
				break;
			case EXCL:
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				((PrefixexprContext)_localctx).zac = match(EXCL);
				setState(362);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						
						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.NOT, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				((PrefixexprContext)_localctx).zac = match(PLUS);
				setState(366);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.ADD, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 4);
				{
				setState(369);
				((PrefixexprContext)_localctx).zac = match(MINUS);
				setState(370);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.SUB, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 5);
				{
				setState(373);
				((PrefixexprContext)_localctx).zac = match(CARET);
				setState(374);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.PTR, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 6);
				{
				setState(377);
				((PrefixexprContext)_localctx).zac = match(NEW);
				setState(378);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.NEW, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
				}
				break;
			case DEL:
				enterOuterAlt(_localctx, 7);
				{
				setState(381);
				((PrefixexprContext)_localctx).zac = match(DEL);
				setState(382);
				((PrefixexprContext)_localctx).prefixexpr = prefixexpr();

						Location location = new Location(((PrevToken) ((PrefixexprContext)_localctx).zac).location(), ((PrefixexprContext)_localctx).prefixexpr.ast.location());
						((PrefixexprContext)_localctx).ast =  new AstPfxExpr(location, AstPfxExpr.Oper.DEL, ((PrefixexprContext)_localctx).prefixexpr.ast);
					
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

	public static class FContext extends ParserRuleContext {
		public AstExpr ast;
		public PrimaryexprContext primaryexpr;
		public PostfixexprContext postfixexpr;
		public PrimaryexprContext primaryexpr() {
			return getRuleContext(PrimaryexprContext.class,0);
		}
		public PostfixexprContext postfixexpr() {
			return getRuleContext(PostfixexprContext.class,0);
		}
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
	}

	public final FContext f() throws RecognitionException {
		FContext _localctx = new FContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			((FContext)_localctx).primaryexpr = primaryexpr();
			setState(388);
			((FContext)_localctx).postfixexpr = postfixexpr(((FContext)_localctx).primaryexpr.ast);

					((FContext)_localctx).ast =  ((FContext)_localctx).postfixexpr.ast;
				
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

	public static class PostfixexprContext extends ParserRuleContext {
		public AstExpr leftTree;
		public AstExpr ast;
		public AstExpr subTree;
		public Expr5Context expr5;
		public Token end;
		public PostfixexprContext postfixexpr;
		public Token name;
		public TerminalNode LEFTSQR() { return getToken(PrevParser.LEFTSQR, 0); }
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public PostfixexprContext postfixexpr() {
			return getRuleContext(PostfixexprContext.class,0);
		}
		public TerminalNode RIGHTSQR() { return getToken(PrevParser.RIGHTSQR, 0); }
		public TerminalNode CARET() { return getToken(PrevParser.CARET, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public PostfixexprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PostfixexprContext(ParserRuleContext parent, int invokingState, AstExpr leftTree) {
			super(parent, invokingState);
			this.leftTree = leftTree;
		}
		@Override public int getRuleIndex() { return RULE_postfixexpr; }
	}

	public final PostfixexprContext postfixexpr(AstExpr leftTree) throws RecognitionException {
		PostfixexprContext _localctx = new PostfixexprContext(_ctx, getState(), leftTree);
		enterRule(_localctx, 54, RULE_postfixexpr);
		try {
			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(LEFTSQR);
				setState(392);
				((PostfixexprContext)_localctx).expr5 = expr5();
				setState(393);
				((PostfixexprContext)_localctx).end = match(RIGHTSQR);

						Location loc = new Location(_localctx.leftTree.location(), ((PrevToken) ((PostfixexprContext)_localctx).end).location());
						((PostfixexprContext)_localctx).subTree =  new AstArrExpr(loc, _localctx.leftTree, ((PostfixexprContext)_localctx).expr5.ast);
					
				setState(395);
				((PostfixexprContext)_localctx).postfixexpr = postfixexpr(_localctx.subTree);

						((PostfixexprContext)_localctx).ast =  ((PostfixexprContext)_localctx).postfixexpr.ast;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(398);
				((PostfixexprContext)_localctx).end = match(CARET);

						Location loc = new Location(_localctx.leftTree.location(), ((PrevToken) ((PostfixexprContext)_localctx).end).location());
						((PostfixexprContext)_localctx).subTree =  new AstSfxExpr(loc, AstSfxExpr.Oper.PTR, _localctx.leftTree);
					
				setState(400);
				((PostfixexprContext)_localctx).postfixexpr = postfixexpr(_localctx.subTree);

						((PostfixexprContext)_localctx).ast =  ((PostfixexprContext)_localctx).postfixexpr.ast;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(403);
				match(DOT);
				setState(404);
				((PostfixexprContext)_localctx).name = match(IDENTIFIER);

						Location loc = new Location(_localctx.leftTree.location(), ((PrevToken) ((PostfixexprContext)_localctx).name).location());
						AstNameExpr nameExpression = new AstNameExpr(((PrevToken) ((PostfixexprContext)_localctx).name).location(), ((PostfixexprContext)_localctx).name.getText());
						((PostfixexprContext)_localctx).subTree =  new AstRecExpr(loc, _localctx.leftTree, nameExpression);
					
				setState(406);
				((PostfixexprContext)_localctx).postfixexpr = postfixexpr(_localctx.subTree);

						((PostfixexprContext)_localctx).ast =  ((PostfixexprContext)_localctx).postfixexpr.ast;
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{

						((PostfixexprContext)_localctx).ast =  _localctx.leftTree;
					
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

	public static class PrimaryexprContext extends ParserRuleContext {
		public AstExpr ast;
		public CnstContext cnst;
		public IdExprContext idExpr;
		public FuncCallContext funcCall;
		public BracExprContext bracExpr;
		public CastExprContext castExpr;
		public CnstContext cnst() {
			return getRuleContext(CnstContext.class,0);
		}
		public IdExprContext idExpr() {
			return getRuleContext(IdExprContext.class,0);
		}
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public BracExprContext bracExpr() {
			return getRuleContext(BracExprContext.class,0);
		}
		public CastExprContext castExpr() {
			return getRuleContext(CastExprContext.class,0);
		}
		public PrimaryexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryexpr; }
	}

	public final PrimaryexprContext primaryexpr() throws RecognitionException {
		PrimaryexprContext _localctx = new PrimaryexprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_primaryexpr);
		try {
			setState(427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(412);
				((PrimaryexprContext)_localctx).cnst = cnst();

						((PrimaryexprContext)_localctx).ast =  ((PrimaryexprContext)_localctx).cnst.ast;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(415);
				((PrimaryexprContext)_localctx).idExpr = idExpr();
				 ((PrimaryexprContext)_localctx).ast =  ((PrimaryexprContext)_localctx).idExpr.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(418);
				((PrimaryexprContext)_localctx).funcCall = funcCall();
				 ((PrimaryexprContext)_localctx).ast =  ((PrimaryexprContext)_localctx).funcCall.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(421);
				((PrimaryexprContext)_localctx).bracExpr = bracExpr();
				 ((PrimaryexprContext)_localctx).ast =  ((PrimaryexprContext)_localctx).bracExpr.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(424);
				((PrimaryexprContext)_localctx).castExpr = castExpr();
				 ((PrimaryexprContext)_localctx).ast =  ((PrimaryexprContext)_localctx).castExpr.ast; 
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

	public static class IdExprContext extends ParserRuleContext {
		public AstExpr ast;
		public Token id;
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public IdExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idExpr; }
	}

	public final IdExprContext idExpr() throws RecognitionException {
		IdExprContext _localctx = new IdExprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_idExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			((IdExprContext)_localctx).id = match(IDENTIFIER);

					((IdExprContext)_localctx).ast =  new AstNameExpr(((PrevToken) ((IdExprContext)_localctx).id).location(), ((IdExprContext)_localctx).id.getText());
				
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

	public static class FuncCallContext extends ParserRuleContext {
		public AstExpr ast;
		public Token name;
		public ArgsContext args;
		public Token rbrac;
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			((FuncCallContext)_localctx).name = match(IDENTIFIER);
			setState(433);
			match(LEFTBRAC);
			setState(434);
			((FuncCallContext)_localctx).args = args();
			setState(435);
			((FuncCallContext)_localctx).rbrac = match(RIGHTBRAC);

					Location loc = new Location(((PrevToken) ((FuncCallContext)_localctx).name).location(), ((PrevToken) ((FuncCallContext)_localctx).rbrac).location());
					((FuncCallContext)_localctx).ast =  new AstCallExpr(loc, ((FuncCallContext)_localctx).name.getText(), ((FuncCallContext)_localctx).args.ast);
				
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

	public static class ArgsContext extends ParserRuleContext {
		public AstTrees<AstExpr> ast;
		public Vector<AstExpr> allExpr = new Vector<AstExpr>();
		public ExprContext expr;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_args);
		int _la;
		try {
			setState(450);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
			case TRUE:
			case FALSE:
			case INTCONST:
			case CHARACTERCONST:
			case STRINGCONST:
			case POINTER:
			case LEFTBRAC:
			case LEFTCURL:
			case EXCL:
			case PLUS:
			case MINUS:
			case CARET:
			case DEL:
			case NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				((ArgsContext)_localctx).expr = expr(0);

						_localctx.allExpr.add(((ArgsContext)_localctx).expr.ast);
						((ArgsContext)_localctx).ast =  new AstTrees<AstExpr>(_localctx.allExpr);
					
				setState(446);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(440);
					match(COMMA);
					setState(441);
					((ArgsContext)_localctx).expr = expr(0);

							_localctx.allExpr.add(((ArgsContext)_localctx).expr.ast);
							((ArgsContext)_localctx).ast =  new AstTrees<AstExpr>(_localctx.allExpr);
						
					}
					}
					setState(448);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RIGHTBRAC:
				enterOuterAlt(_localctx, 2);
				{
				 ((ArgsContext)_localctx).ast =  null; 
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

	public static class BracExprContext extends ParserRuleContext {
		public AstExpr ast;
		public ExprContext expr;
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public BracExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracExpr; }
	}

	public final BracExprContext bracExpr() throws RecognitionException {
		BracExprContext _localctx = new BracExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_bracExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			match(LEFTBRAC);
			setState(453);
			((BracExprContext)_localctx).expr = expr(0);
			setState(454);
			match(RIGHTBRAC);

					((BracExprContext)_localctx).ast =  ((BracExprContext)_localctx).expr.ast;
				
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

	public static class CastExprContext extends ParserRuleContext {
		public AstExpr ast;
		public Token lbrac;
		public ExprContext expr;
		public TypeContext type;
		public Token rbrac;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COL() { return getToken(PrevParser.COL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public CastExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpr; }
	}

	public final CastExprContext castExpr() throws RecognitionException {
		CastExprContext _localctx = new CastExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_castExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			((CastExprContext)_localctx).lbrac = match(LEFTBRAC);
			setState(458);
			((CastExprContext)_localctx).expr = expr(0);
			setState(459);
			match(COL);
			setState(460);
			((CastExprContext)_localctx).type = type();
			setState(461);
			((CastExprContext)_localctx).rbrac = match(RIGHTBRAC);

					Location loc = new Location(((PrevToken) ((CastExprContext)_localctx).lbrac).location(), ((PrevToken) ((CastExprContext)_localctx).rbrac).location());
					((CastExprContext)_localctx).ast =  new AstCastExpr(loc, ((CastExprContext)_localctx).expr.ast, ((CastExprContext)_localctx).type.ast);
				
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

	public static class Expr1Context extends ParserRuleContext {
		public TerminalNode LEFTBRAC() { return getToken(PrevParser.LEFTBRAC, 0); }
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public Expr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
	}

	public final Expr1Context expr1() throws RecognitionException {
		Expr1Context _localctx = new Expr1Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_expr1);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFTBRAC:
				enterOuterAlt(_localctx, 1);
				{
				setState(464);
				match(LEFTBRAC);
				setState(465);
				expr3();
				setState(466);
				match(RIGHTBRAC);
				}
				break;
			case EOF:
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

	public static class Expr3Context extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public TerminalNode COMMA() { return getToken(PrevParser.COMMA, 0); }
		public Expr3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3; }
	}

	public final Expr3Context expr3() throws RecognitionException {
		Expr3Context _localctx = new Expr3Context(_ctx, getState());
		enterRule(_localctx, 70, RULE_expr3);
		try {
			setState(479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
			case TRUE:
			case FALSE:
			case INTCONST:
			case CHARACTERCONST:
			case STRINGCONST:
			case POINTER:
			case LEFTBRAC:
			case LEFTCURL:
			case EXCL:
			case PLUS:
			case MINUS:
			case CARET:
			case DEL:
			case NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(471);
				expr(0);
				setState(472);
				expr3();
				}
				break;
			case COMMA:
				enterOuterAlt(_localctx, 2);
				{
				setState(474);
				match(COMMA);
				setState(475);
				expr(0);
				setState(476);
				expr3();
				}
				break;
			case RIGHTBRAC:
				enterOuterAlt(_localctx, 3);
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

	public static class Expr4Context extends ParserRuleContext {
		public TerminalNode COL() { return getToken(PrevParser.COL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RIGHTBRAC() { return getToken(PrevParser.RIGHTBRAC, 0); }
		public Expr4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr4; }
	}

	public final Expr4Context expr4() throws RecognitionException {
		Expr4Context _localctx = new Expr4Context(_ctx, getState());
		enterRule(_localctx, 72, RULE_expr4);
		try {
			setState(486);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COL:
				enterOuterAlt(_localctx, 1);
				{
				setState(481);
				match(COL);
				setState(482);
				type();
				setState(483);
				match(RIGHTBRAC);
				}
				break;
			case RIGHTBRAC:
				enterOuterAlt(_localctx, 2);
				{
				setState(485);
				match(RIGHTBRAC);
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

	public static class Expr5Context extends ParserRuleContext {
		public AstExpr ast;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr5; }
	}

	public final Expr5Context expr5() throws RecognitionException {
		Expr5Context _localctx = new Expr5Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_expr5);
		try {
			setState(492);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
			case TRUE:
			case FALSE:
			case INTCONST:
			case CHARACTERCONST:
			case STRINGCONST:
			case POINTER:
			case LEFTBRAC:
			case LEFTCURL:
			case EXCL:
			case PLUS:
			case MINUS:
			case CARET:
			case DEL:
			case NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(488);
				((Expr5Context)_localctx).expr = expr(0);

						((Expr5Context)_localctx).ast =  ((Expr5Context)_localctx).expr.ast;
					
				}
				break;
			case RIGHTSQR:
				enterOuterAlt(_localctx, 2);
				{

						((Expr5Context)_localctx).ast =  null;
					
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

	public static class WhereexprContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstDecl> dcls = new Vector<AstDecl>();;
		public Token lcurl;
		public DeclContext decl;
		public TerminalNode RIGHTCURL() { return getToken(PrevParser.RIGHTCURL, 0); }
		public TerminalNode LEFTCURL() { return getToken(PrevParser.LEFTCURL, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public WhereexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereexpr; }
	}

	public final WhereexprContext whereexpr() throws RecognitionException {
		WhereexprContext _localctx = new WhereexprContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_whereexpr);
		int _la;
		try {
			setState(505);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(494);
				((WhereexprContext)_localctx).lcurl = match(LEFTCURL);
				setState(498); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(495);
					((WhereexprContext)_localctx).decl = decl();

							_localctx.dcls.add(((WhereexprContext)_localctx).decl.ast);
							((WhereexprContext)_localctx).ast =  new AstTrees<AstDecl>(_localctx.dcls);
						
					}
					}
					setState(500); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUNCTION) | (1L << TYPE) | (1L << VARIABLE))) != 0) );
				setState(502);
				match(RIGHTCURL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

						((WhereexprContext)_localctx).ast = null;
					
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

	public static class CnstContext extends ParserRuleContext {
		public AstAtomExpr ast;
		public Token truecnst;
		public Token falsecnst;
		public Token intcnst;
		public Token charcnst;
		public Token stringcnst;
		public Token nonecnst;
		public Token ponitercnst;
		public TerminalNode TRUE() { return getToken(PrevParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PrevParser.FALSE, 0); }
		public TerminalNode INTCONST() { return getToken(PrevParser.INTCONST, 0); }
		public TerminalNode CHARACTERCONST() { return getToken(PrevParser.CHARACTERCONST, 0); }
		public TerminalNode STRINGCONST() { return getToken(PrevParser.STRINGCONST, 0); }
		public TerminalNode NONE() { return getToken(PrevParser.NONE, 0); }
		public TerminalNode POINTER() { return getToken(PrevParser.POINTER, 0); }
		public CnstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cnst; }
	}

	public final CnstContext cnst() throws RecognitionException {
		CnstContext _localctx = new CnstContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_cnst);
		try {
			setState(521);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(507);
				((CnstContext)_localctx).truecnst = match(TRUE);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).truecnst).location(), AstAtomExpr.Type.BOOLEAN, ((CnstContext)_localctx).truecnst.getText()); 
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(509);
				((CnstContext)_localctx).falsecnst = match(FALSE);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).falsecnst).location(), AstAtomExpr.Type.BOOLEAN, ((CnstContext)_localctx).falsecnst.getText()); 
				}
				break;
			case INTCONST:
				enterOuterAlt(_localctx, 3);
				{
				setState(511);
				((CnstContext)_localctx).intcnst = match(INTCONST);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).intcnst).location(), AstAtomExpr.Type.INTEGER, ((CnstContext)_localctx).intcnst.getText()); 
				}
				break;
			case CHARACTERCONST:
				enterOuterAlt(_localctx, 4);
				{
				setState(513);
				((CnstContext)_localctx).charcnst = match(CHARACTERCONST);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).charcnst).location(), AstAtomExpr.Type.CHAR, ((CnstContext)_localctx).charcnst.getText()); 
				}
				break;
			case STRINGCONST:
				enterOuterAlt(_localctx, 5);
				{
				setState(515);
				((CnstContext)_localctx).stringcnst = match(STRINGCONST);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).stringcnst).location(), AstAtomExpr.Type.STRING, ((CnstContext)_localctx).stringcnst.getText()); 
				}
				break;
			case NONE:
				enterOuterAlt(_localctx, 6);
				{
				setState(517);
				((CnstContext)_localctx).nonecnst = match(NONE);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).nonecnst).location(), AstAtomExpr.Type.VOID, ((CnstContext)_localctx).nonecnst.getText()); 
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 7);
				{
				setState(519);
				((CnstContext)_localctx).ponitercnst = match(POINTER);
				 ((CnstContext)_localctx).ast =  new AstAtomExpr(((PrevToken) ((CnstContext)_localctx).ponitercnst).location(), AstAtomExpr.Type.POINTER, ((CnstContext)_localctx).ponitercnst.getText()); 
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

	public static class StmtContext extends ParserRuleContext {
		public AstStmt ast;
		public Token ifword;
		public ExprContext expr;
		public StmtContext thenstmt;
		public StmtContext elsestmt;
		public Token whileword;
		public StmtContext stmt;
		public ExprContext firstexpr;
		public ExprContext secondexpr;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(PrevParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(PrevParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(PrevParser.IF, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode DO() { return getToken(PrevParser.DO, 0); }
		public TerminalNode WHILE() { return getToken(PrevParser.WHILE, 0); }
		public TerminalNode ASIGN() { return getToken(PrevParser.ASIGN, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_stmt);
		try {
			setState(545);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(523);
				((StmtContext)_localctx).ifword = match(IF);
				setState(524);
				((StmtContext)_localctx).expr = expr(0);
				setState(525);
				match(THEN);
				setState(526);
				((StmtContext)_localctx).thenstmt = stmt();
				setState(527);
				match(ELSE);
				setState(528);
				((StmtContext)_localctx).elsestmt = stmt();

						Location loc = new Location(((PrevToken) ((StmtContext)_localctx).ifword).location(), ((StmtContext)_localctx).elsestmt.ast.location());
						((StmtContext)_localctx).ast =  new AstIfStmt(loc, ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).thenstmt.ast, ((StmtContext)_localctx).elsestmt.ast);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(531);
				((StmtContext)_localctx).whileword = match(WHILE);
				setState(532);
				((StmtContext)_localctx).expr = expr(0);
				setState(533);
				match(DO);
				setState(534);
				((StmtContext)_localctx).stmt = stmt();

						Location loc = new Location(((PrevToken) ((StmtContext)_localctx).whileword).location(), ((StmtContext)_localctx).stmt.ast.location());
						((StmtContext)_localctx).ast =  new AstWhileStmt(loc, ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).stmt.ast);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(537);
				((StmtContext)_localctx).expr = expr(0);
				 ((StmtContext)_localctx).ast =  new AstExprStmt(((StmtContext)_localctx).expr.ast.location(), ((StmtContext)_localctx).expr.ast); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(540);
				((StmtContext)_localctx).firstexpr = expr(0);
				setState(541);
				match(ASIGN);
				setState(542);
				((StmtContext)_localctx).secondexpr = expr(0);

						Location location = new Location(((StmtContext)_localctx).firstexpr.ast.location(), ((StmtContext)_localctx).secondexpr.ast.location());
						((StmtContext)_localctx).ast =  new AstAssignStmt(location, ((StmtContext)_localctx).firstexpr.ast, ((StmtContext)_localctx).secondexpr.ast);
					
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

	public static class Stmt_Context extends ParserRuleContext {
		public AstExpr ast;
		public Vector<AstStmt> allStatements = new Vector<AstStmt>();;
		public Token lsqr;
		public StmtContext stmt;
		public Token rsqr;
		public TerminalNode LEFTCURL() { return getToken(PrevParser.LEFTCURL, 0); }
		public TerminalNode RIGHTCURL() { return getToken(PrevParser.RIGHTCURL, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> SEMCOL() { return getTokens(PrevParser.SEMCOL); }
		public TerminalNode SEMCOL(int i) {
			return getToken(PrevParser.SEMCOL, i);
		}
		public Stmt_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_; }
	}

	public final Stmt_Context stmt_() throws RecognitionException {
		Stmt_Context _localctx = new Stmt_Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_stmt_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			((Stmt_Context)_localctx).lsqr = match(LEFTCURL);
			setState(552); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(548);
				((Stmt_Context)_localctx).stmt = stmt();

						_localctx.allStatements.add(((Stmt_Context)_localctx).stmt.ast);
					
				setState(550);
				match(SEMCOL);
				}
				}
				setState(554); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NONE) | (1L << TRUE) | (1L << FALSE) | (1L << INTCONST) | (1L << CHARACTERCONST) | (1L << STRINGCONST) | (1L << POINTER) | (1L << LEFTBRAC) | (1L << LEFTCURL) | (1L << EXCL) | (1L << PLUS) | (1L << MINUS) | (1L << CARET) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << IDENTIFIER))) != 0) );
			setState(556);
			((Stmt_Context)_localctx).rsqr = match(RIGHTCURL);

					Location loc = new Location(((PrevToken) ((Stmt_Context)_localctx).lsqr).location(), ((PrevToken) ((Stmt_Context)_localctx).rsqr).location());
					AstTrees<AstStmt> parsedStatements = new AstTrees<AstStmt>(_localctx.allStatements);
					((Stmt_Context)_localctx).ast =  new AstStmtExpr(loc, parsedStatements);
				
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
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u0232\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\6\2Z\n\2\r\2\16\2[\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0080\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\7\7\u008c\n\7\f\7\16\7\u008f\13\7\3\7\3\7\3\7\5\7\u0094\n\7"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\n\u00ba\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e1\n\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u00e9\n\17\f\17\16\17\u00ec\13\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00f7\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u00fe\n\20\f\20\16\20\u0101\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u010a\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u0117\n\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0136\n\25\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\5\27\u0140\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\5\30\u014e\n\30\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\5\32\u0167\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\5\33\u0184\n\33\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u019d\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01ae\n\36\3\37\3\37\3\37\3 "+
		"\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\7!\u01bf\n!\f!\16!\u01c2\13!\3!\5!\u01c5"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\5$\u01d8\n"+
		"$\3%\3%\3%\3%\3%\3%\3%\3%\5%\u01e2\n%\3&\3&\3&\3&\3&\5&\u01e9\n&\3\'\3"+
		"\'\3\'\3\'\5\'\u01ef\n\'\3(\3(\3(\3(\6(\u01f5\n(\r(\16(\u01f6\3(\3(\3"+
		"(\5(\u01fc\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u020c\n)\3"+
		"*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0224"+
		"\n*\3+\3+\3+\3+\3+\6+\u022b\n+\r+\16+\u022c\3+\3+\3+\3+\2\3\36,\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"\2\3\5\2\'(..\63\63\2\u0245\2Y\3\2\2\2\4_\3\2\2\2\6e\3\2\2\2\bk\3\2\2"+
		"\2\n\177\3\2\2\2\f\u0093\3\2\2\2\16\u0095\3\2\2\2\20\u0098\3\2\2\2\22"+
		"\u00b9\3\2\2\2\24\u00bb\3\2\2\2\26\u00bf\3\2\2\2\30\u00c9\3\2\2\2\32\u00e0"+
		"\3\2\2\2\34\u00ea\3\2\2\2\36\u00f6\3\2\2\2 \u0109\3\2\2\2\"\u010b\3\2"+
		"\2\2$\u0116\3\2\2\2&\u0118\3\2\2\2(\u0135\3\2\2\2*\u0137\3\2\2\2,\u013f"+
		"\3\2\2\2.\u014d\3\2\2\2\60\u014f\3\2\2\2\62\u0166\3\2\2\2\64\u0183\3\2"+
		"\2\2\66\u0185\3\2\2\28\u019c\3\2\2\2:\u01ad\3\2\2\2<\u01af\3\2\2\2>\u01b2"+
		"\3\2\2\2@\u01c4\3\2\2\2B\u01c6\3\2\2\2D\u01cb\3\2\2\2F\u01d7\3\2\2\2H"+
		"\u01e1\3\2\2\2J\u01e8\3\2\2\2L\u01ee\3\2\2\2N\u01fb\3\2\2\2P\u020b\3\2"+
		"\2\2R\u0223\3\2\2\2T\u0225\3\2\2\2VW\5\n\6\2WX\b\2\1\2XZ\3\2\2\2YV\3\2"+
		"\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7\2\2\3^\3\3\2\2\2_`"+
		"\7\61\2\2`a\7\66\2\2ab\7&\2\2bc\5\32\16\2cd\b\3\1\2d\5\3\2\2\2ef\7\62"+
		"\2\2fg\7\66\2\2gh\7\25\2\2hi\5\32\16\2ij\b\4\1\2j\7\3\2\2\2kl\7,\2\2l"+
		"m\7\66\2\2mn\7\r\2\2no\5\f\7\2op\7\16\2\2pq\7\25\2\2qr\5\32\16\2rs\7&"+
		"\2\2st\5\36\20\2tu\b\5\1\2u\t\3\2\2\2vw\5\4\3\2wx\b\6\1\2x\u0080\3\2\2"+
		"\2yz\5\6\4\2z{\b\6\1\2{\u0080\3\2\2\2|}\5\b\5\2}~\b\6\1\2~\u0080\3\2\2"+
		"\2\177v\3\2\2\2\177y\3\2\2\2\177|\3\2\2\2\u0080\13\3\2\2\2\u0081\u0082"+
		"\7\66\2\2\u0082\u0083\7\25\2\2\u0083\u0084\5\32\16\2\u0084\u008d\b\7\1"+
		"\2\u0085\u0086\7\24\2\2\u0086\u0087\7\66\2\2\u0087\u0088\7\25\2\2\u0088"+
		"\u0089\5\32\16\2\u0089\u008a\b\7\1\2\u008a\u008c\3\2\2\2\u008b\u0085\3"+
		"\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\b\7\1\2\u0091\u0094\3\2"+
		"\2\2\u0092\u0094\b\7\1\2\u0093\u0081\3\2\2\2\u0093\u0092\3\2\2\2\u0094"+
		"\r\3\2\2\2\u0095\u0096\t\2\2\2\u0096\u0097\b\b\1\2\u0097\17\3\2\2\2\u0098"+
		"\u0099\7\66\2\2\u0099\u009a\b\t\1\2\u009a\21\3\2\2\2\u009b\u009c\7\21"+
		"\2\2\u009c\u009d\5\36\20\2\u009d\u009e\7\22\2\2\u009e\u009f\5\16\b\2\u009f"+
		"\u00a0\b\n\1\2\u00a0\u00ba\3\2\2\2\u00a1\u00a2\7\21\2\2\u00a2\u00a3\5"+
		"\36\20\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\5\20\t\2\u00a5\u00a6\b\n\1\2"+
		"\u00a6\u00ba\3\2\2\2\u00a7\u00a8\7\21\2\2\u00a8\u00a9\5\36\20\2\u00a9"+
		"\u00aa\7\22\2\2\u00aa\u00ab\5\26\f\2\u00ab\u00ac\b\n\1\2\u00ac\u00ba\3"+
		"\2\2\2\u00ad\u00ae\7\21\2\2\u00ae\u00af\5\36\20\2\u00af\u00b0\7\22\2\2"+
		"\u00b0\u00b1\5\30\r\2\u00b1\u00b2\b\n\1\2\u00b2\u00ba\3\2\2\2\u00b3\u00b4"+
		"\7\21\2\2\u00b4\u00b5\5\36\20\2\u00b5\u00b6\7\22\2\2\u00b6\u00b7\5\22"+
		"\n\2\u00b7\u00b8\b\n\1\2\u00b8\u00ba\3\2\2\2\u00b9\u009b\3\2\2\2\u00b9"+
		"\u00a1\3\2\2\2\u00b9\u00a7\3\2\2\2\u00b9\u00ad\3\2\2\2\u00b9\u00b3\3\2"+
		"\2\2\u00ba\23\3\2\2\2\u00bb\u00bc\7%\2\2\u00bc\u00bd\5\32\16\2\u00bd\u00be"+
		"\b\13\1\2\u00be\25\3\2\2\2\u00bf\u00c0\7\17\2\2\u00c0\u00c1\7\66\2\2\u00c1"+
		"\u00c2\7\25\2\2\u00c2\u00c3\5\32\16\2\u00c3\u00c4\b\f\1\2\u00c4\u00c5"+
		"\5\34\17\2\u00c5\u00c6\b\f\1\2\u00c6\u00c7\7\20\2\2\u00c7\u00c8\b\f\1"+
		"\2\u00c8\27\3\2\2\2\u00c9\u00ca\7\r\2\2\u00ca\u00cb\5\32\16\2\u00cb\u00cc"+
		"\7\16\2\2\u00cc\u00cd\b\r\1\2\u00cd\31\3\2\2\2\u00ce\u00cf\5\16\b\2\u00cf"+
		"\u00d0\b\16\1\2\u00d0\u00e1\3\2\2\2\u00d1\u00d2\5\20\t\2\u00d2\u00d3\b"+
		"\16\1\2\u00d3\u00e1\3\2\2\2\u00d4\u00d5\5\22\n\2\u00d5\u00d6\b\16\1\2"+
		"\u00d6\u00e1\3\2\2\2\u00d7\u00d8\5\24\13\2\u00d8\u00d9\b\16\1\2\u00d9"+
		"\u00e1\3\2\2\2\u00da\u00db\5\26\f\2\u00db\u00dc\b\16\1\2\u00dc\u00e1\3"+
		"\2\2\2\u00dd\u00de\5\30\r\2\u00de\u00df\b\16\1\2\u00df\u00e1\3\2\2\2\u00e0"+
		"\u00ce\3\2\2\2\u00e0\u00d1\3\2\2\2\u00e0\u00d4\3\2\2\2\u00e0\u00d7\3\2"+
		"\2\2\u00e0\u00da\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\33\3\2\2\2\u00e2\u00e3"+
		"\7\24\2\2\u00e3\u00e4\7\66\2\2\u00e4\u00e5\7\25\2\2\u00e5\u00e6\5\32\16"+
		"\2\u00e6\u00e7\b\17\1\2\u00e7\u00e9\3\2\2\2\u00e8\u00e2\3\2\2\2\u00e9"+
		"\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\35\3\2\2"+
		"\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\b\20\1\2\u00ee\u00ef\5T+\2\u00ef\u00f0"+
		"\b\20\1\2\u00f0\u00f7\3\2\2\2\u00f1\u00f2\5\"\22\2\u00f2\u00f3\b\20\1"+
		"\2\u00f3\u00f4\5 \21\2\u00f4\u00f5\b\20\1\2\u00f5\u00f7\3\2\2\2\u00f6"+
		"\u00ed\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f7\u00ff\3\2\2\2\u00f8\u00f9\f\5"+
		"\2\2\u00f9\u00fa\7\64\2\2\u00fa\u00fb\5N(\2\u00fb\u00fc\b\20\1\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00f8\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100\37\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103"+
		"\7\30\2\2\u0103\u0104\5\"\22\2\u0104\u0105\b\21\1\2\u0105\u0106\5 \21"+
		"\2\u0106\u0107\b\21\1\2\u0107\u010a\3\2\2\2\u0108\u010a\b\21\1\2\u0109"+
		"\u0102\3\2\2\2\u0109\u0108\3\2\2\2\u010a!\3\2\2\2\u010b\u010c\5&\24\2"+
		"\u010c\u010d\5$\23\2\u010d\u010e\b\22\1\2\u010e#\3\2\2\2\u010f\u0110\7"+
		"\27\2\2\u0110\u0111\5&\24\2\u0111\u0112\b\23\1\2\u0112\u0113\5$\23\2\u0113"+
		"\u0114\b\23\1\2\u0114\u0117\3\2\2\2\u0115\u0117\b\23\1\2\u0116\u010f\3"+
		"\2\2\2\u0116\u0115\3\2\2\2\u0117%\3\2\2\2\u0118\u0119\5*\26\2\u0119\u011a"+
		"\5(\25\2\u011a\u011b\b\24\1\2\u011b\'\3\2\2\2\u011c\u011d\7\32\2\2\u011d"+
		"\u011e\5*\26\2\u011e\u011f\b\25\1\2\u011f\u0136\3\2\2\2\u0120\u0121\7"+
		"\33\2\2\u0121\u0122\5*\26\2\u0122\u0123\b\25\1\2\u0123\u0136\3\2\2\2\u0124"+
		"\u0125\7\34\2\2\u0125\u0126\5*\26\2\u0126\u0127\b\25\1\2\u0127\u0136\3"+
		"\2\2\2\u0128\u0129\7\35\2\2\u0129\u012a\5*\26\2\u012a\u012b\b\25\1\2\u012b"+
		"\u0136\3\2\2\2\u012c\u012d\7\36\2\2\u012d\u012e\5*\26\2\u012e\u012f\b"+
		"\25\1\2\u012f\u0136\3\2\2\2\u0130\u0131\7\37\2\2\u0131\u0132\5*\26\2\u0132"+
		"\u0133\b\25\1\2\u0133\u0136\3\2\2\2\u0134\u0136\b\25\1\2\u0135\u011c\3"+
		"\2\2\2\u0135\u0120\3\2\2\2\u0135\u0124\3\2\2\2\u0135\u0128\3\2\2\2\u0135"+
		"\u012c\3\2\2\2\u0135\u0130\3\2\2\2\u0135\u0134\3\2\2\2\u0136)\3\2\2\2"+
		"\u0137\u0138\5\60\31\2\u0138\u0139\5,\27\2\u0139\u013a\b\26\1\2\u013a"+
		"+\3\2\2\2\u013b\u013c\5.\30\2\u013c\u013d\b\27\1\2\u013d\u0140\3\2\2\2"+
		"\u013e\u0140\b\27\1\2\u013f\u013b\3\2\2\2\u013f\u013e\3\2\2\2\u0140-\3"+
		"\2\2\2\u0141\u0142\7#\2\2\u0142\u0143\5\60\31\2\u0143\u0144\b\30\1\2\u0144"+
		"\u0145\5,\27\2\u0145\u0146\b\30\1\2\u0146\u014e\3\2\2\2\u0147\u0148\7"+
		"$\2\2\u0148\u0149\5\60\31\2\u0149\u014a\b\30\1\2\u014a\u014b\5,\27\2\u014b"+
		"\u014c\b\30\1\2\u014c\u014e\3\2\2\2\u014d\u0141\3\2\2\2\u014d\u0147\3"+
		"\2\2\2\u014e/\3\2\2\2\u014f\u0150\5\64\33\2\u0150\u0151\5\62\32\2\u0151"+
		"\u0152\b\31\1\2\u0152\61\3\2\2\2\u0153\u0154\7 \2\2\u0154\u0155\5\64\33"+
		"\2\u0155\u0156\b\32\1\2\u0156\u0157\5\62\32\2\u0157\u0158\b\32\1\2\u0158"+
		"\u0167\3\2\2\2\u0159\u015a\7!\2\2\u015a\u015b\5\64\33\2\u015b\u015c\b"+
		"\32\1\2\u015c\u015d\5\62\32\2\u015d\u015e\b\32\1\2\u015e\u0167\3\2\2\2"+
		"\u015f\u0160\7\"\2\2\u0160\u0161\5\64\33\2\u0161\u0162\b\32\1\2\u0162"+
		"\u0163\5\62\32\2\u0163\u0164\b\32\1\2\u0164\u0167\3\2\2\2\u0165\u0167"+
		"\b\32\1\2\u0166\u0153\3\2\2\2\u0166\u0159\3\2\2\2\u0166\u015f\3\2\2\2"+
		"\u0166\u0165\3\2\2\2\u0167\63\3\2\2\2\u0168\u0169\5\66\34\2\u0169\u016a"+
		"\b\33\1\2\u016a\u0184\3\2\2\2\u016b\u016c\7\31\2\2\u016c\u016d\5\64\33"+
		"\2\u016d\u016e\b\33\1\2\u016e\u0184\3\2\2\2\u016f\u0170\7#\2\2\u0170\u0171"+
		"\5\64\33\2\u0171\u0172\b\33\1\2\u0172\u0184\3\2\2\2\u0173\u0174\7$\2\2"+
		"\u0174\u0175\5\64\33\2\u0175\u0176\b\33\1\2\u0176\u0184\3\2\2\2\u0177"+
		"\u0178\7%\2\2\u0178\u0179\5\64\33\2\u0179\u017a\b\33\1\2\u017a\u0184\3"+
		"\2\2\2\u017b\u017c\7/\2\2\u017c\u017d\5\64\33\2\u017d\u017e\b\33\1\2\u017e"+
		"\u0184\3\2\2\2\u017f\u0180\7)\2\2\u0180\u0181\5\64\33\2\u0181\u0182\b"+
		"\33\1\2\u0182\u0184\3\2\2\2\u0183\u0168\3\2\2\2\u0183\u016b\3\2\2\2\u0183"+
		"\u016f\3\2\2\2\u0183\u0173\3\2\2\2\u0183\u0177\3\2\2\2\u0183\u017b\3\2"+
		"\2\2\u0183\u017f\3\2\2\2\u0184\65\3\2\2\2\u0185\u0186\5:\36\2\u0186\u0187"+
		"\58\35\2\u0187\u0188\b\34\1\2\u0188\67\3\2\2\2\u0189\u018a\7\21\2\2\u018a"+
		"\u018b\5L\'\2\u018b\u018c\7\22\2\2\u018c\u018d\b\35\1\2\u018d\u018e\5"+
		"8\35\2\u018e\u018f\b\35\1\2\u018f\u019d\3\2\2\2\u0190\u0191\7%\2\2\u0191"+
		"\u0192\b\35\1\2\u0192\u0193\58\35\2\u0193\u0194\b\35\1\2\u0194\u019d\3"+
		"\2\2\2\u0195\u0196\7\23\2\2\u0196\u0197\7\66\2\2\u0197\u0198\b\35\1\2"+
		"\u0198\u0199\58\35\2\u0199\u019a\b\35\1\2\u019a\u019d\3\2\2\2\u019b\u019d"+
		"\b\35\1\2\u019c\u0189\3\2\2\2\u019c\u0190\3\2\2\2\u019c\u0195\3\2\2\2"+
		"\u019c\u019b\3\2\2\2\u019d9\3\2\2\2\u019e\u019f\5P)\2\u019f\u01a0\b\36"+
		"\1\2\u01a0\u01ae\3\2\2\2\u01a1\u01a2\5<\37\2\u01a2\u01a3\b\36\1\2\u01a3"+
		"\u01ae\3\2\2\2\u01a4\u01a5\5> \2\u01a5\u01a6\b\36\1\2\u01a6\u01ae\3\2"+
		"\2\2\u01a7\u01a8\5B\"\2\u01a8\u01a9\b\36\1\2\u01a9\u01ae\3\2\2\2\u01aa"+
		"\u01ab\5D#\2\u01ab\u01ac\b\36\1\2\u01ac\u01ae\3\2\2\2\u01ad\u019e\3\2"+
		"\2\2\u01ad\u01a1\3\2\2\2\u01ad\u01a4\3\2\2\2\u01ad\u01a7\3\2\2\2\u01ad"+
		"\u01aa\3\2\2\2\u01ae;\3\2\2\2\u01af\u01b0\7\66\2\2\u01b0\u01b1\b\37\1"+
		"\2\u01b1=\3\2\2\2\u01b2\u01b3\7\66\2\2\u01b3\u01b4\7\r\2\2\u01b4\u01b5"+
		"\5@!\2\u01b5\u01b6\7\16\2\2\u01b6\u01b7\b \1\2\u01b7?\3\2\2\2\u01b8\u01b9"+
		"\5\36\20\2\u01b9\u01c0\b!\1\2\u01ba\u01bb\7\24\2\2\u01bb\u01bc\5\36\20"+
		"\2\u01bc\u01bd\b!\1\2\u01bd\u01bf\3\2\2\2\u01be\u01ba\3\2\2\2\u01bf\u01c2"+
		"\3\2\2\2\u01c0\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c5\3\2\2\2\u01c2"+
		"\u01c0\3\2\2\2\u01c3\u01c5\b!\1\2\u01c4\u01b8\3\2\2\2\u01c4\u01c3\3\2"+
		"\2\2\u01c5A\3\2\2\2\u01c6\u01c7\7\r\2\2\u01c7\u01c8\5\36\20\2\u01c8\u01c9"+
		"\7\16\2\2\u01c9\u01ca\b\"\1\2\u01caC\3\2\2\2\u01cb\u01cc\7\r\2\2\u01cc"+
		"\u01cd\5\36\20\2\u01cd\u01ce\7\25\2\2\u01ce\u01cf\5\32\16\2\u01cf\u01d0"+
		"\7\16\2\2\u01d0\u01d1\b#\1\2\u01d1E\3\2\2\2\u01d2\u01d3\7\r\2\2\u01d3"+
		"\u01d4\5H%\2\u01d4\u01d5\7\16\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d8\3\2"+
		"\2\2\u01d7\u01d2\3\2\2\2\u01d7\u01d6\3\2\2\2\u01d8G\3\2\2\2\u01d9\u01da"+
		"\5\36\20\2\u01da\u01db\5H%\2\u01db\u01e2\3\2\2\2\u01dc\u01dd\7\24\2\2"+
		"\u01dd\u01de\5\36\20\2\u01de\u01df\5H%\2\u01df\u01e2\3\2\2\2\u01e0\u01e2"+
		"\3\2\2\2\u01e1\u01d9\3\2\2\2\u01e1\u01dc\3\2\2\2\u01e1\u01e0\3\2\2\2\u01e2"+
		"I\3\2\2\2\u01e3\u01e4\7\25\2\2\u01e4\u01e5\5\32\16\2\u01e5\u01e6\7\16"+
		"\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e9\7\16\2\2\u01e8\u01e3\3\2\2\2\u01e8"+
		"\u01e7\3\2\2\2\u01e9K\3\2\2\2\u01ea\u01eb\5\36\20\2\u01eb\u01ec\b\'\1"+
		"\2\u01ec\u01ef\3\2\2\2\u01ed\u01ef\b\'\1\2\u01ee\u01ea\3\2\2\2\u01ee\u01ed"+
		"\3\2\2\2\u01efM\3\2\2\2\u01f0\u01f4\7\17\2\2\u01f1\u01f2\5\n\6\2\u01f2"+
		"\u01f3\b(\1\2\u01f3\u01f5\3\2\2\2\u01f4\u01f1\3\2\2\2\u01f5\u01f6\3\2"+
		"\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8"+
		"\u01f9\7\20\2\2\u01f9\u01fc\3\2\2\2\u01fa\u01fc\b(\1\2\u01fb\u01f0\3\2"+
		"\2\2\u01fb\u01fa\3\2\2\2\u01fcO\3\2\2\2\u01fd\u01fe\7\7\2\2\u01fe\u020c"+
		"\b)\1\2\u01ff\u0200\7\b\2\2\u0200\u020c\b)\1\2\u0201\u0202\7\t\2\2\u0202"+
		"\u020c\b)\1\2\u0203\u0204\7\n\2\2\u0204\u020c\b)\1\2\u0205\u0206\7\13"+
		"\2\2\u0206\u020c\b)\1\2\u0207\u0208\7\6\2\2\u0208\u020c\b)\1\2\u0209\u020a"+
		"\7\f\2\2\u020a\u020c\b)\1\2\u020b\u01fd\3\2\2\2\u020b\u01ff\3\2\2\2\u020b"+
		"\u0201\3\2\2\2\u020b\u0203\3\2\2\2\u020b\u0205\3\2\2\2\u020b\u0207\3\2"+
		"\2\2\u020b\u0209\3\2\2\2\u020cQ\3\2\2\2\u020d\u020e\7-\2\2\u020e\u020f"+
		"\5\36\20\2\u020f\u0210\7\60\2\2\u0210\u0211\5R*\2\u0211\u0212\7+\2\2\u0212"+
		"\u0213\5R*\2\u0213\u0214\b*\1\2\u0214\u0224\3\2\2\2\u0215\u0216\7\65\2"+
		"\2\u0216\u0217\5\36\20\2\u0217\u0218\7*\2\2\u0218\u0219\5R*\2\u0219\u021a"+
		"\b*\1\2\u021a\u0224\3\2\2\2\u021b\u021c\5\36\20\2\u021c\u021d\b*\1\2\u021d"+
		"\u0224\3\2\2\2\u021e\u021f\5\36\20\2\u021f\u0220\7&\2\2\u0220\u0221\5"+
		"\36\20\2\u0221\u0222\b*\1\2\u0222\u0224\3\2\2\2\u0223\u020d\3\2\2\2\u0223"+
		"\u0215\3\2\2\2\u0223\u021b\3\2\2\2\u0223\u021e\3\2\2\2\u0224S\3\2\2\2"+
		"\u0225\u022a\7\17\2\2\u0226\u0227\5R*\2\u0227\u0228\b+\1\2\u0228\u0229"+
		"\7\26\2\2\u0229\u022b\3\2\2\2\u022a\u0226\3\2\2\2\u022b\u022c\3\2\2\2"+
		"\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u022f"+
		"\7\20\2\2\u022f\u0230\b+\1\2\u0230U\3\2\2\2\37[\177\u008d\u0093\u00b9"+
		"\u00e0\u00ea\u00f6\u00ff\u0109\u0116\u0135\u013f\u014d\u0166\u0183\u019c"+
		"\u01ad\u01c0\u01c4\u01d7\u01e1\u01e8\u01ee\u01f6\u01fb\u020b\u0223\u022c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}