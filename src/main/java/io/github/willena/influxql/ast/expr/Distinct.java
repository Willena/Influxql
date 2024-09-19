package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

public class Distinct implements Expr {
    private final String value;

    public Distinct(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DISTINCT " + value;
    }
}
