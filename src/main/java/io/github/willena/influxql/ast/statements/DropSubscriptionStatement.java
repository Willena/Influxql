package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class DropSubscriptionStatement implements Statement {
    private final String name;
    private final String database;
    private final String retentionPolicy;

    public DropSubscriptionStatement(String name, String database, String retentionPolicy) {
        this.name = name;
        this.database = database;
        this.retentionPolicy = retentionPolicy;
    }

    @Override
    public String toString() {
        return "DROP SUBSCRIPTION " + QuoteIdent(name) + " ON " + QuoteIdent(database) + "." + QuoteIdent(retentionPolicy);
    }
}
