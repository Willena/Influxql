package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowQueriesStatement implements Statement {
    public ShowQueriesStatement() {
    }

    @Override
    public String toString() {
        return "SHOW QUERIES";
    }
}
