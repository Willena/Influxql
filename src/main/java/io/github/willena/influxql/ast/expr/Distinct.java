package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Distinct implements Expression {
    private final String value;

    public Distinct(String value) {
        this.value = value;
        ensureDefined("value", value);
    }

    public static Distinct of(String value) {
        return new Distinct(value);
    }

    @Override
    public String toString() {
        return "DISTINCT " + value;
    }
}
