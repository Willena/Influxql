package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DimensionTest {
    @Test
    void testDimension() {
        assertEquals("uuu", Dimension.of(VarRef.of("uuu")).toString());
    }

}