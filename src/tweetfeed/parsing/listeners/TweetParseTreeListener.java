/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.parsing.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import tweetfeed.TweetFeed;
import tweetfeed.tweet.StringContent;
import tweetfeed.tweet.Tweet;
import tweetfeed.tweet.User;
import tweetfeed.tweet.TweetRegistryManager;

/**
 *
 * @author Munier
 *
 * TweetParseTreeListener receives events when CustomTweetParser.java walks the ParseTree as generated by the
 * LexicalAnalyser looking for recognisable tokens in the tweet.txt file
 *
 * This grammar is capable of recognising 7-bit strings in both the message and user's name, as well as spaces in the
 * user's name.
 *
 * The grammar needs to be fixed to allow the > sign to be inside the tweet without cutting off the previous tokens.
 *
 *
 */
public class TweetParseTreeListener implements ParseTreeListener {

    //the lexical analyser that reads the tweet.txt file
    private Lexer lexer;

    //the previous node received from the lexical analyser
    private TerminalNode previousNode;
    //the current User object that is read from user.txt as the rest of the list is uncovered.
    private User currentUserBeingParsed;
    //The previous token's rule name used to identify what the current token should be used for.
    private String previousTokenLexerRuleName;

    //a set of flags to decide how to display error output to the console
    private boolean printErrors = true;
    private boolean bypassUserInputOnError = false;
    private boolean firstTimeError = true;

    /**
     * Create a ParseTreeListener used with the Parser generated by the ANTLR grammar
     *
     * @param lexer the lexer object that will identify tokens in tweet.txt
     */
    public TweetParseTreeListener(Lexer lexer) {
        this.lexer = lexer;
        this.currentUserBeingParsed = null;
        this.previousNode = null;
        this.previousTokenLexerRuleName = "NEWLINE";
    }

    /**
     * When a node is encountered that is recognise and the associated token is returns. This method uses the ANTLR
     * grammar defined rules to identify when a
     *
     * @param tn The current node in the parse tree
     */
    @Override
    public void visitTerminal(TerminalNode tn) {
        String lexerRuleName = this.getLexerRuleName(tn); //get the name of the token

        switch (lexerRuleName) {
            case "SEVEN_BIT_STRING": //if it is a 7-bit string
                if (this.previousTokenLexerRuleName.equals("NEWLINE")) { //and the previous one was a new line
                    //then this token is a user. So set the current user from the name
                    this.currentUserBeingParsed = TweetRegistryManager.getInstance().getUser(tn.getText());
                }
                break;
            case "GREATER_THAN":
                //System.out.println("GREATER_THAN detected." + this.previousNode.getText());
                break;
            case "EOF":
            case "NEWLINE": //If there is a new line
                //and the previous token was a string
                if (this.previousTokenLexerRuleName.equals("SEVEN_BIT_STRING")) {
                    //then the contents of this is a Tweet. Create the tweet
                    Tweet tweet = new Tweet(this.currentUserBeingParsed, new StringContent(this.previousNode.getText()));
                    //and register it
                    TweetRegistryManager.getInstance().addTweetToUser(tweet, true);
                }
                break;
            default:
                break;
        }
        this.previousNode = tn;
        this.previousTokenLexerRuleName = this.getLexerRuleName(tn);
        //System.out.println("Visit Terminal:" + this.lexer.getVocabulary().getSymbolicName(tn.getSymbol().getType()) + ", " + tn.getText());

    }

    //Get the lexer rule name from the token
    public String getLexerRuleName(TerminalNode tokenNode) {
        return this.lexer.getVocabulary().getSymbolicName(tokenNode.getSymbol().getType());
    }

    /**
     * If an error occurs, tell the user: (a) which file (b) what the name of the error is and specifically which line
     * and index (c) how to fix the error for future (d) On a first time error what the known issues are
     *
     * @param en the Error Node
     *
     * NOTE: This block is not meant for Production Code but for the Alan Gray assessors.
     */
    @Override
    public void visitErrorNode(ErrorNode en) {
        if (this.printErrors) {
            StringBuilder output = new StringBuilder();
            output.append(TweetFeed.ANSI_RED
                    + "-----------------------------------------------------------------\n");
            output.append(TweetFeed.ANSI_RED
                    + "An error was encountered while parsing the Tweets file.\n");
            output.append(TweetFeed.ANSI_RED
                    + "The token '" + this.getLexerRuleName(en));
            output.append(TweetFeed.ANSI_RED
                    + "' of [char] '" + (en.getText().equals("\n") ? "\\n" : en.getText()));
            output.append(TweetFeed.ANSI_RED
                    + "' is at line:" + en.getSymbol().getLine() + " and index: ");
            output.append(TweetFeed.ANSI_RED + en.getSymbol().getTokenIndex());
            if (this.firstTimeError) {
                output.append("\n\n" + TweetFeed.ANSI_RED
                        + "Modify the grammar (tweetfeed/grammars/UserFollow.g4)\n");
                output.append(TweetFeed.ANSI_RED
                        + "with additional rules for improved fault tolerance.\n");
                output.append("\n" + TweetFeed.ANSI_RED
                        + "Known issues: not truncating tweets when a '>' sign is present\n");
                this.firstTimeError = false;
            }
            output.append("\n" + TweetFeed.ANSI_RED
                    + "-----------------------------------------------------------------");
            System.out.println(output.toString());
            this.showErrorMenu();

        }
    }

    /**
     * Show the user a menu to decide how to proceed with errors. NOTE: This block is not meant for Production Code but
     * for the Alan Gray assessors.
     */
    private void showErrorMenu() {
        String input = "";
        do {
            System.out.println(TweetFeed.ANSI_GREEN + "      MENU: SELECT AN OPTION");
            System.out.println(TweetFeed.ANSI_GREEN + "(<C> Continue and show future errors");
            System.out.println(TweetFeed.ANSI_GREEN + "(<I> Continue, but ignore future errors");
            System.out.println(TweetFeed.ANSI_GREEN + "(<E> Exit");
            System.out.println(TweetFeed.ANSI_RESET); //reset back to original colour scheme

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();
            } catch (IOException e) {
                TweetFeed.TweetLogger.log(Level.SEVERE, e.getMessage());
                System.exit((0));
            }

        } while (!(input.equals("C") || input.equals("c")
                || input.equals("I") || input.equals("i")
                || input.equals("E") || input.equals("e")));

        switch (input) {
            case "C":
            case "c":
                break;
            case "I":
            case "i":
                this.printErrors = false;
                break;
            case "E":
            case "e":
                System.exit(0);
                break;
            default:
                this.showErrorMenu();
                break;
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext prc) {
        //System.out.println("Parser Enter Every Rule:" + prc.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext prc) {
        //System.out.println("Exit Every Rule:" + prc.getText());
    }

}