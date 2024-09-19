package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.source.Sources;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class ShowMeasurementCardinalityStatement implements Statement {
    private final boolean exact;
    private final String database;
    private final Sources sources;
    private final Expr condition;
    private final Dimensions dimensions;
    private final int limit;
    private final int offset;

    private ShowMeasurementCardinalityStatement(Builder builder) {
        exact = builder.exact;
        database = builder.database;
        sources = builder.sources;
        condition = builder.condition;
        dimensions = builder.dimensions;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW MEASUREMENT");

        if (exact) {
            buf.append(" EXACT");
        }
        buf.append(" CARDINALITY");

        if (!database.isEmpty()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(database));
        }

        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        if (!dimensions.isEmpty()) {
            buf.append(" GROUP BY ");
            buf.append(dimensions);
        }
        if (limit > 0) {
            buf.append(" LIMIT ").append(limit);
        }
        if (offset > 0) {
            buf.append(" OFFSET ");
            buf.append(offset);
        }
        return buf.toString();
    }

    /**
     * {@code ShowMeasurementCardinalityStatement} builder static inner class.
     */
    public static final class Builder {
        private boolean exact;
        private String database;
        private Sources sources;
        private Expr condition;
        private Dimensions dimensions;
        private int limit;
        private int offset;

        public Builder() {
        }

        /**
         * Sets the {@code exact} and returns a reference to this Builder enabling method chaining.
         *
         * @param exact the {@code exact} to set
         * @return a reference to this Builder
         */
        public Builder withExact(boolean exact) {
            this.exact = exact;
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
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withSources(Sources sources) {
            this.sources = sources;
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
         * Sets the {@code dimensions} and returns a reference to this Builder enabling method chaining.
         *
         * @param dimensions the {@code dimensions} to set
         * @return a reference to this Builder
         */
        public Builder withDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
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
         * Returns a {@code ShowMeasurementCardinalityStatement} built from the parameters previously set.
         *
         * @return a {@code ShowMeasurementCardinalityStatement} built with parameters of this {@code ShowMeasurementCardinalityStatement.Builder}
         */
        public ShowMeasurementCardinalityStatement build() {
            return new ShowMeasurementCardinalityStatement(this);
        }
    }
}
