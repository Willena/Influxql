package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowContinuousQueriesStatement implements Statement {
    public ShowContinuousQueriesStatement() {
    }

    public static ShowContinuousQueriesStatement showContinuousQueries() {
        return new ShowContinuousQueriesStatement();
    }

    @Override
    public String toString() {
        return "SHOW CONTINUOUS QUERIES";
    }
}
