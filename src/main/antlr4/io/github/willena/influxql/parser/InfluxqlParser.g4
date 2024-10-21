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

parser grammar InfluxqlParser;

options {
    tokenVocab = InfluxqlLexer;
}

// ------------- PARSER ---------------------
query: statement ( SCOL statement )* EOF;

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
 show_retention_policies_stmt |
 show_series_stmt |
 show_shard_groups_stmt |
 show_shards_stmt |
 show_subscriptions_stmt|
 show_tag_keys_stmt |
 show_tag_key_cardinality_stmt
 show_tag_values_stmt |
 show_tag_values_cardinality_stmt
 show_users_stmt |
 revoke_stmt |
 select_stmt ;

alter_retention_policy_stmt: ALTER RETENTION POLICY policy_name=IDENTIFIER on_clause retention_policy_option retention_policy_option? retention_policy_option? retention_policy_option?;
create_continuous_query_stmt: CREATE CONTINUOUS QUERY query_name=IDENTIFIER on_clause ( RESAMPLE resample_opts )? BEGIN select_stmt END;
create_database_stmt: CREATE DATABASE db_name=IDENTIFIER ( WITH retention_policy_duration? retention_policy_replication? retention_policy_shard_group_duration? retention_policy_name? )?;
create_retention_policy_stmt : CREATE RETENTION POLICY policy_name=IDENTIFIER on_clause retention_policy_duration retention_policy_replication retention_policy_shard_group_duration? DEFAULT?;
create_subscription_stmt : CREATE SUBSCRIPTION subscription_name=IDENTIFIER ON db_name=IDENTIFIER DOT retention_policy=IDENTIFIER DESTINATIONS (ANY|ALL) host=STRING_LITERAL (COMMA host=STRING_LITERAL)*;
create_user_stmt : CREATE USER user_name=IDENTIFIER WITH PASSWORD password=STRING_LITERAL (WITH ALL PRIVILEGES)?;
delete_stmt : DELETE ( from_clause | where_clause | from_clause where_clause );
drop_continuous_query_stmt : DROP CONTINUOUS QUERY query_name=IDENTIFIER on_clause;
drop_database_stmt : DROP DATABASE db_name=IDENTIFIER;
// Drop measurement does not currently support the full range of rules (limitation of influxdb) and has been updated
// The described grammar on influx website should be:
// drop_measurement_stmt : DROP MEASUREMENT measurement;
drop_measurement_stmt : DROP MEASUREMENT measurement_value=IDENTIFIER;
drop_retention_policy_stmt : DROP RETENTION POLICY policy_name=IDENTIFIER on_clause;
drop_series_stmt : DROP SERIES ( from_clause | where_clause | from_clause where_clause );
drop_shard_stmt : DROP SHARD ( shard_id=INTEGER_LITERAL );
drop_subscription_stmt : DROP SUBSCRIPTION subscription_name=IDENTIFIER ON db_name=IDENTIFIER DOT retention_policy=IDENTIFIER;
drop_user_stmt : DROP USER user_name=IDENTIFIER;
explain_stmt : EXPLAIN ANALYZE? select_stmt;
grant_stmt : GRANT privilege on_clause? to_clause;
// On host is only available for cluster installation of influxdb
kill_query_statement : KILL QUERY query_id=INTEGER_LITERAL (ON host=STRING_LITERAL)?;
show_continuous_queries_stmt : SHOW CONTINUOUS QUERIES;
show_databases_stmt : SHOW DATABASES;
show_field_keys_stmt : SHOW FIELD KEYS on_clause? from_clause? order_by_clause? limit_clause? offset_clause?;
show_grants_stmt : SHOW GRANTS FOR user_name=IDENTIFIER;
show_measurements_stmt : SHOW MEASUREMENTS on_clause? with_measurement_clause? where_clause? limit_clause? offset_clause?;
show_queries_stmt : SHOW QUERIES;
show_retention_policies_stmt : SHOW RETENTION POLICIES on_clause;
show_series_stmt : SHOW SERIES on_clause? from_clause? where_clause? limit_clause? offset_clause?;
show_shard_groups_stmt : SHOW SHARD GROUPS;
show_shards_stmt : SHOW SHARDS;
show_subscriptions_stmt : SHOW SUBSCRIPTIONS;
show_tag_keys_stmt : SHOW TAG KEYS on_clause? from_clause? where_clause? order_by_clause? limit_clause? offset_clause?;
show_tag_key_cardinality_stmt: SHOW TAG KEY EXACT? CARDINALITY on_clause? from_clause? where_clause? group_by_clause? limit_clause? offset_clause?;
show_tag_values_stmt : SHOW TAG VALUES on_clause? from_clause? with_tag_clause where_clause? limit_clause? offset_clause?;
show_tag_values_cardinality_stmt: SHOW TAG VALUES EXACT? CARDINALITY on_clause? from_clause? where_clause? group_by_clause? limit_clause? offset_clause? with_tag_clause;
show_users_stmt : SHOW USERS;
revoke_stmt : REVOKE privilege on_clause? FROM user_name=IDENTIFIER ;
select_stmt : SELECT fields from_clause into_clause? where_clause? group_by_clause? order_by_clause? limit_clause? offset_clause? slimit_clause? soffset_clause? timezone_clause?;

