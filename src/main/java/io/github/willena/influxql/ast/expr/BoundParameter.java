package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

/**
 * Bound parameter expression
 */
public class BoundParameter implements Expression {
    private final String name;

    /**
     * Create named bound parameter
     *
     * @param name param
     */
    public BoundParameter(String name) {
        this.name = name;
    }

    /**
     * Create named bound parameter
     *
     * @param name param
     * @return the BoundParameter
     */
    public static BoundParameter named(String name) {
        return new BoundParameter(name);
    }

    @Override
    public String toString() {
        return "$" + quoteIdentifier(name);
    }
}
