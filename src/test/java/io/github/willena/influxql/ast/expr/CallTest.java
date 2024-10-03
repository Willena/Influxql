package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallTest {
    @Test
    void testCall() {
        assertEquals("mean(uuu, 'ddd')", new Call.Builder().function("mean").withArguments(VarRef.of("uuu"), StringLiteral.of("ddd")).build().toString());
    }
}