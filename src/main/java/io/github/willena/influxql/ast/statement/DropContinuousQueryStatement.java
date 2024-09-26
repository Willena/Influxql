package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class DropContinuousQueryStatement implements Statement {

    private final String name;
    private final String database;

    private DropContinuousQueryStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        ensureDefined("name", name);
        ensureDefined("database", database);
    }

    @Override
    public String toString() {
        return "DROP CONTINUOUS QUERY " + QuoteIdent(name) + " ON " + QuoteIdent(database);
    }

    public static final class Builder implements Buildable<DropContinuousQueryStatement> {
        private String name;
        private String database;

        public Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public DropContinuousQueryStatement build() {
            return new DropContinuousQueryStatement(this);
        }
    }
}
