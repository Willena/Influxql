package io.github.willena.influxql.ast.token;

public enum FillOption {
    NULL_FILL {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(null)";
        }
    },
    NO_FILL {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(none)";
        }
    },
    NUMBER_FILL {
        @Override
        public String getSqlForValue(Object value) {
            if (!(value instanceof Number)) {
                throw new IllegalArgumentException("Expected number but got " + value.getClass());
            }

            return "fill(" + value + ")";
        }
    },
    PREVIOUS_FILL {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(previous)";
        }
    },
    LINEAR_FILL {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(linear)";
        }
    };


    public abstract String getSqlForValue(Object value);
}
