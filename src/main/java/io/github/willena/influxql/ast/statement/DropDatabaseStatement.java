package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class DropDatabaseStatement implements Statement {
    private final String name;

    private DropDatabaseStatement(Builder builder) {
        name = builder.name;
    }

    @Override
    public String toString() {
        return "DROP DATABASE " + quoteIdentifier(name);
    }

    public static final class Builder implements Buildable<DropDatabaseStatement> {
        private String name;

        public Builder() {
        }

        public Builder database(String name) {
            this.name = name;
            return this;
        }

        public DropDatabaseStatement build() {
            return new DropDatabaseStatement(this);
        }
    }
}
