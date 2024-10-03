
package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.source.Sources;

import java.util.List;

/**
 * This class is deprecated due to the fact that it is like {@link DeleteStatement} and seams unsued in
 * InfluxQL ast. Until further notice the statement will stay in here.
 */
@Deprecated()
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

    /**
     * {@code DeleteSeriesStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<DeleteSeriesStatement> {
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
                this.sources = new Sources(List.of());
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
         * Returns a {@code DeleteSeriesStatement} built from the parameters previously set.
         *
         * @return a {@code DeleteSeriesStatement} built with parameters of this {@code DeleteSeriesStatement.Builder}
         */
        public DeleteSeriesStatement build() {
            return new DeleteSeriesStatement(this);
        }
    }
}
