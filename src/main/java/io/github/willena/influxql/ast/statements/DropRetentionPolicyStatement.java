package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class DropRetentionPolicyStatement implements Statement {

    private final String name;
    private final String database;

    public DropRetentionPolicyStatement(String name, String database) {
        this.name = name;
        this.database = database;
    }

    @Override
    public String toString() {
        return "DROP RETENTION POLICY " +
                QuoteIdent(name) +
                " ON " +
                QuoteIdent(database);
    }
}
