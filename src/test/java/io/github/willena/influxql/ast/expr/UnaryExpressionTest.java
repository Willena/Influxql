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

import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.expr.literal.NumberLiteral;
import io.github.willena.influxql.ast.token.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnaryExpressionTest {
    @Test
    void plusTest() {
        UnaryExpression lit = UnaryExpression.plus(NumberLiteral.of(1));
        assertEquals("+1.0", lit.toString());
    }

    @Test
    void minusTest() {
        UnaryExpression lit = UnaryExpression.minus(IntegerLiteral.of(12));
        assertEquals("-12", lit.toString());
    }

    @Test
    void notTest() {
        UnaryExpression lit = UnaryExpression.not(IntegerLiteral.of(12));
        assertEquals("~12", lit.toString());
    }

    @Test
    void builderTest() {
        assertThrows(IllegalArgumentException.class, () -> new UnaryExpression.Builder().build());
        assertThrows(IllegalArgumentException.class, () -> new UnaryExpression.Builder().withExpression(IntegerLiteral.of(10)).build());
        assertThrows(IllegalArgumentException.class, () -> new UnaryExpression.Builder().withExpression(IntegerLiteral.of(10)).withOperator(Operator.EQ).build());

        assertDoesNotThrow(() -> new UnaryExpression.Builder().withExpression(IntegerLiteral.of(10)).withOperator(Operator.SUB).build());
    }
}