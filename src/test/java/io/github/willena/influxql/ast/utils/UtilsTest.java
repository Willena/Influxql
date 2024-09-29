package io.github.willena.influxql.ast.utils;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {


    @Test
    void escapeString() {
        assertEquals("\\\\\"", Utils.escapeString("\\\""));
        assertEquals("\\\\n", Utils.escapeString("\n"));
        assertEquals("\\'", Utils.escapeString("'"));
    }

    @Test
    void quoteString() {
        assertEquals("'abc'", Utils.quoteString("abc"));
        assertEquals("'ab\"c'", Utils.quoteString("ab\"c"));
        assertEquals("'ab\\'c'", Utils.quoteString("ab'c"));
    }

    @Test
    void formatDuration() {
        assertEquals("1h", Utils.formatDuration(Duration.ofHours(1)));
    }

    @Test
    void quoteIdentifier() {
        assertEquals("abc", Utils.quoteIdentifier("abc"));
        assertEquals("\"abc\".def", Utils.quoteIdentifier("abc", "def"));
        assertEquals("\"abc\".\"de\\\\nf\"", Utils.quoteIdentifier("abc", "de\nf"));
    }

    @Test
    void ensureDefined() {
        assertDoesNotThrow(() -> Utils.ensureDefined("value", "value"));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", ""));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", null));

        assertDoesNotThrow(() -> Utils.ensureDefined("value", new Object()));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", null));
    }
}