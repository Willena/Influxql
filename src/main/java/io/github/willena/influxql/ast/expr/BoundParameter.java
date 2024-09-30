package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class BoundParameter implements Expression {
    private final String name;

    public BoundParameter(String name) {
        this.name = name;
    }

    public static BoundParameter named(String name) {
        return new BoundParameter(name);
    }

    @Override
    public String toString() {
        return "$" + quoteIdentifier(name);
    }
}
