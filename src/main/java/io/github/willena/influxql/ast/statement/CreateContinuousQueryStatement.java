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

import static io.github.willena.influxql.ast.utils.Utils.*;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

public class CreateContinuousQueryStatement implements Statement {
    private final String name;
    private final String database;
    private final SelectStatement selectStatement;
    private final Duration resampleEvery;
    private final Duration resampleFor;

    private CreateContinuousQueryStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        selectStatement = builder.selectStatement;
        resampleEvery = builder.resampleEvery;
        resampleFor = builder.resampleFor;

        ensureDefined("name", name);
        ensureDefined("database", database);
        ensureDefined("selectStatement", selectStatement);
    }

    public String getName() {
        return name;
    }

    public String getDatabase() {
        return database;
    }

    public SelectStatement getSelectStatement() {
        return selectStatement;
    }

    public Duration getResampleEvery() {
        return resampleEvery;
    }

    public Duration getResampleFor() {
        return resampleFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateContinuousQueryStatement that = (CreateContinuousQueryStatement) o;
        return Objects.equals(name, that.name)
                && Objects.equals(database, that.database)
                && Objects.equals(selectStatement, that.selectStatement)
                && Objects.equals(resampleEvery, that.resampleEvery)
                && Objects.equals(resampleFor, that.resampleFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, database, selectStatement, resampleEvery, resampleFor);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(
                String.format(
                        "CREATE CONTINUOUS QUERY %s ON %s ",
                        quoteIdentifier(name), quoteIdentifier(database)));
        //	ResampleEvery time.Duration
        //	ResampleFor time.Duration
        // if s.ResampleEvery > 0 || s.ResampleFor > 0 {
        boolean hasResampleEvery =
                resampleEvery != null && resampleEvery.compareTo(Duration.ZERO) > 0;
        boolean hasResampleFor = resampleFor != null && resampleFor.compareTo(Duration.ZERO) > 0;
        if (hasResampleEvery || hasResampleFor) {
            buf.append("RESAMPLE ");
            if (hasResampleEvery) {
                buf.append(String.format("EVERY %s ", formatDuration(resampleEvery)));
            }
            if (hasResampleFor) {
                buf.append(String.format("FOR %s ", formatDuration(resampleFor)));
            }
        }
        buf.append(String.format("BEGIN %s END", selectStatement.toString()));
        return buf.toString();
    }

    public static CreateContinuousQueryStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::create_continuous_query_stmt,
                (c, a) -> a.visitCreate_continuous_query_stmt(c),
                sql);
    }

    /** {@code CreateContinuousQueryStatement} builder static inner class. */
    public static final class Builder implements Buildable<CreateContinuousQueryStatement> {
        private String name;
        private String database;
        private SelectStatement selectStatement;
        private Duration resampleEvery;
        private Duration resampleFor;

        public Builder() {}

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

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
         * Sets the {@code source} and returns a reference to this Builder enabling method chaining.
         *
         * @param source the {@code source} to set
         * @return a reference to this Builder
         */
        public Builder select(SelectStatement source) {
            this.selectStatement = source;
            return this;
        }

        public Builder select(Function<SelectStatement.Builder, SelectStatement.Builder> builder) {
            return select(builder.apply(new SelectStatement.Builder()).build());
        }

        /**
         * Sets the {@code resampleEvery} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param resampleEvery the {@code resampleEvery} to set
         * @return a reference to this Builder
         */
        public Builder resampleEvery(Duration resampleEvery) {
            this.resampleEvery = resampleEvery;
            return this;
        }

        /**
         * Sets the {@code resampleFor} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param resampleFor the {@code resampleFor} to set
         * @return a reference to this Builder
         */
        public Builder for_(Duration resampleFor) {
            this.resampleFor = resampleFor;
            return this;
        }

        /**
         * Returns a {@code CreateContinuousQueryStatement} built from the parameters previously
         * set.
         *
         * @return a {@code CreateContinuousQueryStatement} built with parameters of this {@code
         *     CreateContinuousQueryStatement.Builder}
         */
        public CreateContinuousQueryStatement build() {
            return new CreateContinuousQueryStatement(this);
        }
    }
}
