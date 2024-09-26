package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.token.Operator;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;

public class ShowTagValuesStatement implements Statement {
    private final String database;
    private final Sources sources;
    private final Operator operator;
    private final Literal<?> tagKeyExpr;
    private final Expr condition;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;

    private ShowTagValuesStatement(Builder builder) {
        database = builder.database;
        sources = builder.sources;
        operator = builder.operator;
        tagKeyExpr = builder.tagKeyExpr;
        condition = builder.condition;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW TAG VALUES");

        if (!database.isEmpty()) {
            buf.append(" ON ");
            buf.append(QuoteIdent(database));
        }
        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        buf.append(" WITH KEY ");
        buf.append(operator);
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
     * {@code ShowTagValuesStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<ShowTagValuesStatement> {
        private String database;
        private Sources sources;
        private Operator operator;
        private Literal<?> tagKeyExpr;
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
         * Sets the {@code operator} and returns a reference to this Builder enabling method chaining.
         *
         * @param operator the {@code operator} to set
         * @return a reference to this Builder
         */
        public Builder withOperator(Operator operator) {
            this.operator = operator;
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
         * Returns a {@code ShowTagValuesStatement} built from the parameters previously set.
         *
         * @return a {@code ShowTagValuesStatement} built with parameters of this {@code ShowTagValuesStatement.Builder}
         */
        public ShowTagValuesStatement build() {
            return new ShowTagValuesStatement(this);
        }
    }
}
