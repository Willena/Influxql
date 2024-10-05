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
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.token.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryExpressionTest {

    @Test
    void of() {
        assertEquals("kkk = '123'", BinaryExpression.of(VarRef.of("kkk"), StringLiteral.of("123"), Operator.EQ).toString());
    }

    @Test
    void add() {
        assertEquals("kk + 1", BinaryExpression.add(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void sub() {
        assertEquals("kk - 1", BinaryExpression.sub(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void mul() {
        assertEquals("kk * 1", BinaryExpression.mul(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void div() {
        assertEquals("kk / 1", BinaryExpression.div(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void mod() {
        assertEquals("kk % 1", BinaryExpression.mod(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void bitwiseOr() {
        assertEquals("kk | 1", BinaryExpression.bitwiseOr(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void bitwiseAnd() {
        assertEquals("kk & 1", BinaryExpression.bitwiseAnd(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }

    @Test
    void bitwiseXor() {
        assertEquals("kk ^ 1", BinaryExpression.bitwiseXor(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }

    @Test
    void and() {
        assertEquals("kk AND 1", BinaryExpression.and(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void or() {
        assertEquals("kk OR 1", BinaryExpression.or(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void eqRegex() {
        assertEquals("kk =~ 'sss'", BinaryExpression.eqRegex(VarRef.of("kk"), StringLiteral.of("sss")).toString());
    }

    @Test
    void neqRegex() {
        assertEquals("kk !~ 'aa'", BinaryExpression.neqRegex(VarRef.of("kk"), StringLiteral.of("aa")).toString());
    }

    @Test
    void eq() {
        assertEquals("kk = 'aa'", BinaryExpression.eq(VarRef.of("kk"), StringLiteral.of("aa")).toString());
    }

    @Test
    void neq() {
        assertEquals("kk != 1", BinaryExpression.neq(VarRef.of("kk"), IntegerLiteral.of(1)).toString());
    }

    @Test
    void gt() {
        assertEquals("kk > 1", BinaryExpression.gt(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }

    @Test
    void lt() {
        assertEquals("kk < 1", BinaryExpression.lt(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }

    @Test
    void gte() {
        assertEquals("kk >= 1", BinaryExpression.gte(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }

    @Test
    void lte() {
        assertEquals("kk <= 1", BinaryExpression.lte(VarRef.of("kk"), IntegerLiteral.of(1)).toString());

    }
}