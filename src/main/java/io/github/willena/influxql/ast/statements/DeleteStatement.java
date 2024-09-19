package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;

public class DeleteStatement implements Statement {
    private final Source source;
    private final Expr condition;

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
     */
    public static final class Builder {
        private Source source;
        private Expr condition;

        public Builder() {
        }

        /**
         * Sets the {@code source} and returns a reference to this Builder enabling method chaining.
         *
         * @param source the {@code source} to set
         * @return a reference to this Builder
         */
        public Builder withSource(Source source) {
            this.source = source;
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
         * Returns a {@code DeleteStatement} built from the parameters previously set.
         *
         * @return a {@code DeleteStatement} built with parameters of this {@code DeleteStatement.Builder}
         */
        public DeleteStatement build() {
            return new DeleteStatement(this);
        }
    }
}
