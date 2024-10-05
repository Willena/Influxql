package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.time.Instant;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Time / Timestamp literal
 */
public class TimestampLiteral implements Literal<Instant> {
    private final Instant value;

    /**
     * Build timestamp literal form instant
     *
     * @param value instant
     */
    public TimestampLiteral(Instant value) {
        this.value = value;
        ensureDefined("value", value);
    }


    /**
     * Build timestamp literal form instant
     *
     * @param instant instant
     * @return a new literal
     */
    public static TimestampLiteral of(Instant instant) {
        return new TimestampLiteral(instant);
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
