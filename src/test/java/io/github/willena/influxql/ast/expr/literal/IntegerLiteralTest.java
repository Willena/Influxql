package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerLiteralTest {
    @Test
    void testIntegerLiteral() {
        assertThrows(IllegalArgumentException.class, () -> IntegerLiteral.of(null));
        assertEquals(12, IntegerLiteral.of(12).getValue());
        assertEquals("12", IntegerLiteral.of(12).toString());
    }
}