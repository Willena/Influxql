package io.github.willena.influxql.ast;

import java.util.List;

public class SortFields extends StringJoiningList<SortField> {
    public SortFields(List<SortField> list) {
        super(list);
    }

    public SortFields of(SortField... sortFields) {
        return new SortFields(List.of(sortFields));
    }
}
