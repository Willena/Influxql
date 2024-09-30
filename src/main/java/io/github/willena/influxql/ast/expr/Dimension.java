package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

public class Dimension {
    private final Expression expression;

    public Dimension(final Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
