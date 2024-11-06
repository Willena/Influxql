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

/**
 * @deprecated use {@link DeleteSeriesStatement}
 * For some reason, this is included in the Golang package.
 * Implemented it, but it is useless.
 * Many things are invalid given the samples from the documentation and the parser syntax.
 */
@Deprecated(forRemoval = true)
public class DeleteStatement implements Statement {
    private final Source source;
    private final Expression condition;

    private DeleteStatement(Builder builder) {
        source = builder.source;
        condition = builder.condition;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("DELETE FROM ");
        buf.append(source);
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        return buf.toString();
    }

    /**
     * {@code DeleteStatement} builder static inner class.
     *
     * @deprecated use {@link DeleteSeriesStatement.Builder}
     */
    @Deprecated(forRemoval = true)
    public static final class Builder implements Buildable<DeleteStatement> {
        private Source source;
        private Expression condition;

        public Builder() {
        }

        /**
         * Sets the {@code source} and returns a reference to this Builder enabling method chaining.
         *
         * @param source the {@code source} to set
         * @return a reference to this Builder
         */
        public Builder from(Source source) {
            this.source = source;
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
         * Returns a {@code DeleteStatement} built from the parameters previously set.
         *
         * @return a {@code DeleteStatement} built with parameters of this {@code
         * DeleteStatement.Builder}
         */
        public DeleteStatement build() {
            return new DeleteStatement(this);
        }
    }
}
