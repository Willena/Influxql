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

//parser grammar InfluxqlParser;
grammar InfluxqlParser;
//options {
//    tokenVocab = InfluxqlLexer;
//}

//lexer grammar InfluxqlLexer;

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
DIV       : '/';
MOD       : '%';
LT2       : '<<';
GT2       : '>>';
AMP       : '&';
LT        : '<';
LT_EQ     : '<=';
GT        : '>';
GT_EQ     : '>=';
EQ        : '==';
NOT_EQ1   : '!=';
NOT_EQ2   : '<>';
REG_MATCH: '=~';
OR_EX: '^';
PIPE: '|';
PIPE2: '||';

// Keywords
AND: 'AND';
OR: 'OR';
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

INTERGER_LITERAL: ('+' | '-')? [1-9] DIGIT*;
FLOAT_LITERAL: ( '+' | '-' )? ( '.' DIGIT DIGIT* | DIGIT DIGIT* '.' DIGIT* ) ;
STRING_LITERAL: '\'' UNICODE_CHAR* '\'';

DURATION_LITERAL: INTERGER_LITERAL+ DURATION_UNIT;
DURATION_UNIT : 'u' | 'µ' | 'ms' | 's' | 'm' | 'h' | 'd' | 'w';
BOOL_LITERAL: 'TRUE' | 'FALSE';
REGULAR_EXPRESSION_LITERAL: '/' UNICODE_CHAR* '/';
fragment TIME_LITERAL: HOUR ':' MINUTES_SECOND ':' MINUTES_SECOND ('.' DIGIT DIGIT DIGIT DIGIT DIGIT DIGIT)? 'Z';
fragment DATE_LITERAL: YEAR '-' MONTH '-' DAY;
DATE_TIME_LITERAL: DATE_LITERAL 'T' TIME_LITERAL;

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
fragment UNICODE_CHAR: ~[ \u000A\u000B\t\r\n];
fragment NEWLINE     : '\u000A';

IDENTIFIER: UNQUOTED_IDENTIFIER | QUOTED_IDENTIFIER ;
fragment UNQUOTED_IDENTIFIER :  UNICODE_CHAR  UNICODE_CHAR* ;
fragment QUOTED_IDENTIFIER   : '"' UNQUOTED_IDENTIFIER UNQUOTED_IDENTIFIER* '"' ;

// ------------- PARSER ---------------------
query : statement (';' statement )* EOF;

statement : alter_retention_policy_stmt |
                      create_continuous_query_stmt |
                      create_database_stmt |
                      create_retention_policy_stmt |
                      create_subscription_stmt |
                      create_user_stmt |
                      delete_stmt |
                      drop_continuous_query_stmt |
                      drop_database_stmt |
                      drop_measurement_stmt |
                      drop_retention_policy_stmt |
                      drop_series_stmt |
                      drop_shard_stmt |
                      drop_subscription_stmt |
                      drop_user_stmt |
                      explain_stmt |
                      grant_stmt |
                      kill_query_statement |
                      show_continuous_queries_stmt |
                      show_databases_stmt |
                      show_field_keys_stmt |
                      show_grants_stmt |
                      show_measurements_stmt |
                      show_queries_stmt |
                      show_retention_policies |
                      show_series_stmt |
                      show_shard_groups_stmt |
                      show_shards_stmt |
                      show_subscriptions_stmt|
                      show_tag_keys_stmt |
                      show_tag_values_stmt |
                      show_users_stmt |
                      revoke_stmt |
                      select_stmt ;

alter_retention_policy_stmt  : ALTER RETENTION POLICY policy_name on_clause
                               retention_policy_option
                               ( retention_policy_option )?
                               ( retention_policy_option )?
                               ( retention_policy_option )?;
create_continuous_query_stmt : CREATE CONTINUOUS QUERY query_name on_clause
                                ( RESAMPLE resample_opts )?
                                BEGIN select_stmt END;
query_name                   : IDENTIFIER;
resample_opts                : (every_stmt for_stmt | every_stmt | for_stmt);
every_stmt                   : EVERY DURATION_LITERAL;
for_stmt                     : FOR DURATION_LITERAL;
create_database_stmt : CREATE DATABASE db_name
                        ( WITH
                            ( retention_policy_duration )?
                            ( retention_policy_replication )?
                            ( retention_policy_shard_group_duration )?
                            ( retention_policy_name )?
                        )?;
create_retention_policy_stmt : CREATE RETENTION POLICY policy_name on_clause
                                retention_policy_duration
                                retention_policy_replication
                                ( retention_policy_shard_group_duration )?
                                ( DEFAULT )?;
create_subscription_stmt : CREATE SUBSCRIPTION subscription_name ON db_name DOT retention_policy DESTINATIONS (ANY|ALL) host ( COMMA host)*;
create_user_stmt : CREATE USER user_name WITH PASSWORD password
                   ( WITH ALL PRIVILEGES )?;
