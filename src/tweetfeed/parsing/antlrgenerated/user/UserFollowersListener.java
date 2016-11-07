// Generated from UserFollowers.g4 by ANTLR 4.5.3
package tweetfeed.parsing.antlrgenerated.user;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by {@link UserFollowersParser}.
 */
public interface UserFollowersListener extends ParseTreeListener {

    /**
     * Enter a parse tree produced by {@link UserFollowersParser#userfollow}.
     *
     * @param ctx the parse tree
     */
    void enterUserfollow(UserFollowersParser.UserfollowContext ctx);

    /**
     * Exit a parse tree produced by {@link UserFollowersParser#userfollow}.
     *
     * @param ctx the parse tree
     */
    void exitUserfollow(UserFollowersParser.UserfollowContext ctx);

    /**
     * Enter a parse tree produced by {@link UserFollowersParser#user}.
     *
     * @param ctx the parse tree
     */
    void enterUser(UserFollowersParser.UserContext ctx);

    /**
     * Exit a parse tree produced by {@link UserFollowersParser#user}.
     *
     * @param ctx the parse tree
     */
    void exitUser(UserFollowersParser.UserContext ctx);

    /**
     * Enter a parse tree produced by {@link UserFollowersParser#followsequence}.
     *
     * @param ctx the parse tree
     */
    void enterFollowsequence(UserFollowersParser.FollowsequenceContext ctx);

    /**
     * Exit a parse tree produced by {@link UserFollowersParser#followsequence}.
     *
     * @param ctx the parse tree
     */
    void exitFollowsequence(UserFollowersParser.FollowsequenceContext ctx);
}
