package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberLiteralTest {
    @Test
    void numberLiteral() {
        assertEquals(11.5, NumberLiteral.of(11.5).getValue());
        assertEquals(11.0, NumberLiteral.of(11).getValue());
        assertEquals("11.0", NumberLiteral.of(11).toString());
        assertEquals("11.5", NumberLiteral.of(11.5).toString());
        assertEquals("NaN", NumberLiteral.of(Double.NaN).toString());
        assertEquals("-Inf", NumberLiteral.of(Double.NEGATIVE_INFINITY).toString());
        assertEquals("+Inf", NumberLiteral.of(Double.POSITIVE_INFINITY).toString());
        assertThrows(IllegalArgumentException.class, () -> NumberLiteral.of(null));
    }
}