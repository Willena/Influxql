package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallTest {
    @Test
    void testCall() {
        assertEquals("mean(uuu, 'ddd')", new Call.Builder()
                .function("mean")
                .withArguments(VarRef.of("uuu"), StringLiteral.of("ddd"))
                .build().toString());

        assertEquals("mean(uuu, 'ddd')", new Call.Builder()
                .function("mean")
                .withArguments(new StringJoiningList<>(List.of(VarRef.of("uuu"), StringLiteral.of("ddd"))))
                .build().toString());
    }
}