package io.github.willena.influxql.ast.token;

public enum Privilege {
    NO_PRIVILEGE("NO PRIVILEGES"),
    READ_PRIVILEGE("READ"),
    WRITE_PRIVILEGE("WRITE"),
    ALL_PRIVILEGES("ALL PRIVILEGES");

    private final String sql;

    Privilege(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}
