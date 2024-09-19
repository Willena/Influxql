package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.StringJoiningList;

import java.util.List;

public class Dimensions extends StringJoiningList<Dimension> {
    public Dimensions(List<Dimension> list) {
        super(list);
    }
}
