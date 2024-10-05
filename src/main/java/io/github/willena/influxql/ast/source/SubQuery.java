package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.statement.SelectStatement;

import java.util.function.Function;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Define a subquery containing a single {@link SelectStatement}
 */
public class SubQuery implements Source {
    private final SelectStatement statement;

    /**
     * Build a new {@link SubQuery}
     *
     * @param statement select statement builder
     */
    public SubQuery(SelectStatement statement) {
        this.statement = statement;
        ensureDefined("statement", statement);
    }

    /**
     * Helper method to build a subquery
     *
     * @param statement {@link SelectStatement} value
     * @return the {@link SubQuery}
     */
    public static SubQuery of(SelectStatement statement) {
        return new SubQuery(statement);
    }

    /**
     * Helper method to build a subquery
     *
     * @param statement select statement builder
     * @return the {@link SubQuery}
     */
    public static SubQuery of(Function<SelectStatement.Builder, SelectStatement.Builder> statement) {
        return new SubQuery(statement.apply(new SelectStatement.Builder()).build());
    }

    @Override
    public String toString() {
        return "(" + statement.toString() + ")";
    }
}
