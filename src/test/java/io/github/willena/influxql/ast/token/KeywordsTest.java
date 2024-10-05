package io.github.willena.influxql.ast.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeywordsTest {


    @Test
    void hasValue() {
        assertTrue(Keywords.hasValue("WHERE"));
        assertTrue(Keywords.hasValue("where"));
        assertFalse(Keywords.hasValue("wqdqsdqsdshere"));
    }

    @Test
    void find() {
        assertEquals(Keywords.WHERE, Keywords.find("WHERE"));
        assertEquals(Keywords.WHERE, Keywords.find("where"));
        assertThrows(IllegalArgumentException.class, () -> Keywords.find("wqdqsdqsdshere"));
    }

    @Test
    void toString() {
        assertEquals("WHERE", Keywords.WHERE.toString());
    }
}