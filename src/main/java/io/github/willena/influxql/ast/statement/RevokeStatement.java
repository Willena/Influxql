package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.token.Privilege;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class RevokeStatement implements Statement {
    private final Privilege privilege;
    private final String database;
    private final String username;


    private RevokeStatement(Builder builder) {
        privilege = builder.privilege;
        database = builder.database;
        username = builder.username;
        ensureDefined("privilege", privilege);
        ensureDefined("database", database);
        ensureDefined("username", username);
    }

    @Override
    public String toString() {
        return "REVOKE " +
                privilege.toString() +
                " ON " +
                QuoteIdent(database) +
                " FROM " +
                QuoteIdent(username);
    }

    public static final class Builder implements Buildable<RevokeStatement> {
        private Privilege privilege;
        private String database;
        private String username;

        public Builder() {
        }

        public Builder withPrivilege(Privilege privilege) {
            this.privilege = privilege;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public RevokeStatement build() {
            return new RevokeStatement(this);
        }
    }
}
