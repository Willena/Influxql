package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

public class Fields extends StringJoiningList<Field> {
    public Fields(List<Field> list) {
        super(list);
    }

    public Fields() {
        super();
    }

    public static Fields of(Field... fields) {
        return new Fields(List.of(fields));
    }
}
