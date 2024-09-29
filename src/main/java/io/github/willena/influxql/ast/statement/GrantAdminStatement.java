package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class GrantAdminStatement implements Statement {
    private final String user;

    private GrantAdminStatement(Builder builder) {
        user = builder.user;
        ensureDefined("user", user);
    }

    @Override
    public String toString() {
        return "GRANT ALL PRIVILEGES TO " + quoteIdentifier(user);
    }

    public static final class Builder implements Buildable<GrantAdminStatement> {
        private String user;

        public Builder() {
        }

        public Builder withUsername(String user) {
            this.user = user;
            return this;
        }

        public GrantAdminStatement build() {
            return new GrantAdminStatement(this);
        }
    }
}
