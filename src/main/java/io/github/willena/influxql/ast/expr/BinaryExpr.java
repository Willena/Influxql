package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.token.Operator;

public class BinaryExpr implements Expr {
    private final Expr left;
    private final Expr right;
    private final Operator op;

    public BinaryExpr(Expr left, Expr right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toString() {
        return left.toString() + " " + op.toString() + " " + right.toString();
    }
}

