// Generated from T.g4 by ANTLR 4.7.1
package tc.compiler.parse;

  import tc.compiler.Location;
  import tc.compiler.tree.*;
  import static tc.compiler.parse.TreeBuilder.*;
  import java.util.List;
  import java.util.ArrayList;
  import tc.compiler.tree.type.*;
  import java.lang.Integer;
  import java.lang.String;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTEGER_LITERAL=1, LPAREN=2, RPAREN=3, LBRACK=4, RBRACK=5, LSQBRACK=6, 
		RSQBRACK=7, SEMICOLON=8, PLUS=9, MINUS=10, COMMA=11, EQUALS=12, NOT=13, 
		MULTIPLY=14, DIVIDE=15, LESSTHAN=16, GREATERTHAN=17, INT=18, MAIN=19, 
		OUT=20, IF=21, ELSE=22, WHILE=23, BREAK=24, CONTINUE=25, NEW=26, IDENTIFIER=27, 
		Discard=28, DiscardComment=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"Digit", "Letter", "SpaceToken", "EndOfLineComment", "INTEGER_LITERAL", 
		"LPAREN", "RPAREN", "LBRACK", "RBRACK", "LSQBRACK", "RSQBRACK", "SEMICOLON", 
		"PLUS", "MINUS", "COMMA", "EQUALS", "NOT", "MULTIPLY", "DIVIDE", "LESSTHAN", 
		"GREATERTHAN", "INT", "MAIN", "OUT", "IF", "ELSE", "WHILE", "BREAK", "CONTINUE", 
		"NEW", "IDENTIFIER", "Discard", "DiscardComment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "'int'", "'main'", "'out'", "'if'", 
		"'else'", "'while'", "'break'", "'continue'", "'new'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "INTEGER_LITERAL", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LSQBRACK", 
		"RSQBRACK", "SEMICOLON", "PLUS", "MINUS", "COMMA", "EQUALS", "NOT", "MULTIPLY", 
		"DIVIDE", "LESSTHAN", "GREATERTHAN", "INT", "MAIN", "OUT", "IF", "ELSE", 
		"WHILE", "BREAK", "CONTINUE", "NEW", "IDENTIFIER", "Discard", "DiscardComment"
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


	public TLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "T.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00bd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5P\n\5\f\5\16"+
		"\5S\13\5\3\5\5\5V\n\5\3\6\6\6Y\n\6\r\6\16\6Z\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \7 \u00ae\n \f \16 \u00b1\13 \3!\6!\u00b4\n!\r!\16!\u00b5"+
		"\3!\3!\3\"\3\"\3\"\3\"\2\2#\3\2\5\2\7\2\t\2\13\3\r\4\17\5\21\6\23\7\25"+
		"\b\27\t\31\n\33\13\35\f\37\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\26\63"+
		"\27\65\30\67\319\32;\33=\34?\35A\36C\37\3\2\27\3\2\62;\5\2C\\aac|\5\2"+
		"\13\f\16\17\"\"\4\2\f\f\17\17\4\3\f\f\17\17\3\2**\3\2++\3\2}}\3\2\177"+
		"\177\3\2]]\3\2__\3\2==\3\2--\3\2//\3\2..\3\2??\3\2##\3\2,,\3\2\61\61\3"+
		"\2>>\3\2@@\2\u00bd\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5G\3\2\2\2\7I\3\2\2\2\tK\3\2\2\2\13X\3"+
		"\2\2\2\r\\\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2\25d\3\2\2\2\27"+
		"f\3\2\2\2\31h\3\2\2\2\33j\3\2\2\2\35l\3\2\2\2\37n\3\2\2\2!p\3\2\2\2#r"+
		"\3\2\2\2%t\3\2\2\2\'v\3\2\2\2)x\3\2\2\2+z\3\2\2\2-|\3\2\2\2/\u0080\3\2"+
		"\2\2\61\u0085\3\2\2\2\63\u0089\3\2\2\2\65\u008c\3\2\2\2\67\u0091\3\2\2"+
		"\29\u0097\3\2\2\2;\u009d\3\2\2\2=\u00a6\3\2\2\2?\u00aa\3\2\2\2A\u00b3"+
		"\3\2\2\2C\u00b9\3\2\2\2EF\t\2\2\2F\4\3\2\2\2GH\t\3\2\2H\6\3\2\2\2IJ\t"+
		"\4\2\2J\b\3\2\2\2KL\7\61\2\2LM\7\61\2\2MQ\3\2\2\2NP\n\5\2\2ON\3\2\2\2"+
		"PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RU\3\2\2\2SQ\3\2\2\2TV\t\6\2\2UT\3\2\2\2"+
		"V\n\3\2\2\2WY\5\3\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\f\3\2\2"+
		"\2\\]\t\7\2\2]\16\3\2\2\2^_\t\b\2\2_\20\3\2\2\2`a\t\t\2\2a\22\3\2\2\2"+
		"bc\t\n\2\2c\24\3\2\2\2de\t\13\2\2e\26\3\2\2\2fg\t\f\2\2g\30\3\2\2\2hi"+
		"\t\r\2\2i\32\3\2\2\2jk\t\16\2\2k\34\3\2\2\2lm\t\17\2\2m\36\3\2\2\2no\t"+
		"\20\2\2o \3\2\2\2pq\t\21\2\2q\"\3\2\2\2rs\t\22\2\2s$\3\2\2\2tu\t\23\2"+
		"\2u&\3\2\2\2vw\t\24\2\2w(\3\2\2\2xy\t\25\2\2y*\3\2\2\2z{\t\26\2\2{,\3"+
		"\2\2\2|}\7k\2\2}~\7p\2\2~\177\7v\2\2\177.\3\2\2\2\u0080\u0081\7o\2\2\u0081"+
		"\u0082\7c\2\2\u0082\u0083\7k\2\2\u0083\u0084\7p\2\2\u0084\60\3\2\2\2\u0085"+
		"\u0086\7q\2\2\u0086\u0087\7w\2\2\u0087\u0088\7v\2\2\u0088\62\3\2\2\2\u0089"+
		"\u008a\7k\2\2\u008a\u008b\7h\2\2\u008b\64\3\2\2\2\u008c\u008d\7g\2\2\u008d"+
		"\u008e\7n\2\2\u008e\u008f\7u\2\2\u008f\u0090\7g\2\2\u0090\66\3\2\2\2\u0091"+
		"\u0092\7y\2\2\u0092\u0093\7j\2\2\u0093\u0094\7k\2\2\u0094\u0095\7n\2\2"+
		"\u0095\u0096\7g\2\2\u00968\3\2\2\2\u0097\u0098\7d\2\2\u0098\u0099\7t\2"+
		"\2\u0099\u009a\7g\2\2\u009a\u009b\7c\2\2\u009b\u009c\7m\2\2\u009c:\3\2"+
		"\2\2\u009d\u009e\7e\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1"+
		"\7v\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7w\2\2\u00a4"+
		"\u00a5\7g\2\2\u00a5<\3\2\2\2\u00a6\u00a7\7p\2\2\u00a7\u00a8\7g\2\2\u00a8"+
		"\u00a9\7y\2\2\u00a9>\3\2\2\2\u00aa\u00af\5\5\3\2\u00ab\u00ae\5\5\3\2\u00ac"+
		"\u00ae\5\3\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0@\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\u00b4\5\7\4\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\b!"+
		"\2\2\u00b8B\3\2\2\2\u00b9\u00ba\5\t\5\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc"+
		"\b\"\2\2\u00bcD\3\2\2\2\t\2QUZ\u00ad\u00af\u00b5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}