package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DurationLiteralTest {
    @Test
    void durationLiteral() {
        assertThrows(IllegalArgumentException.class, () -> DurationLiteral.of(null));
        assertEquals(Duration.ofHours(1), DurationLiteral.of(Duration.ofHours(1)).getValue());
        assertEquals("1h", DurationLiteral.of(Duration.ofHours(1)).toString());
    }
}