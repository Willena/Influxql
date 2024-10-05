package io.github.willena.influxql.ast.token;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Well known InfluxQL keywords
 */
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

    private static final Set<String> KEYWORDS_VALUES = Arrays.stream(Keywords.values()).map(Keywords::toString).collect(Collectors.toSet());
    private final String value;

    /**
     * Build a new {@link Keywords} with its InfluxQL representation
     *
     * @param value influxql representation
     */
    Keywords(String value) {
        this.value = value;
    }

    /**
     * Check if given string is an existing keyword
     *
     * @param value the string to check
     * @return true if a keyword is found
     */
    public static boolean hasValue(String value) {
        return KEYWORDS_VALUES.contains(value.toUpperCase());
    }

    /**
     * Find a keyword matching the given string
     *
     * @param keyword keyword to find as upper or lower
     * @return the found keyword if any
     */
    public static Keywords find(String keyword) {
        return Keywords.valueOf(keyword.toUpperCase());
    }

    /**
     * InfluxQL representation of a Keyword
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        return value;
    }

}
