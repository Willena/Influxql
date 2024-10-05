package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

/**
 * List of sort fields
 */
public class SortFields extends StringJoiningList<SortField> {
    /**
     * Create initialized list
     *
     * @param list the list
     */
    public SortFields(List<SortField> list) {
        super(list);
    }

    /**
     * Create empty {@link SortField} list
     */
    public SortFields() {
        super();
    }

    /**
     * Create initialized sortField list
     *
     * @param sortFields elements to add
     * @return the list
     */
    public static SortFields of(SortField... sortFields) {
        return new SortFields(List.of(sortFields));
    }
}
