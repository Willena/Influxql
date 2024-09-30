package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import io.github.willena.influxql.ast.utils.Utils;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class ListLiteral implements Literal<List<String>> {
    private final List<String> values;

    public ListLiteral(final List<String> values) {
        this.values = new StringJoiningList<>(values, Utils::quoteIdentifier);
        ensureDefined("values", values);
    }

    public static ListLiteral of(final List<String> values) {
        return new ListLiteral(values);
    }

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
