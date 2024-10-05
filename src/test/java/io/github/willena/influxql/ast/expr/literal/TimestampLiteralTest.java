package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimestampLiteralTest {
    @Test
    void testTimeLiteral() {
        assertEquals(Instant.ofEpochMilli(123456789), TimestampLiteral.of(Instant.ofEpochMilli(123456789)).getValue());
        assertEquals("'1970-01-02T10:17:36.789Z'", TimestampLiteral.of(Instant.ofEpochMilli(123456789)).toString());
        assertThrows(IllegalArgumentException.class, () -> TimestampLiteral.of(null));
    }
}