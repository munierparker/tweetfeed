/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.parsing.parsers;

/**
 *
 * @author Munier
 *
 * This interface is used in the JobConsumer.java and JobProducer.java. It enables the Consumer to parse the tweet.txt
 * file, with any implementation that the programmer chooses, as long as it implements this interface.
 *
 * This allows clean separation of interests and to decouple the parsing.
 */
public interface TweetParsable {

    public void parseTweetFile();
}
