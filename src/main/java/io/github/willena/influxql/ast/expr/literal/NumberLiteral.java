package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Floating number literal
 */
public class NumberLiteral implements Literal<Double>, NumericLiteral {
    private final Double value;

    /**
     * Create a new number literal
     *
     * @param value double value
     */
    public NumberLiteral(Double value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Create numberLiteral from number
     *
     * @param value number
     * @return NumberLiteral
     */
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
