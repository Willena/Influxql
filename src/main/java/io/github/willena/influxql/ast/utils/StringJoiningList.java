package io.github.willena.influxql.ast.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringJoiningList<T> extends LinkedList<T> {
    private final String delimiter;

    public StringJoiningList(List<T> list, String delimiter) {
        super(list);
        this.delimiter = delimiter;
    }

    public StringJoiningList(List<T> list) {
        this(list, ", ");
    }

    public StringJoiningList() {
        this(Collections.emptyList());
    }

    @Override
    public String toString() {
        return stream().map(Objects::toString).collect(Collectors.joining(delimiter));
    }
}
