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

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.List;
import java.util.Objects;

public class ShowSeriesStatement implements Statement {
    private final String database;
    private final Sources sources;
    private final Expression conditions;
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

        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }
        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }

        if (conditions != null) {
            buf.append(" WHERE ");
            buf.append(conditions);
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

    public String getDatabase() {
        return database;
    }

    public Sources getSources() {
        return sources;
    }

    public Expression getConditions() {
        return conditions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowSeriesStatement that = (ShowSeriesStatement) o;
        return limit == that.limit
                && offset == that.offset
                && Objects.equals(database, that.database)
                && Objects.equals(sources, that.sources)
                && Objects.equals(conditions, that.conditions)
                && Objects.equals(sortFields, that.sortFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, sources, conditions, sortFields, limit, offset);
    }

    public static ShowSeriesStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::show_series_stmt, (c, a) -> a.visitShow_series_stmt(c), sql);
    }

    /** {@code ShowSeriesStatement} builder static inner class. */
    public static final class Builder implements Buildable<ShowSeriesStatement> {
        private String database;
        private Sources sources;
        private Expression conditions;
        private SortFields sortFields;
        private int limit;
        private int offset;

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
         * Sets the {@code conditions} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param conditions the {@code conditions} to set
         * @return a reference to this Builder
         */
        public Builder where(Expression conditions) {
            this.conditions = conditions;
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

        /**
         * Returns a {@code ShowSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code ShowSeriesStatement} built with parameters of this {@code
         *     ShowSeriesStatement.Builder}
         */
        public ShowSeriesStatement build() {
            return new ShowSeriesStatement(this);
        }
    }
}
