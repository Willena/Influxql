package io.github.willena.influxql.ast.source;

public class Target {
    private final Measurement measurement;

    public Target(Measurement measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("INTO ");
        buf.append(measurement.toString());
        if (measurement.getName().isBlank()) {
            buf.append(":MEASUREMENT");
        }
        return buf.toString();
    }
}
