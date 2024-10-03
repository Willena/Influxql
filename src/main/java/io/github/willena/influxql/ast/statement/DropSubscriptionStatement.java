package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class DropSubscriptionStatement implements Statement {
    private final String name;
    private final String database;
    private final String retentionPolicy;


    private DropSubscriptionStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;

        ensureDefined("name", name);
        ensureDefined("database", database);
        ensureDefined("retentionPolicy", retentionPolicy);
    }

    @Override
    public String toString() {
        return "DROP SUBSCRIPTION " + quoteIdentifier(name) + " ON " + quoteIdentifier(database) + "." + quoteIdentifier(retentionPolicy);
    }

    public static final class Builder implements Buildable<DropSubscriptionStatement> {
        private String name;
        private String database;
        private String retentionPolicy;

        public Builder() {
        }

        public Builder subscription(String name) {
            this.name = name;
            return this;
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public Builder retentionPolicy(String retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
            return this;
        }

        public DropSubscriptionStatement build() {
            return new DropSubscriptionStatement(this);
        }
    }
}
