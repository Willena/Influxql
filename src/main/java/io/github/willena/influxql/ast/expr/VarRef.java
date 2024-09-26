package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

import static io.github.willena.influxql.ast.utils.Utils.QuoteIdent;

public class VarRef implements Expr {
    private final String value;
    private final DataType type;

    public VarRef(String value, DataType type) {
        this.value = value;
        this.type = type;
    }

    public VarRef(String expr) {
        this(expr, null);
    }

    public static VarRef of(String expr) {
        return new VarRef(expr);
    }

    public static VarRef of(String expr, DataType type) {
        return new VarRef(expr, type);
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
