package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Dimension {
    private final Expression expression;

    public Dimension(final Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    public static Dimension of(final Expression expression) {
        return new Dimension(expression);
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
