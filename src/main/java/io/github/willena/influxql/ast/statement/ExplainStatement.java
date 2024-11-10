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

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.Objects;
import java.util.function.Function;

public class ExplainStatement implements Statement {
    private final SelectStatement statement;
    private final boolean analyze;
    private final boolean verbose;

    private ExplainStatement(Builder builder) {
        statement = builder.statement;
        analyze = builder.analyze;
        verbose = builder.verbose;
        ensureDefined("statement", statement);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("EXPLAIN ");
        if (analyze) {
            buf.append("ANALYZE ");
        }
        if (verbose) {
            buf.append("VERBOSE ");
        }
        buf.append(statement);
        return buf.toString();
    }

    public SelectStatement getStatement() {
        return statement;
    }

    public boolean isAnalyze() {
        return analyze;
    }

    public boolean isVerbose() {
        return verbose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplainStatement that = (ExplainStatement) o;
        return analyze == that.analyze
                && verbose == that.verbose
                && Objects.equals(statement, that.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, analyze, verbose);
    }

    public static ExplainStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::explain_stmt, (c, a) -> a.visitExplain_stmt(c), sql);
    }

    /** {@code ExplainStatement} builder static inner class. */
    public static final class Builder implements Buildable<ExplainStatement> {
        private SelectStatement statement;
        private boolean analyze;
        private boolean verbose;

        public Builder() {}

        /**
         * Sets the {@code statement} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param statement the {@code statement} to set
         * @return a reference to this Builder
         */
        public Builder select(SelectStatement statement) {
            this.statement = statement;
            return this;
        }

        public Builder select(Function<SelectStatement.Builder, SelectStatement.Builder> builder) {
            return select(builder.apply(new SelectStatement.Builder()).build());
        }

        /**
         * Sets the {@code analyze} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param analyze the {@code analyze} to set
         * @return a reference to this Builder
         */
        public Builder analyse(boolean analyze) {
            this.analyze = analyze;
            return this;
        }

        public Builder analyse() {
            return analyse(true);
        }

        /**
         * Sets the {@code verbose} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param verbose the {@code verbose} to set
         * @return a reference to this Builder
         */
        public Builder verbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public Builder verbose() {
            return verbose(true);
        }

        /**
         * Returns a {@code ExplainStatement} built from the parameters previously set.
         *
         * @return a {@code ExplainStatement} built with parameters of this {@code
         *     ExplainStatement.Builder}
         */
        public ExplainStatement build() {
            return new ExplainStatement(this);
        }
    }
}
