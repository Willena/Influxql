package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.time.Instant;

public class TimeLiteral implements Literal<Instant> {
    private final Instant value;

    public TimeLiteral(Instant value) {
        this.value = value;
    }

    @Override
    public Instant getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "'" + value.toString() + "'";
    }
}
