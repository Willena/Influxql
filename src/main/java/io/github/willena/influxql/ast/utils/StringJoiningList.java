package io.github.willena.influxql.ast.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringJoiningList<T> extends LinkedList<T> {
    private final String delimiter;
    private final Function<T, String> converter;

    public StringJoiningList(List<T> list, String delimiter) {
        this(list, delimiter, Object::toString);
    }

    public StringJoiningList(List<T> list, String delimiter, Function<T, String> converter) {
        super(list);
        this.delimiter = delimiter;
        this.converter = converter;
    }

    public StringJoiningList(List<T> list) {
        this(list, ", ");
    }

    public StringJoiningList(List<T> list, Function<T, String> converter) {
        this(list, ", ", converter);
    }


    public StringJoiningList() {
        this(Collections.emptyList());
    }

    @Override
    public String toString() {
        return stream().map(converter).collect(Collectors.joining(delimiter));
    }
}
