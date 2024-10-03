package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class NumberLiteral implements Literal<Double>, NumericLiteral {
    private final Double value;

    public NumberLiteral(Double value) {
        this.value = value;
        ensureDefined("value", value);
    }

    public static NumberLiteral of(Number value) {
        ensureDefined("value", value);
        return new NumberLiteral(value.doubleValue());
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
            return value == Double.POSITIVE_INFINITY ? "+Inf" : "-Inf";
        }

        return value.toString();
    }
}
