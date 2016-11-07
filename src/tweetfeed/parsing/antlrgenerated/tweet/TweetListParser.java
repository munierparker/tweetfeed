// Generated from TweetList.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.tweet;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TweetListParser extends Parser {

    static {
        RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int SEVEN_BIT_STRING = 1, GREATER_THAN = 2, NEWLINE = 3, ErrorCharacter = 4;
    public static final int RULE_tweet = 0, RULE_tweet_begin = 1;
    public static final String[] ruleNames = {
        "tweet", "tweet_begin"
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
    public ATN getATN() {
        return _ATN;
    }

    public TweetListParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class TweetContext extends ParserRuleContext {

        public Tweet_beginContext tweet_begin() {
            return getRuleContext(Tweet_beginContext.class, 0);
        }

        public TweetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tweet;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof TweetListListener) {
                ((TweetListListener) listener).enterTweet(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof TweetListListener) {
                ((TweetListListener) listener).exitTweet(this);
            }
        }
    }

    public final TweetContext tweet() throws RecognitionException {
        TweetContext _localctx = new TweetContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_tweet);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(4);
                tweet_begin();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Tweet_beginContext extends ParserRuleContext {

        public TerminalNode EOF() {
            return getToken(TweetListParser.EOF, 0);
        }

        public List<TerminalNode> SEVEN_BIT_STRING() {
            return getTokens(TweetListParser.SEVEN_BIT_STRING);
        }

        public TerminalNode SEVEN_BIT_STRING(int i) {
            return getToken(TweetListParser.SEVEN_BIT_STRING, i);
        }

        public List<TerminalNode> GREATER_THAN() {
            return getTokens(TweetListParser.GREATER_THAN);
        }

        public TerminalNode GREATER_THAN(int i) {
            return getToken(TweetListParser.GREATER_THAN, i);
        }

        public List<TerminalNode> NEWLINE() {
            return getTokens(TweetListParser.NEWLINE);
        }

        public TerminalNode NEWLINE(int i) {
            return getToken(TweetListParser.NEWLINE, i);
        }

        public Tweet_beginContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tweet_begin;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof TweetListListener) {
                ((TweetListListener) listener).enterTweet_begin(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof TweetListListener) {
                ((TweetListListener) listener).exitTweet_begin(this);
            }
        }
    }

    public final Tweet_beginContext tweet_begin() throws RecognitionException {
        Tweet_beginContext _localctx = new Tweet_beginContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_tweet_begin);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(20);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(6);
                            match(SEVEN_BIT_STRING);
                            setState(7);
                            match(GREATER_THAN);
                            setState(11);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
                            while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                                if (_alt == 1) {
                                    {
                                        {
                                            setState(8);
                                            _la = _input.LA(1);
                                            if (!(_la == SEVEN_BIT_STRING || _la == GREATER_THAN)) {
                                                _errHandler.recoverInline(this);
                                            } else {
                                                consume();
                                            }
                                        }
                                    }
                                }
                                setState(13);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
                            }
                            setState(17);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == NEWLINE) {
                                {
                                    {
                                        setState(14);
                                        match(NEWLINE);
                                    }
                                }
                                setState(19);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                        }
                    }
                    setState(22);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == SEVEN_BIT_STRING);
                setState(24);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\6\35\4\2\t\2\4\3"
            + "\t\3\3\2\3\2\3\3\3\3\3\3\7\3\f\n\3\f\3\16\3\17\13\3\3\3\7\3\22\n\3\f\3"
            + "\16\3\25\13\3\6\3\27\n\3\r\3\16\3\30\3\3\3\3\3\3\2\2\4\2\4\2\3\3\2\3\4"
            + "\35\2\6\3\2\2\2\4\26\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\7\3\2\2\t\r\7"
            + "\4\2\2\n\f\t\2\2\2\13\n\3\2\2\2\f\17\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2"
            + "\16\23\3\2\2\2\17\r\3\2\2\2\20\22\7\5\2\2\21\20\3\2\2\2\22\25\3\2\2\2"
            + "\23\21\3\2\2\2\23\24\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\26\b\3\2\2\2"
            + "\27\30\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\32\3\2\2\2\32\33\7\2\2\3"
            + "\33\5\3\2\2\2\5\r\23\30";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
