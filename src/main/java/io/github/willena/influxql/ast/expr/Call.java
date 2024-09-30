package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

public class Call implements Expression {
    private final String name;
    private final StringJoiningList<Expression> args;

    public Call(String name, List<Expression> args) {
        this.name = name;
        this.args = new StringJoiningList<>(args);
    }

    @Override
    public String toString() {
        return name + "(" + args + ")";
    }
}
