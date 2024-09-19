package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class DropMeasurementStatement implements Statement {
    private final String name;

    public DropMeasurementStatement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DROP MEASUREMENT " + QuoteIdent(name);
    }
}
