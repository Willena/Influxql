package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;
import io.github.willena.influxql.ast.StringJoiningList;

import java.util.List;

public class Call implements Expr {
    private final String name;
    private final StringJoiningList<Expr> args;

    public Call(String name, List<Expr> args) {
        this.name = name;
        this.args = new StringJoiningList<>(args);
    }

    @Override
    public String toString() {
        return name + "(" + args.toString() + ")";
    }
}
