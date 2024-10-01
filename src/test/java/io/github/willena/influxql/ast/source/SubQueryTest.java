package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.statement.SelectStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubQueryTest {
    @Test
    void test() {
        assertEquals("(SELECT * FROM Meas)", SubQuery.of(new SelectStatement.Builder()
                .select(Field.wildcard())
                .from(Measurement.measurement("Meas"))
                .build()).toString());
    }
}