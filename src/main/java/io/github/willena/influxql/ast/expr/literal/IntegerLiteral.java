package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

public class IntegerLiteral implements Literal<Integer> {
    private final Integer value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
