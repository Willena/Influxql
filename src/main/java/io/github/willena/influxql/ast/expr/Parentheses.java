package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Parentheses implements Expression {
    private final Expression expression;

    public Parentheses(Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    public static Parentheses of(Expression expression) {
        return new Parentheses(expression);
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }
}
