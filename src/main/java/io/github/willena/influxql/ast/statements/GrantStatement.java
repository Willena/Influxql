package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Privilege;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class GrantStatement implements Statement {
    private final Privilege privilege;
    private final String on;
    private final String user;

    public GrantStatement(Privilege privilege, String on, String user) {
        this.privilege = privilege;
        this.on = on;
        this.user = user;
    }

    @Override
    public String toString() {
        return "GRANT " +
                privilege.toString() +
                " ON " +
                QuoteIdent(on) +
                " TO " +
                QuoteIdent(user);
    }
}
