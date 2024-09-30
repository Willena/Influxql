package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringLiteralTest {
    @Test
    void testStringLiteral() {
        assertEquals("abc", StringLiteral.of("abc").getValue());
        assertEquals("'abc'", StringLiteral.of("abc").toString());
        assertThrows(IllegalArgumentException.class, () -> StringLiteral.of(null));
    }
}