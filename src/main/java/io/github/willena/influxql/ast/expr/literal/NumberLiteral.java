package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

public class NumberLiteral implements Literal<Double> {
    private final Double value;

    public NumberLiteral(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value.isNaN()) {
            return "NaN";
        }

        if (value.isInfinite()) {
            return value > 0 ? "+Inf" : "-Inf";
        }

        return value.toString();
    }
}
