package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class ShowRetentionPoliciesStatement implements Statement {
    private final String database;

    public ShowRetentionPoliciesStatement(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW RETENTION POLICIES");
        if (!database.isEmpty()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(database));
        }
        return buf.toString();
    }
}
