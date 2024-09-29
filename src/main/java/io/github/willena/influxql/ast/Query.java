package io.github.willena.influxql.ast;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Query implements Node {
    private final List<Statement> statements;

    public Query(List<Statement> statements) {
        this.statements = statements;
    }

    public static Query ofStatements(Statement statement, Statement... statements) {
        List<Statement> list = new LinkedList<>();
        list.add(statement);
        list.addAll(List.of(statements));
        return new Query(list);
    }

    @Override
    public String toString() {
        return statements.stream().map(Statement::toString).collect(Collectors.joining("; "));
    }
}
