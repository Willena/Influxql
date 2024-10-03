package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowQueriesStatement.showQueries;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowQueriesStatementTest {
    @Test
    void test() {
        assertEquals("SHOW QUERIES", new ShowQueriesStatement().toString());
        assertEquals("SHOW QUERIES", showQueries().toString());
    }
}