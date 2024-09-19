package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class GrantAdminStatement implements Statement {
    private final String user;

    public GrantAdminStatement(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GRANT ALL PRIVILEGES TO " + QuoteIdent(user);
    }
}
