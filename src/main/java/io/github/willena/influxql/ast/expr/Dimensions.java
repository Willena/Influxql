package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

/**
 * Dimensions list
 */
public class Dimensions extends StringJoiningList<Dimension> {
    /**
     * Create using initialized list
     *
     * @param list list
     */
    public Dimensions(List<Dimension> list) {
        super(list);
    }

    /**
     * Create empty list
     */
    public Dimensions() {
        super();
    }

    /**
     * Build list from params
     *
     * @param dimensions dimensions
     * @return a dimensions list
     */
    public static Dimensions of(Dimension... dimensions) {
        return new Dimensions(List.of(dimensions));
    }
}
