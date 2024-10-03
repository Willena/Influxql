package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class ShowTagValuesStatement implements Statement {
    private final String database;
    private final Sources sources;
    private final Operator operator;
    private final Literal<?> tagKeyExpr;
    private final Expression condition;
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
        ensureDefined("operator", operator);
        ensureDefined("tagKeyExpr", tagKeyExpr);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW TAG VALUES");

        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }
        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        buf.append(" WITH KEY ");
        buf.append(operator);
        buf.append(" ");
        if (tagKeyExpr instanceof StringLiteral) {
            buf.append(quoteIdentifier(((StringLiteral) tagKeyExpr).getValue()));
        } else {
            buf.append(tagKeyExpr);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        if (sortFields != null && !sortFields.isEmpty()) {
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
        private Expression condition;
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
        public Builder on(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder from(Sources sources) {
            this.sources = sources;
            return this;
        }

        public Builder from(Source source, Source... sources) {
            if (this.sources == null) {
                this.sources = new Sources();
            }
            this.sources.add(source);
            this.sources.addAll(List.of(sources));
            return this;
        }

        /**
         * Sets the {@code tagKeyExpr} and returns a reference to this Builder enabling method chaining.
         *
         * @param tagKeyExpr the {@code tagKeyExpr} to set
         * @return a reference to this Builder
         */
        public Builder withKey(Operator op, Literal<?> tagKeyExpr) {
            this.tagKeyExpr = tagKeyExpr;
            this.operator = op;
            return this;
        }

        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder where(Expression condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder orderBY(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        public Builder orderBY(SortField sortField, SortField... sortFields) {
            if (this.sortFields == null) {
                this.sortFields = new SortFields();
            }
            this.sortFields.add(sortField);
            this.sortFields.addAll(List.of(sortFields));
            return this;
        }

        /**
         * Sets the {@code limit} and returns a reference to this Builder enabling method chaining.
         *
         * @param limit the {@code limit} to set
         * @return a reference to this Builder
         */
        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the {@code offset} and returns a reference to this Builder enabling method chaining.
         *
         * @param offset the {@code offset} to set
         * @return a reference to this Builder
         */
        public Builder offset(int offset) {
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
