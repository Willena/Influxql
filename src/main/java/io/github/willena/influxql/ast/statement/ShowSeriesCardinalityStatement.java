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
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.source.Sources;
import java.util.List;

public class ShowSeriesCardinalityStatement implements Statement {
    private final String database;
    private final boolean exact;
    private final Sources sources;
    private final Expression condition;
    private final Dimensions dimensions;
    private final int limit;
    private final int offset;

    private ShowSeriesCardinalityStatement(Builder builder) {
        database = builder.database;
        exact = builder.exact;
        sources = builder.sources;
        condition = builder.condition;
        dimensions = builder.dimensions;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW SERIES");

        if (exact) {
            buf.append(" EXACT");
        }
        buf.append(" CARDINALITY");

        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }

        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
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

    /** {@code ShowSeriesCardinalityStatement} builder static inner class. */
    public static final class Builder implements Buildable<ShowSeriesCardinalityStatement> {
        private String database;
        private boolean exact;
        private Sources sources;
        private Expression condition;
        private Dimensions dimensions;
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
         * Sets the {@code exact} and returns a reference to this Builder enabling method chaining.
         *
         * @param exact the {@code exact} to set
         * @return a reference to this Builder
         */
        public Builder exact(boolean exact) {
            this.exact = exact;
            return this;
        }

        public Builder exact() {
            return exact(true);
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
         * Sets the {@code dimensions} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param dimensions the {@code dimensions} to set
         * @return a reference to this Builder
         */
        public Builder groupBy(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder groupBy(Dimension dimension, Dimension... dimensions) {
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
         * Returns a {@code ShowSeriesCardinalityStatement} built from the parameters previously
         * set.
         *
         * @return a {@code ShowSeriesCardinalityStatement} built with parameters of this {@code
         *     ShowSeriesCardinalityStatement.Builder}
         */
        public ShowSeriesCardinalityStatement build() {
            return new ShowSeriesCardinalityStatement(this);
        }
    }
}
