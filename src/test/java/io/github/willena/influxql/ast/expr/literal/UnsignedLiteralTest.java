package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnsignedLiteralTest {
    @Test
    void unsignedLiteral() {
        assertEquals(12, UnsignedLiteral.of(12).getValue());
        assertEquals("12", UnsignedLiteral.of(12).toString());
    }
}