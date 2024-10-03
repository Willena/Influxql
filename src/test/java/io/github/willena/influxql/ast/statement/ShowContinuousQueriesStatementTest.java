package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowContinuousQueriesStatement.showContinuousQueries;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowContinuousQueriesStatementTest {

    @Test
    void asSql() {
        assertEquals("SHOW CONTINUOUS QUERIES", new ShowContinuousQueriesStatement().toString());
        assertEquals("SHOW CONTINUOUS QUERIES", showContinuousQueries().toString());
    }
}