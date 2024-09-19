package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class KillQueryStatement implements Statement {
    private final long queryId;
    private final String host;

    private KillQueryStatement(Builder builder) {
        queryId = builder.queryId;
        host = builder.host;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("KILL QUERY ");
        buf.append(queryId);
        if (!host.isBlank()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(host));
        }
        return buf.toString();
    }

    /**
     * {@code KillQueryStatement} builder static inner class.
     */
    public static final class Builder {
        private long queryId;
        private String host;

        public Builder() {
        }

        /**
         * Sets the {@code queryId} and returns a reference to this Builder enabling method chaining.
         *
         * @param queryId the {@code queryId} to set
         * @return a reference to this Builder
         */
        public Builder withQueryId(long queryId) {
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