//query_name: identifier;
resample_opts: (every_stmt for_stmt | every_stmt | for_stmt);
every_stmt: EVERY DURATION_LITERAL;
for_stmt: FOR DURATION_LITERAL;

alias: AS IDENTIFIER;
back_ref: ( policy_name=IDENTIFIER MEASUREMENT_BACK_REF ) | ( db_name=IDENTIFIER DOT ( policy_name=IDENTIFIER )? MEASUREMENT_BACK_REF );
dimension : expression;
dimensions : dimension ( COMMA dimension )*;
field: expression alias?;
fields: field ( COMMA field )*;
fill_option: NULL | NONE | PREVIOUS | LINEAR | NUMERIC_LITERAL;
measurement: simple_measurement_name |  measurment_with_rp | measurement_with_rp_and_database;
simple_measurement_name : IDENTIFIER | REGULAR_EXPRESSION_LITERAL;
measurment_with_rp: policy_name=IDENTIFIER DOT simple_measurement_name;
measurement_with_rp_and_database: db_name=IDENTIFIER (DOT policy_name=IDENTIFIER)? DOT simple_measurement_name;
source: measurement | sub_query;
sources: source ( COMMA source )*;
privilege: (ALL PRIVILEGES?) | READ | WRITE;
sub_query: OPEN_PAR select_stmt CLOSE_PAR;

retention_policy_option : retention_policy_duration | retention_policy_replication | retention_policy_shard_group_duration | DEFAULT;
retention_policy_duration : DURATION DURATION_LITERAL;
retention_policy_replication : REPLICATION INTEGER_LITERAL;
retention_policy_shard_group_duration : SHARD DURATION DURATION_LITERAL;
retention_policy_name : NAME IDENTIFIER;

sort_field : field_key=IDENTIFIER ( ASC | DESC )?;
sort_fields : sort_field ( COMMA sort_field )*;
tag_keys : tag_key=IDENTIFIER ( COMMA tag_key=IDENTIFIER )*;
var_ref : measurement;
//binary_op : PLUS | MINUS | STAR | SLASH | MOD | AMP | PIPE | OR_EX| AND | OR | ASSIGN | NOT_EQ1 | NOT_EQ2 | LT | LT_EQ | GT | GT_EQ;

//expression : unary_expr ( binary_op unary_expr )*;
//unary_expr : group_expr | call | var_ref | literal_expr;
group_expr: OPEN_PAR expression CLOSE_PAR;
//literal_expr: STRING_LITERAL | signed_number | TRUE | FALSE | DURATION_LITERAL;
//signed_number: (PLUS|MINUS)? NUMERIC_LITERAL;
call: IDENTIFIER OPEN_PAR expression? (COMMA expression)* CLOSE_PAR;

unary_operator
    : MINUS
    | PLUS
    | TILDE;

expression
    : literal_value
    | unary_operator expression
    | expression PIPE2 expression
    | expression ( STAR | SLASH | MOD) expression
    | expression ( PLUS | MINUS) expression
    | expression ( LT2 | GT2 | AMP | PIPE | OR_EX) expression
    | expression ( LT | LT_EQ | GT | GT_EQ) expression
    | expression (
        ASSIGN
        | EQ
        | NOT_EQ1
        | NOT_EQ2
    ) expression
    | expression AND expression
    | expression OR expression
    | call
    | group_expr
    | var_ref
    | STAR | START_FIELD | START_TAGS
;

literal_value
    : INTEGER_LITERAL
    | NUMERIC_LITERAL
    | STRING_LITERAL
    | DURATION_LITERAL
    | NULL
    | TRUE
    | FALSE
;

from_clause : FROM sources;
group_by_clause : GROUP BY dimensions fill_clause?;
into_clause : INTO ( measurement | back_ref );
limit_clause : LIMIT INTEGER_LITERAL;
offset_clause : OFFSET INTEGER_LITERAL;
slimit_clause : SLIMIT INTEGER_LITERAL;
soffset_clause : SOFFSET INTEGER_LITERAL;
timezone_clause : TZ OPEN_PAR STRING_LITERAL CLOSE_PAR;
on_clause : ON db_name=IDENTIFIER;
fill_clause: FILL OPEN_PAR fill_option CLOSE_PAR;
order_by_clause: ORDER BY sort_fields;
to_clause : TO user_name=IDENTIFIER ;
where_clause : WHERE expression;
with_measurement_clause : WITH MEASUREMENT ( EQ measurement | REG_MATCH REGULAR_EXPRESSION_LITERAL );
with_tag_clause : WITH KEY ( op=ASSIGN tag_key=IDENTIFIER | op=NOT_EQ1 tag_key=IDENTIFIER | op=REG_MATCH tag_key=REGULAR_EXPRESSION_LITERAL | op=IN OPEN_PAR tag_keys CLOSE_PAR );

