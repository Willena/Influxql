package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expr;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class Field {
    private final Expr expr;
    private final String alias;

    private Field(Builder builder) {
        expr = builder.expr;
        alias = builder.alias;
    }

    @Override
    public String toString() {
        String str = expr.toString();

        if (alias == null || alias.isBlank()) {
            return str;
        }
        return String.format("%s AS %s", str, quoteIdentifier(alias));
    }

    /**
     * {@code Field} builder static inner class.
     */
    public static final class Builder implements Buildable<Field> {
        private Expr expr;
        private String alias;

        public Builder() {
        }

        /**
         * Sets the {@code expr} and returns a reference to this Builder enabling method chaining.
         *
         * @param expr the {@code expr} to set
         * @return a reference to this Builder
         */
        public Builder withExpr(Expr expr) {
            this.expr = expr;
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
