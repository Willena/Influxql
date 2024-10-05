package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Integer literal
 */
public class IntegerLiteral implements Literal<Long>, NumericLiteral {
    private final Long value;

    /**
     * Create a new integer literal
     *
     * @param value integer
     */
    public IntegerLiteral(Long value) {
        ensureDefined("value", value);
        this.value = value;
    }

    /**
     * Create a new integer literal from number
     *
     * @param i integer
     * @return IntegerLiteral
     */
    public static IntegerLiteral of(Number i) {
        ensureDefined("value", i);
        return new IntegerLiteral(i.longValue());
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
