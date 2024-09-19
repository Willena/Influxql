package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.Utils;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class CreateSubscriptionStatement implements Statement {
    private final String name;
    private final String database;
    private final String retentionPolicy;
    private final List<String> destinations;
    private final String mode;

    private CreateSubscriptionStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;
        destinations = builder.destinations;
        mode = builder.mode;
    }

    @Override
    public String toString() {
        return "CREATE SUBSCRIPTION " +
                QuoteIdent(name) +
                " ON " +
                QuoteIdent(database) +
                "." +
                QuoteIdent(retentionPolicy) +
                " DESTINATIONS " +
                mode +
                " " +
                destinations.stream().map(Utils::QuoteString).collect(Collectors.joining(", "));
    }

    /**
     * {@code CreateSubscriptionStatement} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String database;
        private String retentionPolicy;
        private List<String> destinations;
        private String mode;

        public Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code database} and returns a reference to this Builder enabling method chaining.
         *
         * @param database the {@code database} to set
         * @return a reference to this Builder
         */
        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code retentionPolicy} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicy the {@code retentionPolicy} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicy(String retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
            return this;
        }

        /**
         * Sets the {@code destinations} and returns a reference to this Builder enabling method chaining.
         *
         * @param destinations the {@code destinations} to set
         * @return a reference to this Builder
         */
        public Builder withDestinations(List<String> destinations) {
            this.destinations = destinations;
            return this;
        }

        /**
         * Sets the {@code mode} and returns a reference to this Builder enabling method chaining.
         *
         * @param mode the {@code mode} to set
         * @return a reference to this Builder
         */
        public Builder withMode(String mode) {
            this.mode = mode;
            return this;
        }

        /**
         * Returns a {@code CreateSubscriptionStatement} built from the parameters previously set.
         *
         * @return a {@code CreateSubscriptionStatement} built with parameters of this {@code CreateSubscriptionStatement.Builder}
         */
        public CreateSubscriptionStatement build() {
            return new CreateSubscriptionStatement(this);
        }
    }
}
