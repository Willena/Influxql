package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Parenthesis expression
 */
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
