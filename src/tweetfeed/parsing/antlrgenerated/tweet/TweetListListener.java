// Generated from TweetList.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.tweet;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by {@link TweetListParser}.
 */
public interface TweetListListener extends ParseTreeListener {

    /**
     * Enter a parse tree produced by {@link TweetListParser#tweet}.
     *
     * @param ctx the parse tree
     */
    void enterTweet(TweetListParser.TweetContext ctx);

    /**
     * Exit a parse tree produced by {@link TweetListParser#tweet}.
     *
     * @param ctx the parse tree
     */
    void exitTweet(TweetListParser.TweetContext ctx);

    /**
     * Enter a parse tree produced by {@link TweetListParser#tweet_begin}.
     *
     * @param ctx the parse tree
     */
    void enterTweet_begin(TweetListParser.Tweet_beginContext ctx);

    /**
     * Exit a parse tree produced by {@link TweetListParser#tweet_begin}.
     *
     * @param ctx the parse tree
     */
    void exitTweet_begin(TweetListParser.Tweet_beginContext ctx);
}
