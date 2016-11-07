grammar UserFollowers;

@lexer::header {
package tweetfeed.parsers.antlrgenerated.user;
}
 
@parser::header {
package tweetfeed.parsers.antlrgenerated.user;
}   

userfollow: (ALPHANUMERIC FOLLOWS followsequence? NEWLINE*)*;
followsequence : user (COMMA user?)*;
user : ALPHANUMERIC;

FOLLOWS : ('f'|'F')('o'|'O')('l'|'L')('l'|'L')('o'|'O')('w'|'W')('s'|'S');
ALPHANUMERIC : (SEVEN_BIT_RANGE)+;
fragment SEVEN_BIT_RANGE: ('\u0001'..'\u0009') | ('\u000B'..'\u000C') | ('\u000E'..'\u003D') | ('\u003F'..'\u007F');
//WHITESPACE : ('\t' | ' ')+ -> skip ;
NEWLINE : ('\r' | '\n'| '\u000C');
//NEWLINE: '\n';
GREATER_THAN : ('>');
COMMA : (',');
ErrorCharacter : . ;







