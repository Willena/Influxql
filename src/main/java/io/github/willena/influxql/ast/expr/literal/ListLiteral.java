package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import io.github.willena.influxql.ast.utils.Utils;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * List of string value literal
 */
public class ListLiteral implements Literal<List<String>> {
    private final List<String> values;

    /**
     * New list literal given an input list of string
     *
     * @param values list
     */
    public ListLiteral(final List<String> values) {
        this.values = new StringJoiningList<>(values, Utils::quoteIdentifier);
        ensureDefined("values", values);
    }

    /**
     * New list literal given an input list of string
     *
     * @param values list
     * @return ListLiteral
     */
    public static ListLiteral of(final List<String> values) {
        return new ListLiteral(values);
    }

    /**
     * New list literal given strings
     *
     * @param values list
     * @return ListLiteral
     */
    public static ListLiteral of(String... values) {
        return of(List.of(values));
    }

    @Override
    public List<String> getValue() {
        return values;
    }

    @Override
    public String toString() {
        return "(" + values.toString() + ")";
    }
}
