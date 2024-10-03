package io.github.willena.influxql.ast.token;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.expr.literal.NumericLiteral;

public enum FillOption {
    NULL {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(null)";
        }
    },
    NONE {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(none)";
        }
    },
    NUMBER {
        @Override
        public String getSqlForValue(Literal<?> value) {
            if (!(value instanceof NumericLiteral)) {
                throw new IllegalArgumentException("Expected number but got " + value.getClass());
            }

            return "fill(" + value + ")";
        }
    },
    PREVIOUS {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(previous)";
        }
    },
    LINEAR {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(linear)";
        }
    };


    public abstract String getSqlForValue(Literal<?> value);
}
