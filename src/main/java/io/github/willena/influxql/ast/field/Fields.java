package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

/**
 * List of fields
 */
public class Fields extends StringJoiningList<Field> {
    /**
     * Create initialized list of field
     *
     * @param list list
     */
    public Fields(List<Field> list) {
        super(list);
    }

    /**
     * Create empty list of fields
     */
    public Fields() {
        super();
    }

    /**
     * Create an initialized list of fields
     *
     * @param fields fields to add
     * @return field list
     */
    public static Fields of(Field... fields) {
        return new Fields(List.of(fields));
    }
}
