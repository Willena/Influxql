package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowSubscriptionsStatement.showSubscriptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowSubscriptionsStatementTest {

    @Test
    void test() {
        assertEquals("SHOW SUBSCRIPTIONS", new ShowSubscriptionsStatement().toString());
        assertEquals("SHOW SUBSCRIPTIONS", showSubscriptions().toString());
    }
}