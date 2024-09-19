package io.github.willena.influxql.ast;

import java.util.List;

public class Fields extends StringJoiningList<Field> {
    public Fields(List<Field> list) {
        super(list);
    }

    public Fields of(Field... fields) {
        return new Fields(List.of(fields));
    }
}
