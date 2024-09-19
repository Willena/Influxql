package io.github.willena.influxql.ast;

import java.util.List;
import java.util.stream.Collectors;

public class Query implements Node {
    private final List<Statement> statements;

    public Query(List<Statement> statements) {
        this.statements = statements;
    }

    public Query ofStatements(Statement... statements) {
        return new Query(List.of(statements));
    }

    @Override
    public String toString() {
        return statements.stream().map(Statement::toString).collect(Collectors.joining("; "));
    }
}
