/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;

/**
 *
 * @author Munier
 * 
 * This class acts as a base-class container for the contents of a Tweet. It is not concrete, so as to allow the Tweet
 * to be extended with other types of content in the future. As long as the Tweet processing engine recognises the
 * Content object, it does not need to be concerned with it during processing. The only point at which is becomes
 * relevant is when a the Content must be rendered/displayed.
 */
public abstract class Content {

}
