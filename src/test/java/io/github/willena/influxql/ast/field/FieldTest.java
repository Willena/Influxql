package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.expr.DataType;
import io.github.willena.influxql.ast.expr.VarRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {
    @Test
    void testField() {
        assertEquals("fieldName", Field.field("fieldName").toString());
        assertEquals("fieldName::integer", Field.field("fieldName", DataType.INTEGER).toString());
    }

    @Test
    void fieldWithAlias() {
        assertEquals("fieldAlias AS toto", new Field.Builder().withAlias("toto").withExpr(VarRef.of("fieldAlias")).build().toString());
    }

    @Test
    void wildcard() {
        assertEquals("*", Field.wildcard().toString());
        assertEquals("*::field", Field.wildcardFields().toString());
        assertEquals("*::tag", Field.wildcardTags().toString());
    }


}