/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Munier
 *
 * An object for a User who sends or receives Tweets. It uses an internalID field to uniquely identify a User, because a
 * "name" such as Alan is a poor identifier. There could be many "Alans" and this class and all of the code dependent on
 * it would have to carefully revised/considered. Initially I used a long, instead of a string. However, this makes the
 * class less reusable, as the Alan Gray's databases may use something other than a number.
 *
 * Note: Tweets are intentionally kept separate from User and is not stored in this class as it would couple them too
 * tightly which is bad for reusability. For this reason, the Tweet objects are kept and managed by the
 * TweetRegistryManager Class.
 *
 */
public class User {

    /**
     * Global constants that point to an object to a valid object if an illegal state for the object is encountered.
     * This is design to prevent crashes or null objects.
     *
     * UN_INITIALISED_INTERNAL_ID is used as a replacement InternalID UN_INITIALISED_NAME is used as a replacement name
     */
    //Use UUID to prevent a user from mimicking a predefined string, in which case Tweets will actually go to that user
    public final static String UN_INITIALISED_INTERNAL_ID = UUID.randomUUID().toString();
    public final static String UN_INITIALISED_NAME = UUID.randomUUID().toString();
    
    //A null User object can be catastrophic. Rather point it to a dummy user that can be detected and logged
    public final static User DEAD_USER = new User(UN_INITIALISED_INTERNAL_ID, UN_INITIALISED_NAME);
    private String internalID;// Unique internalID. See above
    private String name; //The user's name

    /**
     * The word "follow" and "follower" can result in very confusing relationships and if the wrong one is used, will
     * result in bugs. I decided to be more verbose with these variable name, to ensure that the relationship is clear
     * and easy to follow.
     *
     * I also decided to store both relationships i.e. who "i" follow and who follows "me". The reason for this is two
     * fold: (a) The base computation for the relationship is already being done during runtime. There is a small
     * computation price to simply cache the inverse relationship. (b) The additional memory is a small price to pay as
     * opposed to large scale processing afterwards to retrieve the same information.
     *
     * A ConcurrentHashMap is used to ensure that if multiple threads are being used to read/write that a Users follow
     * information can be updated on the fly.
     */
    private ConcurrentHashMap<String, User> listOfUsersIFollow;
    private ConcurrentHashMap<String, User> listOfUsersFollowingMe;

    /**
     * Create a user by specifying an internalID, usually obtained from a company Database.
     *
     * @param internalID The unique identifier for this user
     * @param name The user's displayable name
     */
    public User(String internalID, String name) {
        //prevent the internalID and name from being null
        this.internalID = (internalID == null ? User.UN_INITIALISED_INTERNAL_ID : internalID);
        this.name = (name == null ? User.UN_INITIALISED_NAME : name.trim());
        //maps of the "follow/following" relationships
        this.listOfUsersIFollow = new ConcurrentHashMap<>();
        this.listOfUsersFollowingMe = new ConcurrentHashMap<>();
    }

    /**
     * Indicate when a user is following "me" = this user
     *
     * @param user The user who is now following "me"
     */
    public void followMe(User user) {
        //ensure nothing used is null, so need to catch an Exception
        //I also cant follow myself, this will become cyclical
        if (user != null && user != this && user.internalID != null) {
            if (!listOfUsersFollowingMe.containsKey(user.getInternalID())) {
                this.listOfUsersFollowingMe.put(user.getInternalID(), user);
            }
        }
    }

    /**
     * Indicate when this user is following another user
     *
     * @param user The user to follow.
     */
    public void follow(User user) {
        //ensure nothing used is null, so need to catch an Exception
        if (user != null && user != this && this.internalID != null) {
            if (!listOfUsersIFollow.containsKey(user.getInternalID())) {
                this.listOfUsersIFollow.put(user.getInternalID(), user);
                user.followMe(this);
            }
        }
    }

    /**
     *
     * @return The internalID
     */
    public String getInternalID() {
        return this.internalID;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return The users, that this user follows.
     */
    public ConcurrentHashMap getListOfUsersIFollow() {
        return this.listOfUsersIFollow;
    }

    /**
     *
     * @return The users, who are following this user.
     */
    public ConcurrentHashMap getListOfUsersFollowingMe() {
        return this.listOfUsersFollowingMe;
    }

}
