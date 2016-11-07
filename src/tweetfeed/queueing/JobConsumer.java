/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.queueing;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import tweetfeed.TweetFeed;
import tweetfeed.parsing.parsers.TweetParsable;
import tweetfeed.parsing.parsers.UserParsable;
import tweetfeed.tweet.TweetRegistryManager;

/**
 *
 * @author Munier
 *
 * JobConsumer consumes and processes jobs as put on the shared queue by JobProducer.
 */
public class JobConsumer implements Runnable {

    public static boolean GLOBAL_CONSUMER_ENABLE_FLAG; //Stop all Consumers immediately
    private static int GLOBAL_CONSUMER_ID_COUNT = 0; //Give each Consumer is own ID

    private BlockingQueue<Job> sharedQueue; ///the shared queue from TweetFeed.java
    private long localConsumerID; //The unique ID of this Consumer
    private boolean localConsumerRunFlag; //Stop only this consumer immediately

    /**
     * Create a new JobConsumer Object
     *
     * @param sharedQueue the queue shared with JobProducer
     */
    public JobConsumer(BlockingQueue<Job> sharedQueue) {
        this.localConsumerID = JobConsumer.GLOBAL_CONSUMER_ID_COUNT++; //get a unique ID
        this.sharedQueue = sharedQueue;
        this.localConsumerRunFlag = true; //allow this Consumer to run
    }

    /**
     * Run the Consumer thread
     */
    @Override
    public void run() {
        TweetFeed.Echo("Consumer started.");
        try {
            //Run into infinity, until either all Consumers are stopped or this one is stopped.
            while (JobConsumer.GLOBAL_CONSUMER_ENABLE_FLAG && this.localConsumerRunFlag) {
                Job job = sharedQueue.take(); //get the next Job on the queue
                this.processJob(job); //process it
            }

            TweetFeed.Echo("** Stopping Consumer with ID " + this.localConsumerID);

            if (this.sharedQueue.isEmpty()) {
                TweetFeed.Echo("** All Consumers have stopped");
                TweetFeed.Echo("** Shutdown");
                TweetFeed.Echo("-----------------------------------------");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process the job from the run method.
     *
     * @param job The job to be processed
     */
    private void processJob(Job job) {
        switch (job.getJobType()) {
            case JOB_PARSE_INPUT: //if its a Parse job
                Object[] parsables = (Object[]) job.getTag();
                UserParsable userParsable = (UserParsable) parsables[0]; //get the UserParsable interface
                TweetParsable tweetParsable = (TweetParsable) parsables[1]; //get the TweetParsable interface
                TweetFeed.Echo("Parsing users and tweets.");
                try {
                    userParsable.parseUserFile(); //then Parse the user file with which ever method the programmer used
                    tweetParsable.parseTweetFile(); //then Parse the tweet file with which ever method the programmer used
                } catch (Exception e) {
                    //the interface above is designed to allow a programmer to add their own implementation
                    //Just in case they didnt catch their own Exceptions, this method cannot make any assumptions
                    //So it has to catch everything unfortunately
                    TweetFeed.TweetLogger.log(Level.SEVERE, e.getMessage());
                    TweetFeed.Echo(e.getMessage());
                }
                break;

            case JOB_PRINT_TWEET_OUTPUT: //if it is a job to show the Tweet output
                TweetRegistryManager.getInstance().printTweets(); //print the tweets
                break;

            case JOB_PRINT_USER_FOLLOW_MAP: // if its a job to show how users follow each other
                TweetRegistryManager.getInstance().printUserFollowMap(); //then print the map
                break;

            case JOB_QUEUE_INSTRUCTION: //Of if the queue itself is given an instruction
                this.processInstruction(job); //do that instruction
                break;

            default:
                //Log something?
                break;
        }
    }

    /**
     * Process instructions given by the programmer to the Queue
     *
     * @param job The job to process
     */
    private void processInstruction(Job job) {
        switch (job.getInstructionType()) {
            case INSTRUCTION_SHUTDOWN_ALL_CONSUMERS_IMMEDIATELY: //shutdown all consumers
                JobConsumer.GLOBAL_CONSUMER_ENABLE_FLAG = false; //set the flag
                TweetFeed.Echo("** Stopping all consumers");
                break;
            case INSTRUCTION_SHUTDOWN_THIS_CONSUMER_IMMEDIATELY: //shutdown this consumer
                this.localConsumerRunFlag = false; //set the flag
                break;
            default:
                //log something
                break;
        }
    }
}
