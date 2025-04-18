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

package io.github.willena.influxql.ast.expr;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Expression;
import java.util.Objects;

/** Parenthesis expression */
public class Parentheses implements Expression {
    private final Expression expression;

    /**
     * Build a new {@link Parentheses} expression
     *
     * @param expression expression to be included
     */
    public Parentheses(Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parentheses that = (Parentheses) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expression);
    }

    /**
     * Build a new {@link Parentheses} expression
     *
     * @param expression expression to be included
     * @return the expression with parenthesis
     */
    public static Parentheses of(Expression expression) {
        return new Parentheses(expression);
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }
}
