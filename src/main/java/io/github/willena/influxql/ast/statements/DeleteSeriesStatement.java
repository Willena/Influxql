
package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteSeriesStatement implements Statement {
    private final List<Source> sources;
    private final Expr condition;

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
            buf.append(sources.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        return buf.toString();
    }

    /**
     * {@code DeleteSeriesStatement} builder static inner class.
     */
    public static final class Builder {
        private List<Source> sources;
        private Expr condition;

        public Builder() {
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withSources(List<Source> sources) {
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
         * Returns a {@code DeleteSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code DeleteSeriesStatement} built with parameters of this {@code DeleteSeriesStatement.Builder}
         */
        public DeleteSeriesStatement build() {
            return new DeleteSeriesStatement(this);
        }
    }
}
