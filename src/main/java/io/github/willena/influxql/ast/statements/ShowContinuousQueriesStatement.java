package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class ShowContinuousQueriesStatement implements Statement {
    public ShowContinuousQueriesStatement() {
    }

    @Override
    public String toString() {
        return "SHOW CONTINUOUS QUERIES";
    }
}
