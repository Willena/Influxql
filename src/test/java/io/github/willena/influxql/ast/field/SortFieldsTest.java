package io.github.willena.influxql.ast.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortFieldsTest {

    @Test
    void test() {
        assertEquals(0, new SortFields().size());
        assertEquals(0, SortFields.of().size());
        assertEquals("name ASC, another DESC", SortFields.of(SortField.asc("name"), SortField.desc("another")).toString());
    }
}