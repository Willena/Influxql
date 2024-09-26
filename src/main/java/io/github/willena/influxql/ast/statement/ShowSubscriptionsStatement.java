package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowSubscriptionsStatement implements Statement {
    public ShowSubscriptionsStatement() {
    }

    @Override
    public String toString() {
        return "SHOW SUBSCRIPTIONS";
    }
}
