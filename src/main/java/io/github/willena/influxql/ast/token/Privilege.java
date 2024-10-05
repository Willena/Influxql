package io.github.willena.influxql.ast.token;

/**
 * Known privileges
 */
public enum Privilege {
    NO_PRIVILEGE("NO PRIVILEGES"),
    READ_PRIVILEGE("READ"),
    WRITE_PRIVILEGE("WRITE"),
    ALL_PRIVILEGES("ALL PRIVILEGES");

    private final String sql;

    /**
     * New privilege with influxQL String
     *
     * @param sql the InfluxQl representation
     */
    Privilege(String sql) {
        this.sql = sql;
    }

    /**
     * Return the representation
     *
     * @return representation String
     */
    @Override
    public String toString() {
        return sql;
    }
}
