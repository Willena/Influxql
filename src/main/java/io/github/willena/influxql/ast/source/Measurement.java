package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;

import java.util.regex.Pattern;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class Measurement implements Source {
    private final String database;
    private final String retentionPolicy;
    private final String name;
    private final Pattern regex;
    private final String systemIterator;

    private Measurement(Builder builder) {
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;
        name = builder.name;
        regex = builder.regex;
        systemIterator = builder.systemIterator;
    }


    public String getName() {
        return name;
    }

    public Pattern getRegex() {
        return regex;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        if (!database.isBlank()) {
            buf.append(QuoteIdent(database));
            buf.append(".");
        }

        if (!retentionPolicy.isBlank()) {
            buf.append(QuoteIdent(retentionPolicy));
        }

        if (!database.isEmpty() || !retentionPolicy.isEmpty()) {
            buf.append(".");
        }

        if (!name.isEmpty() && systemIterator.isEmpty()) {
            buf.append(QuoteIdent(name));
        } else if (!systemIterator.isEmpty()) {
            buf.append(QuoteIdent(systemIterator));
        } else if (regex != null) {
            buf.append(regex);
        }

        return buf.toString();
    }

    /**
     * {@code Measurement} builder static inner class.
     */
    public static final class Builder {
        private String database;
        private String retentionPolicy;
        private String name;
        private Pattern regex;
        private String systemIterator;

        public Builder() {
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
         * Sets the {@code regex} and returns a reference to this Builder enabling method chaining.
         *
         * @param regex the {@code regex} to set
         * @return a reference to this Builder
         */
        public Builder withRegex(Pattern regex) {
            this.regex = regex;
            return this;
        }

        public Builder withSystemIterator(String systemIterator) {
            this.systemIterator = systemIterator;
            return this;
        }

        /**
         * Returns a {@code Measurement} built from the parameters previously set.
         *
         * @return a {@code Measurement} built with parameters of this {@code Measurement.Builder}
         */
        public Measurement build() {
            return new Measurement(this);
        }
    }
}
