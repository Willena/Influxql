package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistinctTest {
    @Test
    void testDistinct() {
        assertEquals("DISTINCT field", Distinct.of("field").toString());
    }
}