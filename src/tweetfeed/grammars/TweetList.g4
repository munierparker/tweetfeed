grammar TweetList;

@lexer::header {
package tweetfeed.parsers.antlrgenerated.tweet;
}
 
@parser::header {
package tweetfeed.parsers.antlrgenerated.tweet;
}   

tweets: tweet_begin;
tweet_begin: (SEVEN_BIT_STRING GREATER_THAN (SEVEN_BIT_STRING | GREATER_THAN)* NEWLINE*)+ EOF;

SEVEN_BIT_STRING: (SEVEN_BIT_RANGE)+;
GREATER_THAN: ('\u003E');
fragment SEVEN_BIT_RANGE: ('\u0001'..'\u0009') | ('\u000B'..'\u000C') | ('\u000E'..'\u003D') | ('\u003F'..'\u007F');
//WHITESPACE : ('\t' | ' ')+ -> skip ;
NEWLINE : ('\r' | '\n'| '\u000C'); //A, C, D

ErrorCharacter : . ;
