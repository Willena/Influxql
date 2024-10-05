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

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class ShowFieldKeysStatement implements Statement {
    private final String database;
    private final Sources sources;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;

    private ShowFieldKeysStatement(Builder builder) {
        database = builder.database;
        sources = builder.sources;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW FIELD KEYS");

        if (database != null && !database.isBlank()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }
        if (sources != null && !sources.isEmpty()) {
            buf.append(" FROM ");
            buf.append(sources);
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
     * {@code ShowFieldKeysStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<ShowFieldKeysStatement> {
        private String database;
        private Sources sources;
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
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method chaining.
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
         * Returns a {@code ShowFieldKeysStatement} built from the parameters previously set.
         *
         * @return a {@code ShowFieldKeysStatement} built with parameters of this {@code ShowFieldKeysStatement.Builder}
         */
        public ShowFieldKeysStatement build() {
            return new ShowFieldKeysStatement(this);
        }
    }
}
