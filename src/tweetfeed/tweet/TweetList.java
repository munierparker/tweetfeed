/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tweetfeed.tweet.Tweet.ProcessedStates;

/**
 *
 * @author Munier
 *
 * Store a list of Tweets for each User
 */
public class TweetList {

    // A list of Tweets
    private List<Tweet> list;

    public TweetList() {
        //Synchronised to ensure Thread safety
        this.list = Collections.synchronizedList(new ArrayList<Tweet>());
    }

    /**
     * Add the Tweet and indicate that it is now being processed. Set the timestamp for creation on the queue to "now".
     * The ProcessedStates in this implementation can be improved upon. See Tweet.java for an explanation.
     *
     * @param tweet The Tweet to add to the list
     *
     */
    public void addTweet(Tweet tweet) {
        tweet.setProcessedState(ProcessedStates.isBeingProcessed);
        tweet.setTimeStampCreationOnQueue(); //Set it to "now"
        this.list.add(tweet);
    }

    /**
     * Get the list of Tweets
     *
     * @return List of Tweets
     */
    public List<Tweet> getList() {
        return this.list;
    }
}
