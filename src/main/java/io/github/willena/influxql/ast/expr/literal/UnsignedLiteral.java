package io.github.willena.influxql.ast.expr.literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Unsigned literal:
 * This is the same as an integer literal
 */
public class UnsignedLiteral extends IntegerLiteral {
    /**
     * Build unsigned literal
     *
     * @param value value
     */
    public UnsignedLiteral(Long value) {
        super(value);
    }

    /**
     * Build unsigned literal
     *
     * @param value value
     * @return literal
     */
    public static UnsignedLiteral of(Number value) {
        ensureDefined("value", value);
        return new UnsignedLiteral(value.longValue());
    }
}
