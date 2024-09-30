package io.github.willena.influxql.ast.expr.literal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListLiteralTest {
    @Test
    void listTest() {
        assertEquals("(A, B)", ListLiteral.of("A", "B").toString());
        assertEquals(List.of("A", "B"), ListLiteral.of("A", "B").getValue());
        assertEquals(List.of("A", "B"), ListLiteral.of(List.of("A", "B")).getValue());
    }
}