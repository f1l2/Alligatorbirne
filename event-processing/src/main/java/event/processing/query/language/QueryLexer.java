// Generated from Query.g4 by ANTLR 4.5.1

	package event.processing.query.language;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, Name=4, Int=5, COMMA=6, WS=7, NL=8, OPERATOR=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "Name", "Int", "COMMA", "WS", "NL", "OPERATOR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'FROM'", "'CONDITION'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "Name", "Int", "COMMA", "WS", "NL", "OPERATOR"
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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13F\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\6\5("+
		"\n\5\r\5\16\5)\3\6\6\6-\n\6\r\6\16\6.\3\7\6\7\62\n\7\r\7\16\7\63\3\b\6"+
		"\b\67\n\b\r\b\16\b8\3\t\5\t<\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\5\nE\n\n"+
		"\2\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\3\2\4\4\2C\\c|\4\2\13"+
		"\13\"\"L\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5\32"+
		"\3\2\2\2\7$\3\2\2\2\t\'\3\2\2\2\13,\3\2\2\2\r\61\3\2\2\2\17\66\3\2\2\2"+
		"\21;\3\2\2\2\23D\3\2\2\2\25\26\7H\2\2\26\27\7T\2\2\27\30\7Q\2\2\30\31"+
		"\7O\2\2\31\4\3\2\2\2\32\33\7E\2\2\33\34\7Q\2\2\34\35\7P\2\2\35\36\7F\2"+
		"\2\36\37\7K\2\2\37 \7V\2\2 !\7K\2\2!\"\7Q\2\2\"#\7P\2\2#\6\3\2\2\2$%\7"+
		"\60\2\2%\b\3\2\2\2&(\t\2\2\2\'&\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2"+
		"*\n\3\2\2\2+-\4\62;\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\f\3\2\2"+
		"\2\60\62\7.\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2"+
		"\2\64\16\3\2\2\2\65\67\t\3\2\2\66\65\3\2\2\2\678\3\2\2\28\66\3\2\2\28"+
		"9\3\2\2\29\20\3\2\2\2:<\7\17\2\2;:\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\f\2"+
		"\2>\22\3\2\2\2?E\4>@\2@A\7>\2\2AE\7?\2\2BC\7@\2\2CE\7?\2\2D?\3\2\2\2D"+
		"@\3\2\2\2DB\3\2\2\2E\24\3\2\2\2\t\2).\638;D\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}