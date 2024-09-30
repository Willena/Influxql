package io.github.willena.influxql.ast.expr.literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class UnsignedLiteral extends IntegerLiteral {

    public UnsignedLiteral(Long value) {
        super(value);
    }

    public static UnsignedLiteral of(Number value) {
        ensureDefined("value", value);
        return new UnsignedLiteral(value.longValue());
    }
}
