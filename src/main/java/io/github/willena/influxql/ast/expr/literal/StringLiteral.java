package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteString;

/**
 * Simple string literal
 */
public class StringLiteral implements Literal<String> {
    private final String value;

    /**
     * Build string literal
     *
     * @param value string
     */
    public StringLiteral(String value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Create string literal
     *
     * @param value string
     * @return literal
     */
    public static StringLiteral of(String value) {
        return new StringLiteral(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return quoteString(value);
    }
}
