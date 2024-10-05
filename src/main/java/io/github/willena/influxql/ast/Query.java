package io.github.willena.influxql.ast;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Top level {@link Node} is a {@link Query}
 * Each query can contain multiple statements
 */
public class Query implements Node {
    private final List<Statement> statements;

    /**
     * Build a new Query using a list of statements
     *
     * @param statements list of statements
     */
    public Query(List<Statement> statements) {
        this.statements = statements;
    }

    /**
     * Build a query based on provided statements (at least one)
     *
     * @param statement  a statement
     * @param statements other statements
     * @return a query with provided statements
     */
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
