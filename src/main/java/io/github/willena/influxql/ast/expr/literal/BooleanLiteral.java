package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

/**
 * Boolean literal
 */
public class BooleanLiteral implements Literal<Boolean> {

    private final boolean value;

    /**
     * Create from boolean
     *
     * @param value bool
     */
    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    /**
     * Create from boolean
     *
     * @param value bool
     * @return BooleanLiteral
     */
    public static BooleanLiteral of(boolean value) {
        return new BooleanLiteral(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }
}
