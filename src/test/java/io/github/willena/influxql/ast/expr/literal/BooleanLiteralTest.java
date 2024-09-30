package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanLiteralTest {
    @Test
    void testBooleanLiteral() {
        assertEquals(true, BooleanLiteral.of(true).getValue());
        assertEquals("true", BooleanLiteral.of(true).toString());
        assertEquals("false", BooleanLiteral.of(false).toString());
    }
}