package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowSubscriptionsStatement implements Statement {
    public ShowSubscriptionsStatement() {
    }

    public static ShowSubscriptionsStatement showSubscriptions() {
        return new ShowSubscriptionsStatement();
    }

    @Override
    public String toString() {
        return "SHOW SUBSCRIPTIONS";
    }
}
