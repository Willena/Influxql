package io.github.willena.influxql.ast.token;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.expr.literal.NumericLiteral;

/**
 * Fill options
 */
public enum FillOption {
    /**
     * Null fil option
     */
    NULL {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(null)";
        }
    },
    /**
     * None fill option
     */
    NONE {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(none)";
        }
    },
    /**
     * Number fill option
     */
    NUMBER {
        @Override
        public String getSqlForValue(Literal<?> value) {
            if (!(value instanceof NumericLiteral)) {
                throw new IllegalArgumentException("Expected number but got " + value.getClass());
            }

            return "fill(" + value + ")";
        }
    },
    /**
     * Previous fill option
     */
    PREVIOUS {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(previous)";
        }
    },
    /**
     * Linear fill option
     */
    LINEAR {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(linear)";
        }
    };

    /**
     * Get influxql for the current fill option given a literal as parameter
     *
     * @param value the parameter
     * @return the InfluxQL string
     */
    public abstract String getSqlForValue(Literal<?> value);
}
