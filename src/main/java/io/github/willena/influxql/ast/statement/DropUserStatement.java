package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class DropUserStatement implements Statement {
    private final String name;

    private DropUserStatement(Builder builder) {
        name = builder.name;
        ensureDefined("name", name);
    }

    @Override
    public String toString() {
        return "DROP USER " + quoteIdentifier(name);
    }

    public static final class Builder implements Buildable<DropUserStatement> {
        private String name;

        public Builder() {
        }

        public Builder withUsername(String name) {
            this.name = name;
            return this;
        }

        public DropUserStatement build() {
            return new DropUserStatement(this);
        }
    }
}
