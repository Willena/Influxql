package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowDatabasesStatement.showDatabases;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowDatabasesStatementTest {
    @Test
    void asSql() {
        assertEquals("SHOW DATABASES", new ShowDatabasesStatement().toString());
        assertEquals("SHOW DATABASES", showDatabases().toString());
    }
}