package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class ExplainStatement implements Statement {
    private final SelectStatement statement;
    private final boolean analyze;
    private final boolean verbose;

    private ExplainStatement(Builder builder) {
        statement = builder.statement;
        analyze = builder.analyze;
        verbose = builder.verbose;
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

    /**
     * {@code ExplainStatement} builder static inner class.
     */
    public static final class Builder {
        private SelectStatement statement;
        private boolean analyze;
        private boolean verbose;

        public Builder() {
        }

        /**
         * Sets the {@code statement} and returns a reference to this Builder enabling method chaining.
         *
         * @param statement the {@code statement} to set
         * @return a reference to this Builder
         */
        public Builder withStatement(SelectStatement statement) {
            this.statement = statement;
            return this;
        }

        /**
         * Sets the {@code analyze} and returns a reference to this Builder enabling method chaining.
         *
         * @param analyze the {@code analyze} to set
         * @return a reference to this Builder
         */
        public Builder withAnalyze(boolean analyze) {
            this.analyze = analyze;
            return this;
        }

        /**
         * Sets the {@code verbose} and returns a reference to this Builder enabling method chaining.
         *
         * @param verbose the {@code verbose} to set
         * @return a reference to this Builder
         */
        public Builder withVerbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        /**
         * Returns a {@code ExplainStatement} built from the parameters previously set.
         *
         * @return a {@code ExplainStatement} built with parameters of this {@code ExplainStatement.Builder}
         */
        public ExplainStatement build() {
            return new ExplainStatement(this);
        }
    }
}
