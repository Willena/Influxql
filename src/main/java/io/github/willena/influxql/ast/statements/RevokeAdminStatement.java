package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class RevokeAdminStatement implements Statement {
    private final String user;

    public RevokeAdminStatement(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "REVOKE ALL PRIVILEGES FROM " + QuoteIdent(user);
    }
}
