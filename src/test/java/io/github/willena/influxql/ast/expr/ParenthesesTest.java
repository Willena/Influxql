package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParenthesesTest {

    @Test
    void test() {
        assertEquals("(var = 1)", Parentheses.of(BinaryExpression.eq(VarRef.of("var"), IntegerLiteral.of(1))).toString());
    }
}