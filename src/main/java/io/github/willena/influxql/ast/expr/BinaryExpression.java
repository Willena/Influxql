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
import io.github.willena.influxql.ast.token.Operator;
import java.util.Objects;

/** Binary expression */
public class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator op;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryExpression that = (BinaryExpression) o;
        return Objects.equals(left, that.left)
                && Objects.equals(right, that.right)
                && op == that.op;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, op);
    }

    /**
     * Create a new Binary expression with LHS and RHS and operator
     *
     * @param left left expression
     * @param right right expression
     * @param op operator between
     */
    public BinaryExpression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
        ensureDefined("left", left);
        ensureDefined("right", right);
        ensureDefined("op", op);
    }

    /**
     * Generic helper method for binaryExpression
     *
     * @param left LHS
     * @param right RHS
     * @param op operator
     * @return a binaryExpression
     */
    public static BinaryExpression of(Expression left, Expression right, Operator op) {
        return new BinaryExpression(left, right, op);
    }

    /**
     * Add binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression add(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.ADD);
    }

    /**
     * Sub binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression sub(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.SUB);
    }

    /**
     * Multiplicative binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression mul(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.MUL);
    }

    /**
     * Divide binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression div(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.DIV);
    }

    /**
     * modulus binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression mod(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.MOD);
    }

    /**
     * bit or binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression bitwiseOr(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_OR);
    }

    /**
     * bit and binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression bitwiseAnd(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_AND);
    }

    /**
     * bit xor binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression bitwiseXor(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_XOR);
    }

    /**
     * and binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression and(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.AND);
    }

    /**
     * or expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression or(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.OR);
    }

    /**
     * equals regex binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression eqRegex(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.EQREGEX);
    }

    /**
     * not equals regex binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression neqRegex(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.NEQREGEX);
    }

    /**
     * equal binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression eq(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.EQ);
    }

    /**
     * non equal binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression neq(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.NEQ);
    }

    /**
     * greater than binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression gt(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.GT);
    }

    /**
     * lesser than binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression lt(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.LT);
    }

    /**
     * greater equals binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression gte(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.GTE);
    }

    /**
     * less than equal binary expression
     *
     * @param left LHS
     * @param right RHS
     */
    public static BinaryExpression lte(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.LTE);
    }

    @Override
    public String toString() {
        return left.toString() + " " + op.toString() + " " + right.toString();
    }
}
