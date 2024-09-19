package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class ListLiteral implements Literal<List<String>> {
    private final List<String> values;

    public ListLiteral(final List<String> values) {
        this.values = values;
    }

    @Override
    public List<String> getValue() {
        return values;
    }

    @Override
    public String toString() {
        return "(" +
                values.stream()
                        .map(Utils::QuoteIdent)
                        .collect(Collectors.joining(", ")) +
                ")";
    }
}
