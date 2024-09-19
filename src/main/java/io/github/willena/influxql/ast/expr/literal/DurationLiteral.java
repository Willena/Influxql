package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.time.Duration;

import static io.github.willena.influxql.ast.Utils.formatDuration;

public class DurationLiteral implements Literal<Duration> {
    private final Duration value;

    public DurationLiteral(final Duration value) {
        this.value = value;
    }

    @Override
    public Duration getValue() {
        return value;
    }

    @Override
    public String toString() {
        return formatDuration(value);
    }
}
