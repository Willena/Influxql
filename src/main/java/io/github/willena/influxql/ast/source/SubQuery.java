package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.statement.SelectStatement;

public class SubQuery implements Source {
    private final SelectStatement statement;

    public SubQuery(SelectStatement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "(" + statement.toString() + ")";
    }
}
