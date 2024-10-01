package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoundParameterTest {

    @Test
    void testBoundParameter() {
        assertEquals("$myName", BoundParameter.named("myName").toString());
    }
}