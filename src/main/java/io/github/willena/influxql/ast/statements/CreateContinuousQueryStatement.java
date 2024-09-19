package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import java.time.Duration;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.Utils.formatDuration;

public class CreateContinuousQueryStatement implements Statement {
    private final String name;
    private final String database;
    private final SelectStatement source;
    private final Duration resampleEvery;
    private final Duration resampleFor;

    private CreateContinuousQueryStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        source = builder.source;
        resampleEvery = builder.resampleEvery;
        resampleFor = builder.resampleFor;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("CREATE CONTINUOUS QUERY %s ON %s ", QuoteIdent(name), QuoteIdent(database)));
        //	ResampleEvery time.Duration
        //	ResampleFor time.Duration
        // if s.ResampleEvery > 0 || s.ResampleFor > 0 {
        boolean hasResampleEvery = resampleEvery != null && resampleEvery.compareTo(Duration.ZERO) > 0;
        boolean hasResampleFor = resampleFor != null && resampleFor.compareTo(Duration.ZERO) > 0;
        if (hasResampleEvery || hasResampleFor) {
            buf.append("RESAMPLE ");
            if (hasResampleEvery) {
                buf.append(String.format("EVERY %s ", formatDuration(resampleEvery)));
            }
            if (hasResampleFor) {
                buf.append(String.format("FOR %s ", formatDuration(resampleFor)));
            }
        }
        buf.append(String.format("BEGIN %s END", source.toString()));
        return buf.toString();
    }

    /**
     * {@code CreateContinuousQueryStatement} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String database;
        private SelectStatement source;
        private Duration resampleEvery;
        private Duration resampleFor;

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
         * Sets the {@code source} and returns a reference to this Builder enabling method chaining.
         *
         * @param source the {@code source} to set
         * @return a reference to this Builder
         */
        public Builder withSource(SelectStatement source) {
            this.source = source;
            return this;
        }

        /**
         * Sets the {@code resampleEvery} and returns a reference to this Builder enabling method chaining.
         *
         * @param resampleEvery the {@code resampleEvery} to set
         * @return a reference to this Builder
         */
        public Builder withResampleEvery(Duration resampleEvery) {
            this.resampleEvery = resampleEvery;
            return this;
        }

        /**
         * Sets the {@code resampleFor} and returns a reference to this Builder enabling method chaining.
         *
         * @param resampleFor the {@code resampleFor} to set
         * @return a reference to this Builder
         */
        public Builder withResampleFor(Duration resampleFor) {
            this.resampleFor = resampleFor;
            return this;
        }

        /**
         * Returns a {@code CreateContinuousQueryStatement} built from the parameters previously set.
         *
         * @return a {@code CreateContinuousQueryStatement} built with parameters of this {@code CreateContinuousQueryStatement.Builder}
         */
        public CreateContinuousQueryStatement build() {
            return new CreateContinuousQueryStatement(this);
        }
    }
}
