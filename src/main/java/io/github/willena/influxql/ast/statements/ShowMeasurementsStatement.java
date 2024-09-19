package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.SortFields;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.source.Measurement;

public class ShowMeasurementsStatement implements Statement {
    private final String database;
    private final String retentionPolicy;
    private final boolean wildcardDatabase;
    private final boolean wildcardRetentionPolicy;
    private final Source source;
    private final Expr condition;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;

    private ShowMeasurementsStatement(Builder builder) {
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;
        wildcardDatabase = builder.wildcardDatabase;
        wildcardRetentionPolicy = builder.wildcardRetentionPolicy;
        source = builder.source;
        condition = builder.condition;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW MEASUREMENTS");

        if (!database.isEmpty() || wildcardDatabase) {
            buf.append(" ON ");
            if (wildcardDatabase) {
                buf.append("*");
            } else {
                buf.append(database);
            }
            if (wildcardRetentionPolicy) {
                buf.append(".*");
            } else if (!retentionPolicy.isEmpty()) {
                buf.append(".");
                buf.append(retentionPolicy);
            }
        }
        if (source != null) {
            buf.append(" WITH MEASUREMENT ");
            boolean isRegex = ((Measurement) source).getRegex() != null;
            if (isRegex) {
                buf.append("=~ ");
            } else {
                buf.append("= ");
            }
            buf.append(source);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }

        if (!sortFields.isEmpty()) {
            buf.append(" ORDER BY ");
            buf.append(sortFields);
        }
        if (limit > 0) {
            buf.append(" LIMIT ");
            buf.append(limit);
        }
        if (offset > 0) {
            buf.append(" OFFSET ");
            buf.append(offset);
        }
        return buf.toString();
    }

    /**
     * {@code ShowMeasurementsStatement} builder static inner class.
     */
    public static final class Builder {
        private String database;
        private String retentionPolicy;
        private boolean wildcardDatabase;
        private boolean wildcardRetentionPolicy;
        private Source source;
        private Expr condition;
        private SortFields sortFields;
        private int limit;
        private int offset;

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
         * Sets the {@code wildcardDatabase} and returns a reference to this Builder enabling method chaining.
         *
         * @param wildcardDatabase the {@code wildcardDatabase} to set
         * @return a reference to this Builder
         */
        public Builder withWildcardDatabase(boolean wildcardDatabase) {
            this.wildcardDatabase = wildcardDatabase;
            return this;
        }

        /**
         * Sets the {@code wildcardRetentionPolicy} and returns a reference to this Builder enabling method chaining.
         *
         * @param wildcardRetentionPolicy the {@code wildcardRetentionPolicy} to set
         * @return a reference to this Builder
         */
        public Builder withWildcardRetentionPolicy(boolean wildcardRetentionPolicy) {
            this.wildcardRetentionPolicy = wildcardRetentionPolicy;
            return this;
        }

        /**
         * Sets the {@code source} and returns a reference to this Builder enabling method chaining.
         *
         * @param source the {@code source} to set
         * @return a reference to this Builder
         */
        public Builder withSource(Source source) {
            this.source = source;
            return this;
        }

        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder withCondition(Expr condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder withSortFields(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        /**
         * Sets the {@code limit} and returns a reference to this Builder enabling method chaining.
         *
         * @param limit the {@code limit} to set
         * @return a reference to this Builder
         */
        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the {@code offset} and returns a reference to this Builder enabling method chaining.
         *
         * @param offset the {@code offset} to set
         * @return a reference to this Builder
         */
        public Builder withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Returns a {@code ShowMeasurementsStatement} built from the parameters previously set.
         *
         * @return a {@code ShowMeasurementsStatement} built with parameters of this {@code ShowMeasurementsStatement.Builder}
         */
        public ShowMeasurementsStatement build() {
            return new ShowMeasurementsStatement(this);
        }
    }
}
