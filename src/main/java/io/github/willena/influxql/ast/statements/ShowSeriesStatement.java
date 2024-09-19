package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.SortFields;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.source.Sources;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class ShowSeriesStatement implements Statement {
    private final String database;
    private final Sources sources;
    private final Expr conditions;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;

    private ShowSeriesStatement(Builder builder) {
        database = builder.database;
        sources = builder.sources;
        conditions = builder.conditions;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW SERIES");

        if (!database.isEmpty()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(database));
        }
        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }

        if (conditions != null) {
            buf.append(" WHERE ");
            buf.append(conditions);
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
     * {@code ShowSeriesStatement} builder static inner class.
     */
    public static final class Builder {
        private String database;
        private Sources sources;
        private Expr conditions;
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
         * Sets the {@code conditions} and returns a reference to this Builder enabling method chaining.
         *
         * @param conditions the {@code conditions} to set
         * @return a reference to this Builder
         */
        public Builder withConditions(Expr conditions) {
            this.conditions = conditions;
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
         * Returns a {@code ShowSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code ShowSeriesStatement} built with parameters of this {@code ShowSeriesStatement.Builder}
         */
        public ShowSeriesStatement build() {
            return new ShowSeriesStatement(this);
        }
    }
}
