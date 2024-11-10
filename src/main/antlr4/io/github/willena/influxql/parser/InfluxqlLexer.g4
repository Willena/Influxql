lexer grammar InfluxqlLexer;

options {
    caseInsensitive = true;
}

QUOTE: '"';
SCOL : ';';
DOT : '.';
OPEN_PAR : '(';
CLOSE_PAR : ')';
COMMA : ',';
ASSIGN : '=';
STAR : '*';
PLUS : '+';
MINUS : '-';
TILDE : '~';
SLASH : '/';
MOD : '%';
LT2 : '<<';
GT2 : '>>';
AMP : '&';
LT : '<';
LT_EQ : '<=';
GT : '>';
GT_EQ : '>=';
EQ : '==';
NOT_EQ1 : '!=';
NOT_EQ2 : '<>';
REG_MATCH: '=~';
REG_NMATCH: '!~';
OR_EX: '^';
PIPE: '|';
PIPE2: '||';
STAR_TAGS: STAR '::tag';
STAR_FIELD: STAR '::field';

// Keywords
AND: 'AND';
OR: 'OR';
ALL: 'ALL';
ALTER: 'ALTER';
ANALYZE: 'ANALYZE';
ANY: 'ANY';
AS: 'AS';
ASC: 'ASC';
BEGIN: 'BEGIN';
BY: 'BY';
CREATE: 'CREATE';
CONTINUOUS: 'CONTINUOUS';
DATABASE: 'DATABASE';
DATABASES: 'DATABASES';
DEFAULT: 'DEFAULT';
DELETE: 'DELETE';
DESC: 'DESC';
DESTINATIONS: 'DESTINATIONS';
DIAGNOSTICS: 'DIAGNOSTICS';
DISTINCT: 'DISTINCT';
DROP: 'DROP';
DURATION: 'DURATION';
END: 'END';
EVERY: 'EVERY';
EXPLAIN: 'EXPLAIN';
FIELD: 'FIELD';
FOR: 'FOR';
FROM: 'FROM';
GRANT: 'GRANT';
GRANTS: 'GRANTS';
GROUP: 'GROUP';
GROUPS: 'GROUPS';
IN: 'IN';
INF: 'INF';
INSERT: 'INSERT';
INTO: 'INTO';
KEY: 'KEY';
KEYS: 'KEYS';
KILL: 'KILL';
LIMIT: 'LIMIT';
SHOW: 'SHOW';
MEASUREMENT: 'MEASUREMENT';
MEASUREMENTS: 'MEASUREMENTS';
NAME: 'NAME';
OFFSET: 'OFFSET';
ON: 'ON';
ORDER: 'ORDER';
PASSWORD: 'PASSWORD';
POLICY: 'POLICY';
POLICIES: 'POLICIES';
PRIVILEGES: 'PRIVILEGES';
QUERIES: 'QUERIES';
QUERY: 'QUERY';
READ: 'READ';
REPLICATION: 'REPLICATION';
RESAMPLE: 'RESAMPLE';
RETENTION: 'RETENTION';
REVOKE: 'REVOKE';
SELECT: 'SELECT';
SERIES: 'SERIES';
SET: 'SET';
SHARD: 'SHARD';
SHARDS: 'SHARDS';
SLIMIT: 'SLIMIT';
SOFFSET: 'SOFFSET';
STATS: 'STATS';
SUBSCRIPTION: 'SUBSCRIPTION';
SUBSCRIPTIONS: 'SUBSCRIPTIONS';
TAG: 'TAG';
TO: 'TO';
USER: 'USER';
USERS: 'USERS';
VALUES: 'VALUES';
WHERE: 'WHERE';
WITH: 'WITH';
WRITE: 'WRITE';
CARDINALITY: 'CARDINALITY';
EXACT: 'EXACT';

FILL: 'FILL';
NULL: 'NULL';
NONE: 'NONE';
PREVIOUS: 'PREVIOUS';
LINEAR: 'LINEAR';
TZ: 'TZ';
TRUE: 'TRUE';
FALSE: 'FALSE';
MEASUREMENT_BACK_REF: '.:MEASUREMENT';

IDENTIFIER:
    '"' (~'"' | '""')* '"'
    | '`' (~'`' | '``')* '`'
    | [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]*
    ;

INTEGER_LITERAL: DIGIT+;
NUMERIC_LITERAL: (DIGIT+ ('.' DIGIT*)?) | ('.' DIGIT+);
STRING_LITERAL: '\'' ( ~'\'' | '\\\'')* '\'';

DURATION_LITERAL: INTEGER_LITERAL ('ns' | 'u' | 'µ' | 'ms' | 's' | 'm' | 'h' | 'd' | 'w');

REGULAR_EXPRESSION_LITERAL: '/' UNICODE_CHAR* '/';
UNICODE_CHAR: ~[ \t\r\n];

fragment DIGIT: [0-9];

SINGLE_LINE_COMMENT: '--' ~[\r\n]* (('\r'? '\n') | EOF) -> channel(HIDDEN);
MULTILINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);
SPACES: [ \u000B\t\r\n] -> channel(HIDDEN);
UNEXPECTED_CHAR: .;
