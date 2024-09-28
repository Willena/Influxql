package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class IntegerLiteral implements Literal<Long> {
    private final Long value;

    public IntegerLiteral(Integer value) {
        this(value.longValue());
    }

    public IntegerLiteral(Long value) {
        ensureDefined("value", value);
        this.value = value;
    }

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
