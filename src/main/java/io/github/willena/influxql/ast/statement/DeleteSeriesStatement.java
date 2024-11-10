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
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.List;
import java.util.Objects;

public class DeleteSeriesStatement implements Statement {
    private final Sources sources;
    private final Expression condition;

    private DeleteSeriesStatement(Builder builder) {
        sources = builder.sources;
        condition = builder.condition;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("DELETE");

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

    public static DeleteSeriesStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::delete_stmt, (c, a) -> a.visitDelete_stmt(c), sql);
    }

    public Sources getSources() {
        return sources;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteSeriesStatement that = (DeleteSeriesStatement) o;
        return Objects.equals(sources, that.sources) && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sources, condition);
    }

    /** {@code DeleteSeriesStatement} builder static inner class. */
    public static final class Builder implements Buildable<DeleteSeriesStatement> {
        private Sources sources;
        private Expression condition;

        public Builder() {}

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
                this.sources = new Sources(List.of());
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
         * Returns a {@code DeleteSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code DeleteSeriesStatement} built with parameters of this {@code
         *     DeleteSeriesStatement.Builder}
         */
        public DeleteSeriesStatement build() {
            return new DeleteSeriesStatement(this);
        }
    }
}
