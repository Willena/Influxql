package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.source.Sources;

public class DropSeriesStatement implements Statement {
    private final Sources sources;
    private final Expr condition;

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
    public static final class Builder {
        private Sources sources;
        private Expr condition;

        public Builder() {
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withSources(Sources sources) {
            this.sources = sources;
            return this;
        }

        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder withCondition(Expr condition) {
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
