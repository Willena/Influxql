package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DimensionsTest {
    @Test
    void testDimensionsEmpty() {
        assertEquals(0, new Dimensions().size());
    }

    @Test
    void testDimensions() {
        Dimensions dimensions = Dimensions.of(Dimension.of(VarRef.of("jjj")));
        assertEquals(1, dimensions.size());
        assertEquals("jjj", dimensions.toString());
    }

}