package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.token.Operator;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator op;

    public BinaryExpression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
        ensureDefined("left", left);
        ensureDefined("right", right);
        ensureDefined("op", op);
    }

    public static BinaryExpression of(Expression left, Expression right, Operator op) {
        return new BinaryExpression(left, right, op);
    }

    public static BinaryExpression add(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.ADD);
    }

    public static BinaryExpression sub(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.SUB);
    }

    public static BinaryExpression mul(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.MUL);
    }

    public static BinaryExpression div(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.DIV);
    }

    public static BinaryExpression mod(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.MOD);
    }

    public static BinaryExpression bitwiseOr(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_OR);
    }

    public static BinaryExpression bitwiseAnd(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_AND);
    }

    public static BinaryExpression bitwiseXor(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.BITWISE_XOR);
    }

    public static BinaryExpression and(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.AND);
    }

    public static BinaryExpression or(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.OR);
    }

    public static BinaryExpression eqRegex(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.EQREGEX);
    }

    public static BinaryExpression neqRegex(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.NEQREGEX);
    }

    public static BinaryExpression eq(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.EQ);
    }

    public static BinaryExpression neq(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.NEQ);
    }

    public static BinaryExpression gt(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.GT);
    }

    public static BinaryExpression lt(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.LT);
    }

    public static BinaryExpression gte(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.GTE);
    }

    public static BinaryExpression lte(Expression left, Expression right) {
        return new BinaryExpression(left, right, Operator.LTE);
    }

    @Override
    public String toString() {
        return left.toString() + " " + op.toString() + " " + right.toString();
    }
}

