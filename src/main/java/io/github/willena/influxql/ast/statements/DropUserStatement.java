package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class DropUserStatement implements Statement {
    private final String name;

    public DropUserStatement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DROP USER " + QuoteIdent(name);
    }
}
