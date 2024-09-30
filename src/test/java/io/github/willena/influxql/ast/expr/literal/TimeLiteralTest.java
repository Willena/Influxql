package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeLiteralTest {
    @Test
    void testTimeLiteral() {
        assertEquals(Instant.ofEpochMilli(123456789), TimeLiteral.of(Instant.ofEpochMilli(123456789)).getValue());
        assertEquals("'1970-01-02T10:17:36.789Z'", TimeLiteral.of(Instant.ofEpochMilli(123456789)).toString());
        assertThrows(IllegalArgumentException.class, () -> TimeLiteral.of(null));
    }
}