TweetFeed, by Munier Parker (2016).

Instructions to Run:
- Go to the “dist” folder.
- Make sure “java.exe” is in the Classpath
- Make sure that at least Java runtime version 1.8.0.91 is used 64-bit.
- Run it with “java -jar TweetFeed.jar <user file> <tweet file>”
- <user file> = user.txt (or other)
- <tweet file> = tweet.txt (or other)
- Feel free to change or modify the text files

Known Issues:
- The parsers are generated by a grammar using ANTLR. The grammars have not been fully developed for all permutations or errors.
- User.txt errors: 
(a) It registers new lines at the start of the file as an error, but ignores them.
(b) It does not allow 7-bit characters or spaces in the User’s name yet.
(c) A comma ending after a list of names, not followed by a name is registered as an error, but is ignored.
- Tweet.txt errors:
(a) The parser needs to be updated to allow the greater than sign without
truncating a part of the message. This is due to how ANTLRs tokens work and it needs to be updated.
(b) For both User.txt and Tweet.txt, the grammar currently allows \U00C as a valid character. This should be removed.


Improvements:
- Due to time contains, I did not include Unit tests. This should be done.
- Although I provide a clean separation of interests through interfaces and decoupled architectures, I would have liked to have explored in more detail if restructuring the project to an MVC pattern would make more sense.
- I would have liked to have used Storm Cluster (http://storm.apache.org/index.html) to allow super scalability on multiple nodes. I did not have time for this however.
- I would have liked to have used Log4J more extensively for logging.

Licence:
- Please see LICENCE.  

