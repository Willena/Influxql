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
import io.github.willena.influxql.ast.token.Operator;

import java.util.Set;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class UnaryExpression implements Expression {
    private static final Set<Operator> ALLOWED_OPERATORS = Set.of(Operator.ADD, Operator.SUB, Operator.NOT);
    private final Expression expression;
    private final Operator operator;

    private UnaryExpression(Builder builder) {
        expression = builder.expression;
        operator = builder.operator;
        ensureDefined("expression", expression);
        ensureDefined("op", operator);
        if (!ALLOWED_OPERATORS.contains(operator)) {
            throw new IllegalArgumentException("operator is not allowed: " + operator + "; Allowed operators are: " + ALLOWED_OPERATORS);
        }
    }

    @Override
    public String toString() {
        return operator.toString() + expression.toString();
    }

    public static UnaryExpression plus(Expression expression) {
        return new Builder().withExpression(expression).withOperator(Operator.ADD).build();
    }

    public static UnaryExpression minus(Expression expression) {
        return new Builder().withExpression(expression).withOperator(Operator.SUB).build();
    }

    public static UnaryExpression not(Expression expression) {
        return new Builder().withExpression(expression).withOperator(Operator.NOT).build();
    }

    public static final class Builder {
        private Expression expression;
        private Operator operator;

        public Builder() {
        }

        public Builder withExpression(Expression expression) {
            this.expression = expression;
            return this;
        }

        public Builder withOperator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public UnaryExpression build() {
            return new UnaryExpression(this);
        }
    }
}
