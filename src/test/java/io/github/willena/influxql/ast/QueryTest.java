package io.github.willena.influxql.ast;

import io.github.willena.influxql.ast.statement.ShowDatabasesStatement;
import io.github.willena.influxql.ast.statement.ShowMeasurementsStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryTest {

    @Test
    void test() {
        Query q = Query.ofStatements(
                new ShowDatabasesStatement(),
                new ShowMeasurementsStatement.Builder().build()
        );
        assertEquals("SHOW DATABASES; SHOW MEASUREMENTS", q.toString());
    }
}