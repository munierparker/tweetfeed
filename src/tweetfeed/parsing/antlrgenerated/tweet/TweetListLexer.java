// Generated from TweetList.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.tweet;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TweetListLexer extends Lexer {

    static {
        RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int SEVEN_BIT_STRING = 1, GREATER_THAN = 2, NEWLINE = 3, ErrorCharacter = 4;
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

    public static final String[] ruleNames = {
        "SEVEN_BIT_STRING", "GREATER_THAN", "SEVEN_BIT_RANGE", "NEWLINE", "ErrorCharacter"
    };

    private static final String[] _LITERAL_NAMES = {};
    private static final String[] _SYMBOLIC_NAMES = {
        null, "SEVEN_BIT_STRING", "GREATER_THAN", "NEWLINE", "ErrorCharacter"
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

    public TweetListLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "TweetList.g4";
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
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\6\33\b\1\4\2\t\2"
            + "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\17\n\2\r\2\16\2\20\3\3\3\3\3"
            + "\4\5\4\26\n\4\3\5\3\5\3\6\3\6\2\2\7\3\3\5\4\7\2\t\5\13\6\3\2\4\6\2\3\13"
            + "\r\16\20?A\u0081\4\2\f\f\16\17\32\2\3\3\2\2\2\2\5\3\2\2\2\2\t\3\2\2\2"
            + "\2\13\3\2\2\2\3\16\3\2\2\2\5\22\3\2\2\2\7\25\3\2\2\2\t\27\3\2\2\2\13\31"
            + "\3\2\2\2\r\17\5\7\4\2\16\r\3\2\2\2\17\20\3\2\2\2\20\16\3\2\2\2\20\21\3"
            + "\2\2\2\21\4\3\2\2\2\22\23\7@\2\2\23\6\3\2\2\2\24\26\t\2\2\2\25\24\3\2"
            + "\2\2\26\b\3\2\2\2\27\30\t\3\2\2\30\n\3\2\2\2\31\32\13\2\2\2\32\f\3\2\2"
            + "\2\5\2\20\25\2";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
