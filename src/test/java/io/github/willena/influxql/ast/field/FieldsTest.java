package io.github.willena.influxql.ast.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldsTest {

    @Test
    void test() {
        assertEquals(0, new Fields().size());
        assertEquals(0, Fields.of().size());
        assertEquals(1, Fields.of(Field.field("name")).size());
    }


}