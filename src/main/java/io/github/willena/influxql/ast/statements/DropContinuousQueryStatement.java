package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class DropContinuousQueryStatement implements Statement {

    private final String name;
    private final String database;

    public DropContinuousQueryStatement(String name, String database) {
        this.name = name;
        this.database = database;
    }

    @Override
    public String toString() {
        return "DROP CONTINUOUS QUERY " + QuoteIdent(name) + " ON " + QuoteIdent(database);
    }
}
