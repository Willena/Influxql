package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class DropRetentionPolicyStatement implements Statement {

    private final String name;
    private final String database;


    private DropRetentionPolicyStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        ensureDefined("name", name);
        ensureDefined("database", database);
    }

    @Override
    public String toString() {
        return "DROP RETENTION POLICY " +
                QuoteIdent(name) +
                " ON " +
                QuoteIdent(database);
    }

    public static final class Builder implements Buildable<DropRetentionPolicyStatement> {
        private String name;
        private String database;

        public Builder() {
        }

        public Builder withRetentionPolicy(String name) {
            this.name = name;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public DropRetentionPolicyStatement build() {
            return new DropRetentionPolicyStatement(this);
        }
    }
}
