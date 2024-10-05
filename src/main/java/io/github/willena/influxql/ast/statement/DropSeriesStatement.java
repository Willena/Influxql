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
import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.source.Sources;

import java.util.List;

public class DropSeriesStatement implements Statement {
    private final Sources sources;
    private final Expression condition;

    private DropSeriesStatement(Builder builder) {
        sources = builder.sources;
        condition = builder.condition;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("DROP SERIES");

        if (sources != null) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }

        return buf.toString();
    }

    /**
     * {@code DropSeriesStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<DropSeriesStatement> {
        private Sources sources;
        private Expression condition;

        public Builder() {
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
         * Returns a {@code DropSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code DropSeriesStatement} built with parameters of this {@code DropSeriesStatement.Builder}
         */
        public DropSeriesStatement build() {
            return new DropSeriesStatement(this);
        }
    }
}