delete_stmt : DELETE ( from_clause | where_clause | from_clause where_clause );
drop_continuous_query_stmt : DROP CONTINUOUS QUERY query_name on_clause;
drop_database_stmt : DROP DATABASE db_name;
drop_measurement_stmt : DROP MEASUREMENT measurement;
drop_retention_policy_stmt : DROP RETENTION POLICY policy_name on_clause;
drop_series_stmt : DROP SERIES ( from_clause | where_clause | from_clause where_clause );
drop_shard_stmt : DROP SHARD ( shard_id );
drop_subscription_stmt : DROP SUBSCRIPTION subscription_name ON db_name DOT retention_policy;
drop_user_stmt : DROP USER user_name;
explain_stmt : EXPLAIN ( ANALYZE )? select_stmt;
grant_stmt : GRANT privilege ( on_clause )? to_clause;
kill_query_statement : KILL QUERY query_id;
show_continuous_queries_stmt : SHOW CONTINUOUS QUERIES;
show_databases_stmt : SHOW DATABASES;
show_field_keys_stmt : SHOW FIELD KEYS ( from_clause )?;
show_grants_stmt : SHOW GRANTS FOR user_name;
show_measurements_stmt : SHOW MEASUREMENTS (on_clause)? ( with_measurement_clause )? ( where_clause )? ( limit_clause )? ( offset_clause )?;
show_queries_stmt : SHOW QUERIES;
show_retention_policies : SHOW RETENTION POLICIES on_clause;
show_series_stmt : SHOW SERIES ( from_clause )? ( where_clause )? ( limit_clause )? ( offset_clause )?;
show_shard_groups_stmt : SHOW SHARD GROUPS;
show_shards_stmt : SHOW SHARDS;
show_subscriptions_stmt : SHOW SUBSCRIPTIONS;
show_tag_keys_stmt : SHOW TAG KEYS ( from_clause )? ( where_clause )? ( group_by_clause )?
                      ( limit_clause )? ( offset_clause )?;
show_tag_values_stmt : SHOW TAG VALUES ( from_clause )? with_tag_clause ( where_clause )?
                        ( group_by_clause )? ( limit_clause )? ( offset_clause )?;
show_users_stmt : SHOW USERS;
revoke_stmt : REVOKE privilege ( on_clause )? FROM user_name;
select_stmt : SELECT fields from_clause ( into_clause )? ( where_clause )?
               ( group_by_clause )? ( order_by_clause )? ( limit_clause )?
               ( offset_clause )? ( slimit_clause )? ( soffset_clause )?
               ( timezone_clause )?;
alias            : AS IDENTIFIER;
back_ref         : ( policy_name '.:MEASUREMENT' ) |
                    ( db_name '.' ( policy_name )? '.:MEASUREMENT' );
db_name          : IDENTIFIER;
dimension        : expression;
dimensions       : dimension ( COMMA dimension )*;
field_key        : IDENTIFIER;
field            : expression ( alias )?;
fields           : field ( COMMA field )*;
fill_option      : 'null' | 'none' | 'previous' | 'linear' | INTERGER_LITERAL | FLOAT_LITERAL;
host             : STRING_LITERAL;
measurement      : measurement_name |
                    ( policy_name DOT measurement_name ) |
                    ( db_name DOT ( policy_name )? DOT measurement_name );
measurements     : measurement ( COMMA measurement )*;
measurement_name : IDENTIFIER | REGULAR_EXPRESSION_LITERAL;
password         : STRING_LITERAL;
policy_name      : IDENTIFIER;
privilege        : ALL ( PRIVILEGES )? | READ | WRITE;
query_id         : INTERGER_LITERAL;
retention_policy : IDENTIFIER;
retention_policy_option      : retention_policy_duration |
                                retention_policy_replication |
                                retention_policy_shard_group_duration |
                               DEFAULT;
retention_policy_duration    : DURATION DURATION_LITERAL;
retention_policy_replication : REPLICATION INTERGER_LITERAL;
retention_policy_shard_group_duration : SHARD DURATION DURATION_LITERAL;
retention_policy_name : NAME IDENTIFIER;
series_id        : INTERGER_LITERAL;
shard_id         : INTERGER_LITERAL;
sort_field       : field_key ( ASC | DESC )?;
sort_fields      : sort_field ( COMMA sort_field )*;
subscription_name : IDENTIFIER;
tag_key          : IDENTIFIER;
tag_keys         : tag_key ( ',' tag_key )*;
user_name        : IDENTIFIER;
var_ref          : measurement;
binary_op        : PLUS | MINUS | STAR | DIV | MOD | AMP | PIPE | OR_EX | AND |
                    OR | ASSIGN | NOT_EQ1 | NOT_EQ2 | LT | LT_EQ | GT | GT_EQ;
expression             : unary_expr ( binary_op unary_expr )*;
unary_expr       : OPEN_PAR expression CLOSE_PAR | var_ref | TIME_LITERAL | STRING_LITERAL | INTERGER_LITERAL |
                    FLOAT_LITERAL | BOOL_LITERAL | DURATION_LITERAL | REGULAR_EXPRESSION_LITERAL;
from_clause     : FROM measurements;
group_by_clause : GROUP BY dimensions 'fill(' fill_option ')';
into_clause     : INTO ( measurement | back_ref );
limit_clause    : LIMIT INTERGER_LITERAL;
offset_clause   : OFFSET INTERGER_LITERAL;
slimit_clause   : SLIMIT INTERGER_LITERAL;
soffset_clause  : SOFFSET INTERGER_LITERAL;
timezone_clause : 'tz(' STRING_LITERAL ')';
on_clause       : ON db_name;
order_by_clause : ORDER BY sort_fields;
to_clause       : TO user_name;
where_clause    : WHERE expression;
with_measurement_clause : WITH MEASUREMENT ( EQ measurement | REG_MATCH REGULAR_EXPRESSION_LITERAL );
with_tag_clause : WITH KEY ( ASSIGN tag_key | NOT_EQ1 tag_key | REG_MATCH REGULAR_EXPRESSION_LITERAL | IN OPEN_PAR tag_keys CLOSE_PAR );