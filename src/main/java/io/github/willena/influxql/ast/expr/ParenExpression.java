package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

public class ParenExpression implements Expression {
    private final Expression expression;

    public ParenExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }
}
