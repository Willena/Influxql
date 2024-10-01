package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WildcardTest {

    @Test
    void wildcard() {
        assertEquals("*", Wildcard.wildcard().toString());
    }

    @Test
    void wildcardFields() {
        assertEquals("*::field", Wildcard.wildcardFields().toString());
    }

    @Test
    void wildcardTags() {
        assertEquals("*::tag", Wildcard.wildcardTags().toString());
    }
}