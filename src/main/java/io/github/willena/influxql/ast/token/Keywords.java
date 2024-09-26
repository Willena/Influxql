package io.github.willena.influxql.ast.token;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Keywords {
    ALL("ALL"),
    ALTER("ALTER"),
    ANALYZE("ANALYZE"),
    ANY("ANY"),
    AS("AS"),
    ASC("ASC"),
    BEGIN("BEGIN"),
    BY("BY"),
    CARDINALITY("CARDINALITY"),
    CREATE("CREATE"),
    CONTINUOUS("CONTINUOUS"),
    DATABASE("DATABASE"),
    DATABASES("DATABASES"),
    DEFAULT("DEFAULT"),
    DELETE("DELETE"),
    DESC("DESC"),
    DESTINATIONS("DESTINATIONS"),
    DIAGNOSTICS("DIAGNOSTICS"),
    DISTINCT("DISTINCT"),
    DROP("DROP"),
    DURATION("DURATION"),
    END("END"),
    EVERY("EVERY"),
    EXACT("EXACT"),
    EXPLAIN("EXPLAIN"),
    FIELD("FIELD"),
    FOR("FOR"),
    FROM("FROM"),
    GRANT("GRANT"),
    GRANTS("GRANTS"),
    GROUP("GROUP"),
    GROUPS("GROUPS"),
    IN("IN"),
    INF("INF"),
    INSERT("INSERT"),
    INTO("INTO"),
    KEY("KEY"),
    KEYS("KEYS"),
    KILL("KILL"),
    LIMIT("LIMIT"),
    MEASUREMENT("MEASUREMENT"),
    MEASUREMENTS("MEASUREMENTS"),
    NAME("NAME"),
    OFFSET("OFFSET"),
    ON("ON"),
    ORDER("ORDER"),
    PASSWORD("PASSWORD"),
    POLICY("POLICY"),
    POLICIES("POLICIES"),
    PRIVILEGES("PRIVILEGES"),
    QUERIES("QUERIES"),
    QUERY("QUERY"),
    READ("READ"),
    REPLICATION("REPLICATION"),
    RESAMPLE("RESAMPLE"),
    RETENTION("RETENTION"),
    REVOKE("REVOKE"),
    SELECT("SELECT"),
    SERIES("SERIES"),
    SET("SET"),
    SHOW("SHOW"),
    SHARD("SHARD"),
    SHARDS("SHARDS"),
    SLIMIT("SLIMIT"),
    SOFFSET("SOFFSET"),
    STATS("STATS"),
    SUBSCRIPTION("SUBSCRIPTION"),
    SUBSCRIPTIONS("SUBSCRIPTIONS"),
    TAG("TAG"),
    TO("TO"),
    USER("USER"),
    USERS("USERS"),
    VALUES("VALUES"),
    VERBOSE("VERBOSE"),
    WHERE("WHERE"),
    WITH("WITH"),
    WRITE("WRITE");

    private static final Set<String> KEYWORDS_VALUES = Arrays.stream(Keywords.values()).map(Keywords::getValue).collect(Collectors.toSet());
    private final String value;

    Keywords(String value) {
        this.value = value;
    }

    public static boolean hasValue(String value) {
        return KEYWORDS_VALUES.contains(value.toUpperCase());
    }

    public static Keywords find(String keyword) {
        return Keywords.valueOf(keyword.toUpperCase());
    }

    public String getValue() {
        return value;
    }

}
