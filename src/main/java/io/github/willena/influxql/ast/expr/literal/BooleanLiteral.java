package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

public class BooleanLiteral implements Literal<Boolean> {

    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
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
