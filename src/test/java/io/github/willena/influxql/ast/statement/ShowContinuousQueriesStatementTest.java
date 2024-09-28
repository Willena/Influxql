package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowContinuousQueriesStatementTest {

    @Test
    void asSql() {
        assertEquals("SHOW CONTINUOUS QUERIES", new ShowContinuousQueriesStatement().toString());
    }
}