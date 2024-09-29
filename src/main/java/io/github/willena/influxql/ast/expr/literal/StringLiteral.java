package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.quoteString;

public class StringLiteral implements Literal<String> {
    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

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
