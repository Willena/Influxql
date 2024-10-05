/*
 * InfluxQL Java package
 * Copyright 2024 Guillaume VILLENA also known as "Willena" on GitHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

lexer grammar InfluxqlLexer;

//options {
//    caseInsensitive = true;
//}

SCOL      : ';';
DOT       : '.';
OPEN_PAR  : '(';
CLOSE_PAR : ')';
COMMA     : ',';
ASSIGN    : '=';
STAR      : '*';
PLUS      : '+';
MINUS     : '-';
TILDE     : '~';
PIPE2     : '||';
DIV       : '/';
MOD       : '%';
LT2       : '<<';
GT2       : '>>';
AMP       : '&';
PIPE      : '|';
LT        : '<';
LT_EQ     : '<=';
GT        : '>';
GT_EQ     : '>=';
EQ        : '==';
NOT_EQ1   : '!=';
NOT_EQ2   : '<>';

// Keywords
ALL : 'ALL';
ALTER : 'ALTER';
ANALYZE : 'ANALYZE';
ANY : 'ANY';
AS : 'AS';
ASC : 'ASC';
BEGIN : 'BEGIN';
BY : 'BY';
CREATE : 'CREATE';
CONTINUOUS : 'CONTINUOUS';
DATABASE : 'DATABASE';
DATABASES : 'DATABASES';
DEFAULT : 'DEFAULT';
DELETE : 'DELETE';
DESC : 'DESC';
DESTINATIONS : 'DESTINATIONS';
DIAGNOSTICS : 'DIAGNOSTICS';
DISTINCT : 'DISTINCT';
DROP : 'DROP';
DURATION : 'DURATION';
END : 'END';
EVERY : 'EVERY';
EXPLAIN : 'EXPLAIN';
FIELD : 'FIELD';
FOR : 'FOR';
FROM : 'FROM';
GRANT : 'GRANT';
GRANTS : 'GRANTS';
GROUP : 'GROUP';
GROUPS : 'GROUPS';
IN : 'IN';
INF : 'INF';
INSERT : 'INSERT';
INTO : 'INTO';
KEY : 'KEY';
KEYS : 'KEYS';
KILL : 'KILL';
LIMIT : 'LIMIT';
SHOW : 'SHOW';
MEASUREMENT : 'MEASUREMENT';
MEASUREMENTS : 'MEASUREMENTS';
NAME : 'NAME';
OFFSET : 'OFFSET';
ON : 'ON';
ORDER : 'ORDER';
PASSWORD : 'PASSWORD';
POLICY : 'POLICY';
POLICIES : 'POLICIES';
PRIVILEGES : 'PRIVILEGES';
QUERIES : 'QUERIES';
QUERY : 'QUERY';
READ : 'READ';
REPLICATION : 'REPLICATION';
RESAMPLE : 'RESAMPLE';
RETENTION : 'RETENTION';
REVOKE : 'REVOKE';
SELECT : 'SELECT';
SERIES : 'SERIES';
SET : 'SET';
SHARD : 'SHARD';
SHARDS : 'SHARDS';
SLIMIT : 'SLIMIT';
SOFFSET : 'SOFFSET';
STATS : 'STATS';
SUBSCRIPTION : 'SUBSCRIPTION';
SUBSCRIPTIONS : 'SUBSCRIPTIONS';
TAG : 'TAG';
TO : 'TO';
USER : 'USER';
USERS : 'USERS';
VALUES : 'VALUES';
WHERE : 'WHERE';
WITH : 'WITH';
WRITE : 'WRITE';

IDENTIFIER          : UNQUOTED_IDENTIFIER | QUOTED_IDENTIFIER ;
fragment UNQUOTED_IDENTIFIER :  LETTER  ( LETTER | DIGIT )* ;
fragment QUOTED_IDENTIFIER   : '"' UNICODE_CHAR UNICODE_CHAR* '"' ;

INTERGER_LITERAL: ('+' | '-')? [1-9] DIGIT*;
FLOAT_LITERAL: ( '+' | '-' )? ( '.' DIGIT DIGIT* | DIGIT DIGIT* '.' DIGIT* ) ;
STRING_LITERAL: '\'' UNICODE_CHAR* '\'';

DURATION_LITERAL: INTERGER_LITERAL DURATION_UNIT;
DURATION_UNIT : 'u' | 'µ' | 'ms' | 's' | 'm' | 'h' | 'd' | 'w';
BOOL_LITERAL: 'TRUE' | 'FALSE';
REGULAR_EXPRESSION_LITERAL: '/' UNICODE_CHAR* '/';
DATE_LITERAL: YEAR '-' MONTH '-' DAY;
DATE_TIME_LITERAL: DATE_LITERAL 'T' TIME_LITERAL;
TIME_LITERAL: HOUR ':' MINUTES_SECOND ':' MINUTES_SECOND ('.' DIGIT DIGIT DIGIT DIGIT DIGIT DIGIT)? 'Z';

fragment MINUTES_SECOND: [0-5] [0-9];
fragment HOUR: ('0' [0-9]) | ('1' [0-9]) | ('2' [0-3]);
fragment YEAR: DIGIT DIGIT DIGIT DIGIT;
fragment MONTH: ('0' [1-9]) | ('1' [0-2]);
fragment DAY: ('0' [1-9]) | ([1-2] [0-9]) | ('3' [0-1]);

SINGLE_LINE_COMMENT: '--' ~[\r\n]* ('\r'? '\n' | EOF) -> channel(HIDDEN);
MULTILINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);
SPACES: [ \u000B\t\r\n] -> channel(HIDDEN);

fragment NEWLINE_EOF : NEWLINE | EOF;
fragment HEX_DIGIT   : [0-9A-F];
fragment DIGIT       : [0-9];
fragment LETTER      : ASCI_LETTER | '_';
fragment ASCI_LETTER : [A-Z];
fragment UNICODE_CHAR: ~'\u000A';
fragment NEWLINE     : '\u000A';
