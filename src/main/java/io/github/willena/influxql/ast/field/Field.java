package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class Field {
    private final Expression expression;
    private final String alias;

    private Field(Builder builder) {
        expression = builder.expression;
        alias = builder.alias;
    }

    @Override
    public String toString() {
        String str = expression.toString();

        if (alias == null || alias.isBlank()) {
            return str;
        }
        return String.format("%s AS %s", str, quoteIdentifier(alias));
    }

    /**
     * {@code Field} builder static inner class.
     */
    public static final class Builder implements Buildable<Field> {
        private Expression expression;
        private String alias;

        public Builder() {
        }

        /**
         * Sets the {@code expr} and returns a reference to this Builder enabling method chaining.
         *
         * @param expression the {@code expr} to set
         * @return a reference to this Builder
         */
        public Builder withExpr(Expression expression) {
            this.expression = expression;
            return this;
        }

        /**
         * Sets the {@code alias} and returns a reference to this Builder enabling method chaining.
         *
         * @param alias the {@code alias} to set
         * @return a reference to this Builder
         */
        public Builder withAlias(String alias) {
            this.alias = alias;
            return this;
        }

        /**
         * Returns a {@code Field} built from the parameters previously set.
         *
         * @return a {@code Field} built with parameters of this {@code Field.Builder}
         */
        public Field build() {
            return new Field(this);
        }
    }
}
