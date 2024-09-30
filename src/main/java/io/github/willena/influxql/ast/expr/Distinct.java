package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

public class Distinct implements Expression {
    private final String value;

    public Distinct(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DISTINCT " + value;
    }
}
