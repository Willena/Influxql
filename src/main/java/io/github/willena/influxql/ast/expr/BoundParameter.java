package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class BoundParameter implements Expr {
    private final String name;

    public BoundParameter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "$" + quoteIdentifier(name);
    }
}
