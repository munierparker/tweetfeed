/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.queueing;

import java.util.concurrent.BlockingQueue;
import tweetfeed.TweetFeed;
import tweetfeed.parsing.parsers.TweetParsable;
import tweetfeed.parsing.parsers.UserParsable;

/**
 *
 * @author Munier
 *
 * The JobProducer is started from a Thread in TweetRegistry.java and uses a shared Blocking queue for thread safety.
 *
 * It simulates User's who actually create Tweets and places jobs on the queue.
 *
 * It uses the UserParsable and TweetParsable interfaces so that it can instruct the Consumer threads to parse the
 * user.txt and tweet.txt files, no matter what implementation the programmer chooses. In this case, the tool ANTLR was
 * used to create a powerful, configurable parser defined by grammar rules.
 *
 * JobProducer implements the runnable interface. When the thread starts the process, it is instructed with 3 jobs,
 * namely (a) to Parse the input files from the TweetFeed.java file (b) to print the tweet output and (c) To
 * shutdown the Consumer processing the Jobs.
 *
 * Side Note: I am not happy with this implementation as it allows the scaling of batches of parsed Users and Tweets and
 * not individual Tweets themselves. I did not have time to modify the implementation. Apologies :)
 */
public class JobProducer implements Runnable {

    //The queue referenced from TweetFeed.java, since it shared.
    private BlockingQueue<Job> sharedQueue;

    /**
     * These are interface references to the class implementing the parsing of the user.txt and tweet.txt files. This
     * allows the implementation of the parsing to be decoupled from the processing engine entirely and can be swopped
     * out with any parsing function/method.
     */
    private UserParsable userParsable;
    private TweetParsable tweetParsable;

    /**
     * Create a new JobProducer object
     *
     * @param sharedQueue The shared queue object with Consumer.java
     * @param userParsable The object implementing the UserParsable interface to parse the user.txt file
     * @param tweetParsable The object implementing the TweetParsable interface to parse the tweet.txt file
     */
    public JobProducer(BlockingQueue<Job> sharedQueue,
            UserParsable userParsable,
            TweetParsable tweetParsable) {

        this.sharedQueue = sharedQueue;
        this.userParsable = userParsable;
        this.tweetParsable = tweetParsable;
    }

    /**
     * The Runnable interface's "run" method. It adds 3 jobs to the queue. See commentary on top of file for more detail
     */
    @Override
    public void run() {
        TweetFeed.Echo("Producer started.");

        //create a job to parse user.txt and tweet.txt. The UserParsable and JobParsable objects contain references
        //to the necessary files
        try {
            // these cannot be null, the TweetFeed methods made sure of it.
            Object[] parseFiles = new Object[2]; //Set the 2 parsable interfaces to be attached a tag to the Job object
            parseFiles[0] = userParsable;
            parseFiles[1] = tweetParsable;
            Job parseJob = new Job(Job.JobType.JOB_PARSE_INPUT, parseFiles); //create a nw job
            this.sharedQueue.put(parseJob); //add it to the queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //create a job to print the Tweets
        try {
            Job printJob = new Job(Job.JobType.JOB_PRINT_TWEET_OUTPUT);
            this.sharedQueue.put(printJob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Enable to print which user's follow which users.
        /*try {
            Job printJob = new Job(Job.JobType.JOB_PRINT_USER_FOLLOW_MAP);
            this.sharedQueue.put(printJob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //create a job to stop the Consumer
        try {
            Job stopAllConsumersJob = new Job(Job.JobType.JOB_QUEUE_INSTRUCTION,
                    Job.InstructionType.INSTRUCTION_SHUTDOWN_THIS_CONSUMER_IMMEDIATELY);
            this.sharedQueue.put(stopAllConsumersJob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
