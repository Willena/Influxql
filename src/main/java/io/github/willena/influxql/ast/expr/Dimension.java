package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.expr.literal.DurationLiteral;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Dimension {
    private final Expression expression;

    public Dimension(final Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    public static Dimension of(final Expression expression) {
        return new Dimension(expression);
    }

    public static Dimension field(final String field) {
        return new Dimension(VarRef.of(field));
    }

    public static Dimension sampledBy(Duration duration) {
        return Dimension.of(new Call.Builder().function("time").withArguments(DurationLiteral.of(duration)).build());
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
