package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class KillQueryStatement implements Statement {
    private final Long queryId;
    private final String host;

    private KillQueryStatement(Builder builder) {
        queryId = builder.queryId;
        host = builder.host;
        ensureDefined("queryId", queryId);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("KILL QUERY ");
        buf.append(queryId);
        if (host != null && !host.isBlank()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(host));
        }
        return buf.toString();
    }

    /**
     * {@code KillQueryStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<KillQueryStatement> {
        private Long queryId;
        private String host;

        public Builder() {
        }

        /**
         * Sets the {@code queryId} and returns a reference to this Builder enabling method chaining.
         *
         * @param queryId the {@code queryId} to set
         * @return a reference to this Builder
         */
        public Builder withQueryId(Long queryId) {
            this.queryId = queryId;
            return this;
        }

        /**
         * Sets the {@code host} and returns a reference to this Builder enabling method chaining.
         *
         * @param host the {@code host} to set
         * @return a reference to this Builder
         */
        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        /**
         * Returns a {@code KillQueryStatement} built from the parameters previously set.
         *
         * @return a {@code KillQueryStatement} built with parameters of this {@code KillQueryStatement.Builder}
         */
        public KillQueryStatement build() {
            return new KillQueryStatement(this);
        }
    }
}
