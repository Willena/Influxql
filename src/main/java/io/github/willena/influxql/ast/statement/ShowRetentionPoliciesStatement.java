package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class ShowRetentionPoliciesStatement implements Statement {
    private final String database;

    private ShowRetentionPoliciesStatement(Builder builder) {
        database = builder.database;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW RETENTION POLICIES");
        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }
        return buf.toString();
    }

    public static final class Builder implements Buildable<ShowRetentionPoliciesStatement> {
        private String database;

        public Builder() {
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public ShowRetentionPoliciesStatement build() {
            return new ShowRetentionPoliciesStatement(this);
        }
    }
}
