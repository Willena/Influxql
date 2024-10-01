package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class VarRef implements Expression {
    private final String value;
    private final DataType type;

    public VarRef(String value, DataType type) {
        this.value = value;
        this.type = type;
        ensureDefined("value", value);
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
        buf.append(quoteIdentifier(value));
        if (type != null && type != DataType.UNKNOWN) {
            buf.append("::");
            buf.append(type);
        }
        return buf.toString();
    }
}
