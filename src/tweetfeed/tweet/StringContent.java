/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;


/**
 *
 * @author Munier
 * 
 * This is the concrete implementation for the Content of a Tweet. This class specifically implements the 140-character
 * String implementation. The reason that it was not used directly as the Content of a Tweet, is that a Tweet's content
 * in the future may include URLs, videos, pictures, etc.
 */
public class StringContent extends Content {       
    public final static int MAX_LENGTH = 140; //the 140 character limit
    private String message; //The User's message
    
    
    /**
     * Create new Content containing a String. This is not limited to 140-characters at this point, as throwing an 
     * Exception or indicating to the user at this point is too late. It should be done on the GUI. We can therefore
     * allow extensibility by truncating the contents later.
     * @param message 
     */
    public StringContent(String message) {
        this.message = message.trim(); //remove trailing or leading whitespaces
    }
    
    /**
     * Get a MAX_LENGTH (140) character restricted limit of the User's message.
     */
    public String getMessage() {
      return this.message.length() > 140? this.message.substring(0, MAX_LENGTH) : this.message ;
    }
}
