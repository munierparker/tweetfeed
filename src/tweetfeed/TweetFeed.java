/*
 * 
 * TweetFeed. Munier Parker, 2016.
 *
 */
package tweetfeed;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import tweetfeed.queueing.JobProducer;
import tweetfeed.queueing.JobConsumer;
import tweetfeed.parsing.parsers.CustomTweetParser;
import tweetfeed.parsing.parsers.CustomUserParser;
import tweetfeed.queueing.Job;

/**
 * @author Munier
 *
 * This is the Driver Class.
 *
 * Basic Operation: It uses the Producer/Consumer pattern with an unbounded blocking queue to generate Tweets from
 * tweet.txt and uses Consumers to process them. All of the initial setup is done here to pass control to the queues.
 *
 * Further, it does the following: (a) Accepts 2 parameters on the commandline for the user.text file and tweet.txt
 * respectively (b) Prints usage information if the commandline parameters are incorrectly specified. (c) Ensures that
 * the files exist before attempting to continue. (c) Creates the Queue, declares custom parsers for the syntax of the
 * user.txt & tweet.txt and starts the threads. (d) The threads are sent a shutdown signal in the MessageProducer class.
 */
public class TweetFeed {

    //Global static to decide if output should be enabled or not
    public static boolean echoEnabled = true;
    //Global static to a strongly referenced Logger
    public static Logger TweetLogger = Logger.getLogger("TweetLogger");
    // Objects for user.txt and tweet.txt
    private File userFile;
    private File tweetFile;
    //decide if we should 

    //a set of colours to highlight menu or error output to the console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * This class is simply used to call the member methods and start the process.
     */
    public TweetFeed() {
    }

    /**
     * Display a message if echo is enabled. This should be extended to echo error messages, all messages, etc.
     *
     * @param message The message
     */
    public static void Echo(String message) {
        if (echoEnabled) {
            System.out.println(message);
        }
    }

    /**
     * Offer the user the correct syntax to use TweetFeed
     */
    public void printUsage() {
        //We can't "Echo" this, it is fundamentally necessary
        System.out.println("----------------------------------------------------------");
        System.out.println("   Commandline usage:\n");
        System.out.println("   java -jar TweetFeed.jar <file1> <file2>\n");
        System.out.println("   <file1> is the user text file.");
        System.out.println("   <file2> is the tweet text file.");
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Ensure that the files exist in the file system.
     *
     * @param userFileName A 7-bit ASCII text file containing a description of users and their followers.
     * @param tweetFileName A 7-bit ASCII text file containing Tweets from the users.
     * @return Returns true is all files exist or false if there is an error.
     */
    private boolean allFilesExist(String userFileName, String tweetFileName) {
        boolean allFilesExist = true;
        if (userFileName.equals("") || userFileName == null || tweetFileName.equals("") || tweetFileName == null) {
            return false;
        } else {
            try {
                this.userFile = new File(userFileName);
                this.tweetFile = new File(tweetFileName);
            } catch (NullPointerException npe) {
                TweetFeed.TweetLogger.log(Level.SEVERE, npe.getMessage());
                TweetFeed.Echo(npe.getMessage());
                System.exit(0); //fatal error
            }

            try {
                if (!userFile.exists()) {
                    System.out.println(TweetFeed.ANSI_GREEN
                            + "The file '" + userFileName + "' does not exist. Please try again."
                            + TweetFeed.ANSI_RESET
                    );
                    allFilesExist = false;
                }

                if (!tweetFile.exists()) {
                    System.out.println(TweetFeed.ANSI_GREEN
                            + "The file '" + tweetFileName + "' does not exist. Please try again."
                            + TweetFeed.ANSI_RESET);
                    allFilesExist = false;
                }
            } catch (SecurityException se) {
                TweetFeed.TweetLogger.log(Level.SEVERE, se.getMessage());
                System.exit(0); //fatal error
            }
        }
        return allFilesExist;
    }

    /**
     * Create the BlockingQueue, which is used to ensure thread safety with objects putting and getting objects from the
     * queue. It is also unbounded, so that it will continue to grow, until memory runs out.
     *
     * The Custom Parsers are described in more detail in CustomTweetParser.java and CustomUserParser.java
     *
     * This method uses the Producer/Consumer pattern. More information on their operation is available in the
     * respective classes.
     */
    public void startProcessing() {
        TweetFeed.Echo("-----------------------------------------");
        TweetFeed.Echo("    Starting Producer(s)/Consumer(s)");
        TweetFeed.Echo("-----------------------------------------");

        BlockingQueue<Job> sharedQueue = null;
        try {
            //Blocking = thread safe. Linked = Unbounded = large as memory allows. 
            sharedQueue = new LinkedBlockingQueue<>();
        } catch (IllegalStateException ise) {
            TweetFeed.TweetLogger.log(Level.SEVERE, ise.getMessage());
            TweetFeed.Echo(ise.getMessage());
            //System.exit(0);
        }
        /**
         * Custom Parsers to parse each text file. The parsers are needed to break the text files into usable tokens to
         * create objects. The architecture allows clean decoupling so that: (a) The syntax of the text file description
         * can change. (b) The implementation supporting it can change.
         *
         * They implement the "UserParsable" and "TweetParsable" interfaces, respectively. This is required by the
         * MessageProducer so that it can Parse user.txt and tweet.txt using any implementation, as long as it achieves
         * the goal. This means that it is cleanly decoupled and that the implementation can be safely swopped out with
         * something else.
         */
        CustomUserParser userParser = new CustomUserParser(this.userFile);
        CustomTweetParser tweetParser = new CustomTweetParser(this.tweetFile);

        /**
         * The Producer is used to put jobs on the queue. In this implementation it simulates real users as they would
         * create Tweets and submit them for processing. The parsers are needed to extract usable token from the the
         * text files and use them to create objects.
         */
        JobProducer producer = new JobProducer(sharedQueue, userParser, tweetParser);
        JobConsumer consumer = new JobConsumer(sharedQueue);

        //Enable all consumers. By default they are set disabled, to prevent processing unless explicitly allowed to.
        JobConsumer.GLOBAL_CONSUMER_ENABLE_FLAG = true;
        try {

            new Thread(producer).start();
            new Thread(consumer).start();
        } catch (IllegalThreadStateException itse) {
            TweetFeed.TweetLogger.log(Level.SEVERE, itse.getMessage());
            TweetFeed.Echo(itse.getMessage());
            //System.exit(0);
        }
    }

    /**
     * Start TweetFeed with parameters user.txt and tweet.txt as arguments
     *
     * @param args user.txt and tweet.txt as arguments
     */
    public static void main(String[] args) {
        TweetFeed tweetFeed = new TweetFeed();
        if (args.length < 2) { // if there are less than two arguments in the commandline, show the user usage info.
            tweetFeed.printUsage();
        } else if (tweetFeed.allFilesExist(args[0], args[1])) { // if all files exist, then start processing
            tweetFeed.startProcessing();
        } else {
            tweetFeed.printUsage(); // Otherwise tell the user and show user usage info.
        }
    }
}
