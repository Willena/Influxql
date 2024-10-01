package io.github.willena.influxql.ast.source;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Target {
    private final Measurement measurement;

    public Target(Measurement measurement) {
        this.measurement = measurement;
        ensureDefined("measurement", measurement);
    }

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
