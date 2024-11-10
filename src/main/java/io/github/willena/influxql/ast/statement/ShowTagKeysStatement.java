/*
 * InfluxQL Java package
 * Copyright 2024 Guillaume VILLENA also known as "Willena" on GitHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.willena.influxql.ast.statement;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.token.Operator;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.List;
import java.util.Objects;

public class ShowTagKeysStatement implements Statement {
    private final String database;
    private final Operator op;
    private final Literal<?> tagKeyExpr;
    private final Sources sources;
    private final Expression condition;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;
    private final int sLimit;
    private final int sOffset;

    private ShowTagKeysStatement(Builder builder) {
        database = builder.database;
        op = builder.op;
        tagKeyExpr = builder.tagKeyExpr;
        sources = builder.sources;
        condition = builder.condition;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
        sLimit = builder.sLimit;
        sOffset = builder.sOffset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW TAG KEYS");

        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }

        if (op != null && tagKeyExpr != null) {
            buf.append(" WITH KEY ");
            buf.append(op);
            buf.append(" ");
            if (tagKeyExpr instanceof StringLiteral) {
                buf.append(quoteIdentifier(((StringLiteral) tagKeyExpr).getValue()));
            } else {
                buf.append(tagKeyExpr);
            }
        }

        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
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
        if (sLimit > 0) {
            buf.append(" SLIMIT ");
            buf.append(sLimit);
        }
        if (sOffset > 0) {
            buf.append(" SOFFSET ");
            buf.append(sOffset);
        }
        return buf.toString();
    }

    public String getDatabase() {
        return database;
    }

    public Operator getOp() {
        return op;
    }

    public Literal<?> getTagKeyExpr() {
        return tagKeyExpr;
    }

    public Sources getSources() {
        return sources;
    }

    public Expression getCondition() {
        return condition;
    }

    public SortFields getSortFields() {
        return sortFields;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getsLimit() {
        return sLimit;
    }

    public int getsOffset() {
        return sOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTagKeysStatement that = (ShowTagKeysStatement) o;
        return limit == that.limit
                && offset == that.offset
                && sLimit == that.sLimit
                && sOffset == that.sOffset
                && Objects.equals(database, that.database)
                && op == that.op
                && Objects.equals(tagKeyExpr, that.tagKeyExpr)
                && Objects.equals(sources, that.sources)
                && Objects.equals(condition, that.condition)
                && Objects.equals(sortFields, that.sortFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                database,
                op,
                tagKeyExpr,
                sources,
                condition,
                sortFields,
                limit,
                offset,
                sLimit,
                sOffset);
    }

    public static ShowTagKeysStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::show_tag_keys_stmt, (c, a) -> a.visitShow_tag_keys_stmt(c), sql);
    }

    /** {@code ShowTagKeysStatement} builder static inner class. */
    public static final class Builder implements Buildable<ShowTagKeysStatement> {
        private String database;
        private Operator op;
        private Literal<?> tagKeyExpr;
        private Sources sources;
        private Expression condition;
        private SortFields sortFields;
        private int limit;
        private int offset;
        private int sLimit;
        private int sOffset;

        public Builder() {}

        /**
         * Sets the {@code database} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param database the {@code database} to set
         * @return a reference to this Builder
         */
        public Builder on(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code tagKeyExpr} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param tagKeyExpr the {@code tagKeyExpr} to set
         * @return a reference to this Builder
         */
        public Builder withKey(Operator op, Literal<?> tagKeyExpr) {
            this.tagKeyExpr = tagKeyExpr;
            this.op = op;
            return this;
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method
         * chaining.
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
         * Sets the {@code condition} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder where(Expression condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder orderBy(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        public Builder orderBy(SortField sortField, SortField... sortFields) {
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

        public Builder sLimit(int sLimit) {
            this.sLimit = sLimit;
            return this;
        }

        public Builder sOffset(int sOffset) {
            this.sOffset = sOffset;
            return this;
        }

        /**
         * Returns a {@code ShowTagKeysStatement} built from the parameters previously set.
         *
         * @return a {@code ShowTagKeysStatement} built with parameters of this {@code
         *     ShowTagKeysStatement.Builder}
         */
        public ShowTagKeysStatement build() {
            return new ShowTagKeysStatement(this);
        }
    }
}
