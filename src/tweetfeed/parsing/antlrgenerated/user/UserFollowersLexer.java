// Generated from UserFollowers.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.user;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class UserFollowersLexer extends Lexer {

    static {
        RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int FOLLOWS = 1, ALPHANUMERIC = 2, WHITESPACE = 3, NEWLINE = 4, GREATER_THAN = 5, COMMA = 6,
            ErrorCharacter = 7;
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

    public static final String[] ruleNames = {
        "FOLLOWS", "ALPHANUMERIC", "WHITESPACE", "NEWLINE", "GREATER_THAN", "COMMA",
        "ErrorCharacter"
    };

    private static final String[] _LITERAL_NAMES = {};
    private static final String[] _SYMBOLIC_NAMES = {
        null, "FOLLOWS", "ALPHANUMERIC", "WHITESPACE", "NEWLINE", "GREATER_THAN",
        "COMMA", "ErrorCharacter"
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

    public UserFollowersLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "UserFollowers.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\t-\b\1\4\2\t\2\4"
            + "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2"
            + "\3\2\3\2\3\3\6\3\33\n\3\r\3\16\3\34\3\4\6\4 \n\4\r\4\16\4!\3\4\3\4\3\5"
            + "\3\5\3\6\3\6\3\7\3\7\3\b\3\b\2\2\t\3\3\5\4\7\5\t\6\13\7\r\b\17\t\3\2\n"
            + "\4\2HHhh\4\2QQqq\4\2NNnn\4\2YYyy\4\2UUuu\5\2\62;C\\c|\4\2\13\13\"\"\4"
            + "\2\f\f\16\17.\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"
            + "\2\2\2\r\3\2\2\2\2\17\3\2\2\2\3\21\3\2\2\2\5\32\3\2\2\2\7\37\3\2\2\2\t"
            + "%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17+\3\2\2\2\21\22\t\2\2\2\22\23\t\3"
            + "\2\2\23\24\t\4\2\2\24\25\t\4\2\2\25\26\t\3\2\2\26\27\t\5\2\2\27\30\t\6"
            + "\2\2\30\4\3\2\2\2\31\33\t\7\2\2\32\31\3\2\2\2\33\34\3\2\2\2\34\32\3\2"
            + "\2\2\34\35\3\2\2\2\35\6\3\2\2\2\36 \t\b\2\2\37\36\3\2\2\2 !\3\2\2\2!\37"
            + "\3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\b\4\2\2$\b\3\2\2\2%&\t\t\2\2&\n\3\2\2"
            + "\2\'(\7@\2\2(\f\3\2\2\2)*\7.\2\2*\16\3\2\2\2+,\13\2\2\2,\20\3\2\2\2\5"
            + "\2\34!\3\b\2\2";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
