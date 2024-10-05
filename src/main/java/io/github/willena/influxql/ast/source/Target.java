package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Node;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Destination Measurement when using SELECT INTO statements
 */
public class Target implements Node {
    private final Measurement measurement;

    /**
     * Build a new Target
     *
     * @param measurement measurement to use
     */
    public Target(Measurement measurement) {
        this.measurement = measurement;
        ensureDefined("measurement", measurement);
    }

    /**
     * Build a {@link Target} from {@link Measurement}
     *
     * @param measurement the measurement
     * @return the {@link Target}
     */
    public static Target of(Measurement measurement) {
        return new Target(measurement);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("INTO ");
        buf.append(measurement.toString());
        if (measurement.getName() == null || measurement.getName().isBlank()) {
            buf.append(":MEASUREMENT");
        }
        return buf.toString();
    }
}
