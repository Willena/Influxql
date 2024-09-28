package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class ShowTagValuesCardinalityStatement implements Statement {
    private final String database;
    private final boolean exact;
    private final Sources sources;
    private final Operator op;
    private final Literal<?> tagKeyExpr;
    private final Expr condition;
    private final Dimensions dimensions;
    private final int limit;
    private final int offset;

    private ShowTagValuesCardinalityStatement(Builder builder) {
        database = builder.database;
        exact = builder.exact;
        sources = builder.sources;
        op = builder.op;
        tagKeyExpr = builder.tagKeyExpr;
        condition = builder.condition;
        dimensions = builder.dimensions;
        limit = builder.limit;
        offset = builder.offset;
        ensureDefined("op", op);
        ensureDefined("tagKeyExpr", tagKeyExpr);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW TAG VALUES ");

        if (exact) {
            buf.append("EXACT ");
        }
        buf.append("CARDINALITY");

        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(database));
        }
        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        buf.append(" WITH KEY ");
        buf.append(op);
        buf.append(" ");
        if (tagKeyExpr instanceof StringLiteral) {
            buf.append(QuoteIdent(((StringLiteral) tagKeyExpr).getValue()));
        } else {
            buf.append(tagKeyExpr);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        if (dimensions != null && !dimensions.isEmpty()) {
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
     * {@code ShowTagValuesCardinalityStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<ShowTagValuesCardinalityStatement> {
        private String database;
        private boolean exact;
        private Sources sources;
        private Operator op;
        private Literal<?> tagKeyExpr;
        private Expr condition;
        private Dimensions dimensions;
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
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withSources(Sources sources) {
            this.sources = sources;
            return this;
        }

        public Builder withSources(Source source, Source... sources) {
            if (this.sources == null) {
                this.sources = new Sources();
            }
            this.sources.add(source);
            this.sources.addAll(List.of(sources));
            return this;
        }

        /**
         * Sets the {@code op} and returns a reference to this Builder enabling method chaining.
         *
         * @param op the {@code op} to set
         * @return a reference to this Builder
         */
        public Builder withOp(Operator op) {
            this.op = op;
            return this;
        }

        /**
         * Sets the {@code tagKeyExpr} and returns a reference to this Builder enabling method chaining.
         *
         * @param tagKeyExpr the {@code tagKeyExpr} to set
         * @return a reference to this Builder
         */
        public Builder withTagKeyExpr(Literal<?> tagKeyExpr) {
            this.tagKeyExpr = tagKeyExpr;
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

        public Builder withDimensions(Dimension dimension, Dimension... dimensions) {
            if (this.dimensions == null) {
                this.dimensions = new Dimensions();
            }
            this.dimensions.add(dimension);
            this.dimensions.addAll(List.of(dimensions));
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
         * Returns a {@code ShowTagValuesCardinalityStatement} built from the parameters previously set.
         *
         * @return a {@code ShowTagValuesCardinalityStatement} built with parameters of this {@code ShowTagValuesCardinalityStatement.Builder}
         */
        public ShowTagValuesCardinalityStatement build() {
            return new ShowTagValuesCardinalityStatement(this);
        }
    }
}
