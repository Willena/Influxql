package io.github.willena.influxql.ast.token;

public enum FillOption {
    NULL {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(null)";
        }
    },
    NONE {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(none)";
        }
    },
    NUMBER {
        @Override
        public String getSqlForValue(Object value) {
            if (!(value instanceof Number)) {
                throw new IllegalArgumentException("Expected number but got " + value.getClass());
            }

            return "fill(" + value + ")";
        }
    },
    PREVIOUS {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(previous)";
        }
    },
    LINEAR {
        @Override
        public String getSqlForValue(Object value) {
            return "fill(linear)";
        }
    };


    public abstract String getSqlForValue(Object value);
}
