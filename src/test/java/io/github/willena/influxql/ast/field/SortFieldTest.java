package io.github.willena.influxql.ast.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortFieldTest {
    @Test
    void testSortField() {
        assertEquals("\"field\" ASC", SortField.asc("field").toString());
        assertEquals("\"field\" DESC", SortField.desc("field").toString());
    }
}