package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.statement.SelectStatement;

import java.util.function.Function;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class SubQuery implements Source {
    private final SelectStatement statement;

    public SubQuery(SelectStatement statement) {
        this.statement = statement;
        ensureDefined("statement", statement);
    }

    public static SubQuery of(SelectStatement statement) {
        return new SubQuery(statement);
    }

    public static SubQuery of(Function<SelectStatement.Builder, SelectStatement.Builder> statement) {
        return new SubQuery(statement.apply(new SelectStatement.Builder()).build());
    }

    @Override
    public String toString() {
        return "(" + statement.toString() + ")";
    }
}
