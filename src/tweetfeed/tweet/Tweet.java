/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;

import java.time.LocalDateTime;

/**
 *
 * @author Munier
 *
 * A Tweet object storing all of its associated information. A Tweet ProcessedState is included so that it can be
 * updated during runtime if its status needs to be queries. The eventual implementation can be optimised, as the Queues
 * should be modified to process Tweets as they are put on the Queue and not the result of a parsed set of queues at the
 * same time. However, that will not change the implementation of this Tweet class.
 *
 * The timestamps for CreationByUser and CreationOnQueue are not user specified, but are flagged to be set to the
 * current time on the server. This ensures that it is not tamperable.
 *
 * The Contents of a Tweet is an abstract class to ensure that future content e.g. videos, URLs, pictures, etc can be
 * added. In this way, the content of the tweet is decoupled from the Tweet itself. A 140-character Tweet is simply an
 * extension of "Content".
 *
 */
public class Tweet {

    public static enum ProcessedStates {
        awaitingProcessing, isBeingProcessed, alreadyProcessed
    };

    private String internalID; // Unique internal identifier
    private ProcessedStates processedState; //how far is this Tweet in being processed?
    private LocalDateTime timestampCreationByUser;
    private LocalDateTime timestampCreationOnQueue;
    private User owner; //User who created the Tweet, even if posted to others
    private Content contents; //this does not need to be threadsafe, once the tweet is created, the contents will not be mutable.

    /**
     * Create a Tweet object
     *
     * @param owner The User who created the Tweet
     * @param contents The abstract base-class to retrieve the contents
     */
    public Tweet(User owner, Content contents) {
        this.internalID = "";
        this.processedState = ProcessedStates.awaitingProcessing;
        this.timestampCreationByUser = LocalDateTime.now(); //always referenced to synched server time
        this.owner = owner;
        this.contents = contents;
    }

    /**
     * Get the User who owns the Tweet
     *
     * @return The User
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * Get an abstract base class reference to the Contents of the Tweet
     *
     * @return Content reference
     */
    public Content getContents() {
        return this.contents;
    }

    /**
     * Get the Tweets unique internal identifier
     *
     * @return
     */
    public String getInternalID() {
        return this.internalID;
    }

    /**
     * Change the Processed State to where indicate how close to completion the Tweet is.
     *
     * @param processedState The new ProcessedState
     */
    public void setProcessedState(ProcessedStates processedState) {
        this.processedState = processedState;
    }

    /**
     * Get the ProcessedState
     *
     * @return The ProcessedState of the Tweet
     */
    public ProcessedStates getProcessedState() {
        return this.processedState;
    }


    /**
     * Get the Server timestamp for when the Object was created
     * @return A LocalDateTime object
     */
    public LocalDateTime getTimeStampCreationByUser() {
        return this.timestampCreationByUser;
    }

    /**
     * Flag that the Tweet was just put on the Queue. This is not user definable, as it prevents tampering.
     */
    public void setTimeStampCreationOnQueue() {
        this.timestampCreationOnQueue = LocalDateTime.now();
    }

    /**
     * Get the timestamp that the Tweet was created on the Queue
     * @return A LocalDateTime object
     */
    public LocalDateTime getTimeStampCreationOnQueue() {
        return this.timestampCreationOnQueue;
    }
}
