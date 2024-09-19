package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

public class ParenExpr implements Expr {
    private final Expr expr;

    public ParenExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "(" + expr.toString() + ")";
    }
}
