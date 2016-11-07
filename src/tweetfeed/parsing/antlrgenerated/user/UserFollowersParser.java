// Generated from UserFollowers.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.user;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class UserFollowersParser extends Parser {

    static {
        RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int FOLLOWS = 1, ALPHANUMERIC = 2, WHITESPACE = 3, NEWLINE = 4, GREATER_THAN = 5, COMMA = 6,
            ErrorCharacter = 7;
    public static final int RULE_userfollow = 0, RULE_user = 1, RULE_followsequence = 2;
    public static final String[] ruleNames = {
        "userfollow", "user", "followsequence"
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
    public ATN getATN() {
        return _ATN;
    }

    public UserFollowersParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class UserfollowContext extends ParserRuleContext {

        public List<UserContext> user() {
            return getRuleContexts(UserContext.class);
        }

        public UserContext user(int i) {
            return getRuleContext(UserContext.class, i);
        }

        public List<TerminalNode> FOLLOWS() {
            return getTokens(UserFollowersParser.FOLLOWS);
        }

        public TerminalNode FOLLOWS(int i) {
            return getToken(UserFollowersParser.FOLLOWS, i);
        }

        public List<FollowsequenceContext> followsequence() {
            return getRuleContexts(FollowsequenceContext.class);
        }

        public FollowsequenceContext followsequence(int i) {
            return getRuleContext(FollowsequenceContext.class, i);
        }

        public List<TerminalNode> NEWLINE() {
            return getTokens(UserFollowersParser.NEWLINE);
        }

        public TerminalNode NEWLINE(int i) {
            return getToken(UserFollowersParser.NEWLINE, i);
        }

        public UserfollowContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_userfollow;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).enterUserfollow(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).exitUserfollow(this);
            }
        }
    }

    public final UserfollowContext userfollow() throws RecognitionException {
        UserfollowContext _localctx = new UserfollowContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_userfollow);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(19);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == ALPHANUMERIC) {
                    {
                        {
                            setState(6);
                            user();
                            setState(7);
                            match(FOLLOWS);
                            setState(9);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                                case 1: {
                                    setState(8);
                                    followsequence();
                                }
                                break;
                            }
                            setState(14);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == NEWLINE) {
                                {
                                    {
                                        setState(11);
                                        match(NEWLINE);
                                    }
                                }
                                setState(16);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                        }
                    }
                    setState(21);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public static class UserContext extends ParserRuleContext {

        public TerminalNode ALPHANUMERIC() {
            return getToken(UserFollowersParser.ALPHANUMERIC, 0);
        }

        public UserContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_user;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).enterUser(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).exitUser(this);
            }
        }
    }

    public final UserContext user() throws RecognitionException {
        UserContext _localctx = new UserContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_user);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(22);
                match(ALPHANUMERIC);
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

    public static class FollowsequenceContext extends ParserRuleContext {

        public List<UserContext> user() {
            return getRuleContexts(UserContext.class);
        }

        public UserContext user(int i) {
            return getRuleContext(UserContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(UserFollowersParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(UserFollowersParser.COMMA, i);
        }

        public FollowsequenceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_followsequence;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).enterFollowsequence(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof UserFollowersListener) {
                ((UserFollowersListener) listener).exitFollowsequence(this);
            }
        }
    }

    public final FollowsequenceContext followsequence() throws RecognitionException {
        FollowsequenceContext _localctx = new FollowsequenceContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_followsequence);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(24);
                user();
                setState(29);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == COMMA) {
                    {
                        {
                            setState(25);
                            match(COMMA);
                            setState(26);
                            user();
                        }
                    }
                    setState(31);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t#\4\2\t\2\4\3\t"
            + "\3\4\4\t\4\3\2\3\2\3\2\5\2\f\n\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\7\2\24"
            + "\n\2\f\2\16\2\27\13\2\3\3\3\3\3\4\3\4\3\4\7\4\36\n\4\f\4\16\4!\13\4\3"
            + "\4\2\2\5\2\4\6\2\2#\2\25\3\2\2\2\4\30\3\2\2\2\6\32\3\2\2\2\b\t\5\4\3\2"
            + "\t\13\7\3\2\2\n\f\5\6\4\2\13\n\3\2\2\2\13\f\3\2\2\2\f\20\3\2\2\2\r\17"
            + "\7\6\2\2\16\r\3\2\2\2\17\22\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\24"
            + "\3\2\2\2\22\20\3\2\2\2\23\b\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26"
            + "\3\2\2\2\26\3\3\2\2\2\27\25\3\2\2\2\30\31\7\4\2\2\31\5\3\2\2\2\32\37\5"
            + "\4\3\2\33\34\7\b\2\2\34\36\5\4\3\2\35\33\3\2\2\2\36!\3\2\2\2\37\35\3\2"
            + "\2\2\37 \3\2\2\2 \7\3\2\2\2!\37\3\2\2\2\6\13\20\25\37";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
