package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class DropMeasurementStatement implements Statement {
    private final String name;


    private DropMeasurementStatement(Builder builder) {
        name = builder.name;
        ensureDefined("name", name);

    }

    @Override
    public String toString() {
        return "DROP MEASUREMENT " + QuoteIdent(name);
    }

    public static final class Builder implements Buildable<DropMeasurementStatement> {
        private String name;

        public Builder() {
        }

        public Builder withMeasurement(String name) {
            this.name = name;
            return this;
        }

        public DropMeasurementStatement build() {
            return new DropMeasurementStatement(this);
        }
    }
}
