package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class VarRef implements Expr {
    private final String value;
    private final DataType type;

    public VarRef(String value, DataType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append(QuoteIdent(value));
        if (type != null && type != DataType.Unknown) {
            buf.append("::");
            buf.append(type);
        }
        return buf.toString();
    }
}
