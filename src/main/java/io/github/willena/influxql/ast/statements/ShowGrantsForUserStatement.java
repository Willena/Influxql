package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class ShowGrantsForUserStatement implements Statement {
    private final String name;

    public ShowGrantsForUserStatement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SHOW GRANTS FOR " + QuoteIdent(name);
    }
}
