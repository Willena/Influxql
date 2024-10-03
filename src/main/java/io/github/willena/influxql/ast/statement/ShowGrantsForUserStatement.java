package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class ShowGrantsForUserStatement implements Statement {
    private final String name;

    private ShowGrantsForUserStatement(Builder builder) {
        name = builder.name;
        ensureDefined("name", name);
    }

    @Override
    public String toString() {
        return "SHOW GRANTS FOR " + quoteIdentifier(name);
    }

    public static final class Builder implements Buildable<ShowGrantsForUserStatement> {
        private String name;

        public Builder() {
        }

        public Builder for_(String name) {
            this.name = name;
            return this;
        }

        public ShowGrantsForUserStatement build() {
            return new ShowGrantsForUserStatement(this);
        }
    }
}
