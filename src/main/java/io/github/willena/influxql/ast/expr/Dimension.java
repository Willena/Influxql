package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

public class Dimension {
    private final Expr expr;

    public Dimension(final Expr expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return expr.toString();
    }
}
