// Generated from lexan/PrevLexer.g4 by ANTLR 4.8

	package prev.phase.lexan;
	import prev.common.report.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "WS", "TAB", "NONE", "TRUE", "FALSE", "INTCONST", "CHARACTERCONST", 
			"STRINGCONST", "POINTER", "LEFTBRAC", "RIGHTBRAC", "LEFTCURL", "RIGHTCURL", 
			"LEFTSQR", "RIGHTSQR", "DOT", "COMMA", "COL", "SEMCOL", "AND", "OR", 
			"EXCL", "EQL", "NEQ", "LT", "GT", "LTE", "GTE", "STAR", "SLASH", "MOD", 
			"PLUS", "MINUS", "CARET", "ASIGN", "BOOLEAN", "CHAR", "DEL", "DO", "ELSE", 
			"FUNCTION", "IF", "INTEGER", "NEW", "THEN", "TYPE", "VARIABLE", "VOID", 
			"WHERE", "WHILE", "IDENTIFIER", "ERROR"
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
		public LexAn.PrevToken nextToken() {
			return (LexAn.PrevToken) super.nextToken();
		}


	public PrevLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrevLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 2:
			TAB_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void TAB_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setCharPositionInLine((getCharPositionInLine()/8 +1)*8);
			break;
		}
	}
	private void ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 if(true) throw new Report.Error(new Location(getLine(),getCharPositionInLine(), getLine(),getCharPositionInLine()+getText().length()), "Napaka v "+ getLine() +". vrstici, "+ getCharPositionInLine() + ". znak.");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u0140\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\6\2o\n\2\r\2\16\2p\3\2\7\2t\n\2\f\2\16\2"+
		"w\13\2\3\2\3\2\3\3\6\3|\n\3\r\3\16\3}\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\6\b\u0098"+
		"\n\b\r\b\16\b\u0099\3\t\3\t\3\t\3\t\5\t\u00a0\n\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\7\n\u00a8\n\n\f\n\16\n\u00ab\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3"+
		"\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3,\3"+
		",\3,\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\5\65\u0136\n\65\3\65\7\65"+
		"\u0139\n\65\f\65\16\65\u013c\13\65\3\66\3\66\3\66\2\2\67\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67\3\2\b\3\2\f\f\5"+
		"\2\f\f\17\17\"\"\4\2\"(*\u0080\4\2\"#%\u0080\5\2C\\aac|\6\2\62;C\\aac"+
		"|\2\u0147\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\3n\3\2\2\2\5{\3\2\2\2\7\u0081\3\2\2\2\t\u0086\3\2\2\2\13\u008b\3\2\2"+
		"\2\r\u0090\3\2\2\2\17\u0097\3\2\2\2\21\u009b\3\2\2\2\23\u00a3\3\2\2\2"+
		"\25\u00ae\3\2\2\2\27\u00b2\3\2\2\2\31\u00b4\3\2\2\2\33\u00b6\3\2\2\2\35"+
		"\u00b8\3\2\2\2\37\u00ba\3\2\2\2!\u00bc\3\2\2\2#\u00be\3\2\2\2%\u00c0\3"+
		"\2\2\2\'\u00c2\3\2\2\2)\u00c4\3\2\2\2+\u00c6\3\2\2\2-\u00c8\3\2\2\2/\u00ca"+
		"\3\2\2\2\61\u00cc\3\2\2\2\63\u00cf\3\2\2\2\65\u00d2\3\2\2\2\67\u00d4\3"+
		"\2\2\29\u00d6\3\2\2\2;\u00d9\3\2\2\2=\u00dc\3\2\2\2?\u00de\3\2\2\2A\u00e0"+
		"\3\2\2\2C\u00e2\3\2\2\2E\u00e4\3\2\2\2G\u00e6\3\2\2\2I\u00e8\3\2\2\2K"+
		"\u00ea\3\2\2\2M\u00f2\3\2\2\2O\u00f7\3\2\2\2Q\u00fb\3\2\2\2S\u00fe\3\2"+
		"\2\2U\u0103\3\2\2\2W\u0107\3\2\2\2Y\u010a\3\2\2\2[\u0112\3\2\2\2]\u0116"+
		"\3\2\2\2_\u011b\3\2\2\2a\u011f\3\2\2\2c\u0123\3\2\2\2e\u0128\3\2\2\2g"+
		"\u012e\3\2\2\2i\u0135\3\2\2\2k\u013d\3\2\2\2mo\7%\2\2nm\3\2\2\2op\3\2"+
		"\2\2pn\3\2\2\2pq\3\2\2\2qu\3\2\2\2rt\n\2\2\2sr\3\2\2\2tw\3\2\2\2us\3\2"+
		"\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\b\2\2\2y\4\3\2\2\2z|\t\3\2\2{z\3"+
		"\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\b\3\2\2\u0080"+
		"\6\3\2\2\2\u0081\u0082\7\13\2\2\u0082\u0083\b\4\3\2\u0083\u0084\3\2\2"+
		"\2\u0084\u0085\b\4\2\2\u0085\b\3\2\2\2\u0086\u0087\7p\2\2\u0087\u0088"+
		"\7q\2\2\u0088\u0089\7p\2\2\u0089\u008a\7g\2\2\u008a\n\3\2\2\2\u008b\u008c"+
		"\7v\2\2\u008c\u008d\7t\2\2\u008d\u008e\7w\2\2\u008e\u008f\7g\2\2\u008f"+
		"\f\3\2\2\2\u0090\u0091\7h\2\2\u0091\u0092\7c\2\2\u0092\u0093\7n\2\2\u0093"+
		"\u0094\7u\2\2\u0094\u0095\7g\2\2\u0095\16\3\2\2\2\u0096\u0098\4\62;\2"+
		"\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a"+
		"\3\2\2\2\u009a\20\3\2\2\2\u009b\u009f\7)\2\2\u009c\u00a0\t\4\2\2\u009d"+
		"\u009e\7^\2\2\u009e\u00a0\7)\2\2\u009f\u009c\3\2\2\2\u009f\u009d\3\2\2"+
		"\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7)\2\2\u00a2\22\3\2\2\2\u00a3\u00a9"+
		"\7$\2\2\u00a4\u00a8\t\5\2\2\u00a5\u00a6\7^\2\2\u00a6\u00a8\7$\2\2\u00a7"+
		"\u00a4\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\u00ad\7$\2\2\u00ad\24\3\2\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7n\2\2\u00b1\26\3\2\2\2\u00b2\u00b3\7*\2\2\u00b3\30\3\2\2\2\u00b4"+
		"\u00b5\7+\2\2\u00b5\32\3\2\2\2\u00b6\u00b7\7}\2\2\u00b7\34\3\2\2\2\u00b8"+
		"\u00b9\7\177\2\2\u00b9\36\3\2\2\2\u00ba\u00bb\7]\2\2\u00bb \3\2\2\2\u00bc"+
		"\u00bd\7_\2\2\u00bd\"\3\2\2\2\u00be\u00bf\7\60\2\2\u00bf$\3\2\2\2\u00c0"+
		"\u00c1\7.\2\2\u00c1&\3\2\2\2\u00c2\u00c3\7<\2\2\u00c3(\3\2\2\2\u00c4\u00c5"+
		"\7=\2\2\u00c5*\3\2\2\2\u00c6\u00c7\7(\2\2\u00c7,\3\2\2\2\u00c8\u00c9\7"+
		"~\2\2\u00c9.\3\2\2\2\u00ca\u00cb\7#\2\2\u00cb\60\3\2\2\2\u00cc\u00cd\7"+
		"?\2\2\u00cd\u00ce\7?\2\2\u00ce\62\3\2\2\2\u00cf\u00d0\7#\2\2\u00d0\u00d1"+
		"\7?\2\2\u00d1\64\3\2\2\2\u00d2\u00d3\7>\2\2\u00d3\66\3\2\2\2\u00d4\u00d5"+
		"\7@\2\2\u00d58\3\2\2\2\u00d6\u00d7\7>\2\2\u00d7\u00d8\7?\2\2\u00d8:\3"+
		"\2\2\2\u00d9\u00da\7@\2\2\u00da\u00db\7?\2\2\u00db<\3\2\2\2\u00dc\u00dd"+
		"\7,\2\2\u00dd>\3\2\2\2\u00de\u00df\7\61\2\2\u00df@\3\2\2\2\u00e0\u00e1"+
		"\7\'\2\2\u00e1B\3\2\2\2\u00e2\u00e3\7-\2\2\u00e3D\3\2\2\2\u00e4\u00e5"+
		"\7/\2\2\u00e5F\3\2\2\2\u00e6\u00e7\7`\2\2\u00e7H\3\2\2\2\u00e8\u00e9\7"+
		"?\2\2\u00e9J\3\2\2\2\u00ea\u00eb\7d\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed"+
		"\7q\2\2\u00ed\u00ee\7n\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7c\2\2\u00f0"+
		"\u00f1\7p\2\2\u00f1L\3\2\2\2\u00f2\u00f3\7e\2\2\u00f3\u00f4\7j\2\2\u00f4"+
		"\u00f5\7c\2\2\u00f5\u00f6\7t\2\2\u00f6N\3\2\2\2\u00f7\u00f8\7f\2\2\u00f8"+
		"\u00f9\7g\2\2\u00f9\u00fa\7n\2\2\u00faP\3\2\2\2\u00fb\u00fc\7f\2\2\u00fc"+
		"\u00fd\7q\2\2\u00fdR\3\2\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7n\2\2\u0100"+
		"\u0101\7u\2\2\u0101\u0102\7g\2\2\u0102T\3\2\2\2\u0103\u0104\7h\2\2\u0104"+
		"\u0105\7w\2\2\u0105\u0106\7p\2\2\u0106V\3\2\2\2\u0107\u0108\7k\2\2\u0108"+
		"\u0109\7h\2\2\u0109X\3\2\2\2\u010a\u010b\7k\2\2\u010b\u010c\7p\2\2\u010c"+
		"\u010d\7v\2\2\u010d\u010e\7g\2\2\u010e\u010f\7i\2\2\u010f\u0110\7g\2\2"+
		"\u0110\u0111\7t\2\2\u0111Z\3\2\2\2\u0112\u0113\7p\2\2\u0113\u0114\7g\2"+
		"\2\u0114\u0115\7y\2\2\u0115\\\3\2\2\2\u0116\u0117\7v\2\2\u0117\u0118\7"+
		"j\2\2\u0118\u0119\7g\2\2\u0119\u011a\7p\2\2\u011a^\3\2\2\2\u011b\u011c"+
		"\7v\2\2\u011c\u011d\7{\2\2\u011d\u011e\7r\2\2\u011e`\3\2\2\2\u011f\u0120"+
		"\7x\2\2\u0120\u0121\7c\2\2\u0121\u0122\7t\2\2\u0122b\3\2\2\2\u0123\u0124"+
		"\7x\2\2\u0124\u0125\7q\2\2\u0125\u0126\7k\2\2\u0126\u0127\7f\2\2\u0127"+
		"d\3\2\2\2\u0128\u0129\7y\2\2\u0129\u012a\7j\2\2\u012a\u012b\7g\2\2\u012b"+
		"\u012c\7t\2\2\u012c\u012d\7g\2\2\u012df\3\2\2\2\u012e\u012f\7y\2\2\u012f"+
		"\u0130\7j\2\2\u0130\u0131\7k\2\2\u0131\u0132\7n\2\2\u0132\u0133\7g\2\2"+
		"\u0133h\3\2\2\2\u0134\u0136\t\6\2\2\u0135\u0134\3\2\2\2\u0136\u013a\3"+
		"\2\2\2\u0137\u0139\t\7\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013bj\3\2\2\2\u013c\u013a\3\2\2\2"+
		"\u013d\u013e\13\2\2\2\u013e\u013f\b\66\4\2\u013fl\3\2\2\2\r\2pu}\u0099"+
		"\u009f\u00a7\u00a9\u0135\u0138\u013a\5\b\2\2\3\4\2\3\66\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}