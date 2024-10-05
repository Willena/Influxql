package io.github.willena.influxql.ast.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List that concat using the given delimiter and convertor when calling toString
 * ", " is the default delimiter
 *
 * @param <T> the type in the list
 */
public class StringJoiningList<T> extends LinkedList<T> {
    private final String delimiter;
    private final Function<T, String> converter;

    /**
     * Build a new list using a delimiter
     *
     * @param list      the list
     * @param delimiter delimiter
     */
    public StringJoiningList(List<T> list, String delimiter) {
        this(list, delimiter, Object::toString);
    }

    /**
     * Build a new list using a delimiter, a conversion function
     *
     * @param list      the list
     * @param delimiter the delimiter
     * @param converter the converter
     */
    public StringJoiningList(List<T> list, String delimiter, Function<T, String> converter) {
        super(list);
        this.delimiter = delimiter;
        this.converter = converter;
    }

    /**
     * Build a list using default params: ", " as delimiter, toString as String converter
     *
     * @param list the list
     */
    public StringJoiningList(List<T> list) {
        this(list, ", ");
    }

    /**
     * Build a list using default delimiter and custom converter
     *
     * @param list      the list
     * @param converter the converter
     */
    public StringJoiningList(List<T> list, Function<T, String> converter) {
        this(list, ", ", converter);
    }

    /**
     * Build an empty list
     */
    public StringJoiningList() {
        this(Collections.emptyList());
    }

    /**
     * Converter content of the list as String
     *
     * @return the list content converted using the provided converter and concatenated using the given delimiter.
     */
    @Override
    public String toString() {
        return stream().map(converter).collect(Collectors.joining(delimiter));
    }
}
