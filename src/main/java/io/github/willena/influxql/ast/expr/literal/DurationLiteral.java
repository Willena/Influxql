package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.formatDuration;

/**
 * Duration literal
 */
public class DurationLiteral implements Literal<Duration> {
    private final Duration value;

    /**
     * Create from java duration
     *
     * @param value duration
     */
    public DurationLiteral(final Duration value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Create from java duration
     *
     * @param value duration
     * @return DurationLiteral
     */
    public static DurationLiteral of(Duration value) {
        return new DurationLiteral(value);
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
