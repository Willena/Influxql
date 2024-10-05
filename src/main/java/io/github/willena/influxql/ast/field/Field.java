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

package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Node;
import io.github.willena.influxql.ast.expr.DataType;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.Wildcard;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

/**
 * Basic selection Field
 */
public class Field implements Node {
    private final Expression expression;
    private final String alias;

    private Field(Builder builder) {
        expression = builder.expression;
        alias = builder.alias;
        ensureDefined("expression", expression);
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
     * Build a field by name
     *
     * @param name field name
     * @return a field
     */
    public static Field field(String name) {
        return field(name, null);
    }

    /**
     * Build  a field with name and datatype
     *
     * @param name name
     * @param type type
     * @return the Field
     */
    public static Field field(String name, DataType type) {
        return of(VarRef.of(name, type));
    }

    /**
     * Create a wildcard field
     *
     * @return field
     */
    public static Field wildcard() {
        return of(Wildcard.wildcard());
    }

    /**
     * Create a field that wildcard all field but not tags
     *
     * @return a field
     */
    public static Field wildcardFields() {
        return of(Wildcard.wildcardFields());
    }

    /**
     * Create a field that wildcard all tags but not field
     *
     * @return a field
     */
    public static Field wildcardTags() {
        return of(Wildcard.wildcardTags());
    }

    /**
     * Build a generic field based on an expression
     *
     * @param expression the expresion for the field
     * @return a field
     */
    public static Field of(Expression expression) {
        return new Builder().withExpr(expression).build();
    }

    /**
     * {@code Field} builder static inner class.
     */
    public static final class Builder implements Buildable<Field> {
        private Expression expression;
        private String alias;

        /**
         * Create a new builder of Field
         */
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
