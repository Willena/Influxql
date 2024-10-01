package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegexLiteralTest {
    @Test
    void testRegexLiteral() {
        assertEquals(Pattern.compile(".*").toString(), RegexLiteral.of(".*").getValue().toString());
        assertEquals("/.*/", RegexLiteral.of(".*").toString());
        assertEquals("/.*/", RegexLiteral.of(Pattern.compile(".*")).toString());
        assertThrows(IllegalArgumentException.class, () -> new RegexLiteral(null));
    }
}