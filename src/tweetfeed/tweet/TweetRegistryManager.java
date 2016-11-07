/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.tweet;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import tweetfeed.TweetFeed;
import tweetfeed.tweet.Tweet.ProcessedStates;

/**
 *
 * @author Munier
 *
 * This class: (a) Registers all Tweets and all Users. (b) Keeps a sanitised union of users, even if they are declared
 * partially in the user.txt and tweet.txt files. (c) Since there is no real Database with Users available, it simulates
 * a uniqueID. (d) It uses a Singleton pattern to ensure that there is only ever one "registry", which is its purpose.
 * (e) It ensures that a sorted user list can be printed, by bypassing the need to sort entire tweet lists. It achieves
 * this by simply sorting the "Keys" field storing the names and leaves all other information in tact. (f) The correct
 * order of Tweets on the Users feed is achieved adding a reference to a Tweet to all of the followers at the same time
 * that a Tweet is added by the User.
 */
public class TweetRegistryManager {

    //the Singleton manager (see above)
    private static TweetRegistryManager manager;

    // simulated uniqueID generator for users, since no real-life DB is available
    private static long simulatedIDGenerator = 0;

    //Thread-safe maps to store the Users and Tweets.
    private final ConcurrentHashMap<User, TweetList> tweetRegistry;
    private final ConcurrentHashMap<String, User> userRegistry;

    private TweetRegistryManager() {
        this.tweetRegistry = new ConcurrentHashMap<>();
        this.userRegistry = new ConcurrentHashMap<>();
    }

    // The Singleton method to ensure that only one "registry" is ever created.
    public static TweetRegistryManager getInstance() {
        if (manager == null) {
            manager = new TweetRegistryManager();
        }
        return manager;
    }

    /**
     * Get the User by his/her name since it is the only identifier given in the requirements. This method
     * implementation can easily be changed to use a unique ID once a DB is made available.
     *
     * @param name The user's name
     * @return The User object
     */
    public User getUser(String name) {
        if (name == null || name.equals("")) {
            //This is why an internalID is needed to avoid this sort of nonsense. See User.java
            TweetFeed.TweetLogger.log(Level.WARNING, "User had null name.");
            //Dont crash, rather use the DEAD_USER object.
            return User.DEAD_USER; //take care of all instances where something gets getsAUser;
        } else {
            User user = this.userRegistry.get(name.trim());
            if (user == null) {
                user = new User("" + simulatedIDGenerator++, name.trim());
                this.userRegistry.put(name.trim(), user);
            }
            return user;
        }
    }

    /**
     * Get Keep all user's Tweets in a single place. See User.java for an explanation on why Tweets are not associated
     * with the User object.
     *
     * @param userName The User's name
     * @return A list of Tweets in order of insertion that will be displayed on the User's feed. This includes Tweets
     * from other Users.
     */
    public TweetList getUsersTweetList(String userName) {
        User user = this.getUser(userName);
        if (user == null) {
            //This is why an internalID is needed to avoid this sort of nonsense. See User.java
            TweetFeed.TweetLogger.log(Level.WARNING, "User had null name.");
            return new TweetList(); //return a dummy list
        } else {
            TweetList tweetList = this.tweetRegistry.get(user);
            if (tweetList == null) {
                tweetList = new TweetList();
                this.tweetRegistry.put(user, tweetList);
            }
            return tweetList;
        }
    }

    /**
     * Add a Tweet to a User with the option to reference the same Tweet content by this Users followers.
     *
     * @param tweet The Tweet
     * @param addTweetToFollowersAsWell true if the Tweet should be added to the followers Tweet list and false if it
     * should not.
     */
    public void addTweetToUser(Tweet tweet, boolean addTweetToFollowersAsWell) {
        TweetList tweetList = this.getUsersTweetList(tweet.getOwner().getName());
        tweetList.addTweet(tweet);

        // Add the Tweet to follows if true
        if (addTweetToFollowersAsWell) {
            ConcurrentHashMap<String, User> followList = tweet.getOwner().getListOfUsersFollowingMe();
            for (Map.Entry<String, User> followers : followList.entrySet()) {
                User follower = (User) followers.getValue();
                TweetList followerTweetList = this.getUsersTweetList(follower.getName());
                followerTweetList.addTweet(tweet);
            }
        }
    }

    /**
     * Print to the Console (a) All of the Users (b) Who they follow (c) Who follows them
     */
    public void printUserFollowMap() {
        //Print who the the Users follow.
        for (Map.Entry<String, User> followers : this.userRegistry.entrySet()) {
            TweetFeed.Echo("\n" + followers.getKey() + " follows");
            Collection collection = followers.getValue().getListOfUsersIFollow().values();
            Iterator i = collection.iterator();
            while (i.hasNext()) {
                User u = (User) i.next();
                TweetFeed.Echo(" " + u.getName());
            }
        }

        TweetFeed.Echo("");

        //Print who the Users are followed by.
        for (Map.Entry<String, User> followers : this.userRegistry.entrySet()) {
            TweetFeed.Echo("\n" + followers.getKey() + " is followed by");
            Collection collection = followers.getValue().getListOfUsersFollowingMe().values();
            Iterator i = collection.iterator();
            while (i.hasNext()) {
                User u = (User) i.next();
                TweetFeed.Echo(" " + u.getName());
            }
        }
        TweetFeed.Echo("\n\n-----------------------------------------");
    }

    /**
     * Print the Tweets. This method ensures that sorting uses minimal overhead, by not rearranging only the Keys
     * containing the User's name. The correct order of the Tweets are achieved in the method "addTweetToUser".
     *
     */
    public void printTweets() {
        Enumeration<String> usersNames = this.userRegistry.keys();
        List list = Collections.list(usersNames); // create list from enumeration 
        Collections.sort(list);
        usersNames = Collections.enumeration(list);
        //No TweetFeed.Echo() used here, because its an imperative
        System.out.println("-----------------------------------------\n");
        while (usersNames.hasMoreElements()) {
            String usersName = usersNames.nextElement();
            System.out.println(usersName);
            TweetList tweetList = this.getUsersTweetList(usersName);
            for (Tweet tweet : tweetList.getList()) {
                String name = tweet.getOwner().getName();
                StringContent contents = (StringContent) tweet.getContents();
                System.out.println("\t@" + name + " " + contents.getMessage());
                tweet.setProcessedState(ProcessedStates.alreadyProcessed);
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------");
    }
}
