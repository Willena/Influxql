package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Node;
import io.github.willena.influxql.ast.expr.literal.DurationLiteral;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * A grouping dimensions
 */
public class Dimension implements Node {
    private final Expression expression;

    /**
     * Group by expression
     *
     * @param expression the expression
     */
    public Dimension(final Expression expression) {
        this.expression = expression;
        ensureDefined("expression", expression);
    }

    /**
     * Group by expression
     *
     * @param expression expression
     * @return Dimension
     */
    public static Dimension of(final Expression expression) {
        return new Dimension(expression);
    }

    /**
     * Group by a field
     *
     * @param field field name
     * @return Dimension
     */
    public static Dimension field(final String field) {
        return new Dimension(VarRef.of(field));
    }

    /**
     * Special dimension for time based groups
     * This is equivalent to time(duration)
     *
     * @param duration duration group
     * @return a dimension
     */
    public static Dimension sampledBy(Duration duration) {
        return Dimension.of(new Call.Builder().function("time").withArguments(DurationLiteral.of(duration)).build());
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}
