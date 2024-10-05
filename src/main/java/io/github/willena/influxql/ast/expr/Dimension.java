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

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Node;
import io.github.willena.influxql.ast.expr.literal.DurationLiteral;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * A grouping dimensions
 */
public class Dimension implements Node {
    private final Expression expression;

    /**
     * Group by expression
     *
     * @param expression the expression
     */
    public Dimension(final Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    /**
     * Group by expression
     *
     * @param expression expression
     * @return Dimension
     */
    public static Dimension of(final Expression expression) {
        return new Dimension(expression);
    }

    /**
     * Group by a field
     *
     * @param field field name
     * @return Dimension
     */
    public static Dimension field(final String field) {
        return new Dimension(VarRef.of(field));
    }

    /**
     * Special dimension for time based groups
     * This is equivalent to time(duration)
     *
     * @param duration duration group
     * @return a dimension
     */
    public static Dimension sampledBy(Duration duration) {
        return Dimension.of(new Call.Builder().function("time").withArguments(DurationLiteral.of(duration)).build());
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
