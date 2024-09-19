package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expr;

public class Wildcard implements Expr {
    public Wildcard() {
    }

    @Override
    public String toString() {
        return "*";
    }

    public static class WildcardField extends Wildcard {
        public WildcardField() {
            super();
        }

        @Override
        public String toString() {
            return "*::field";
        }
    }

    public static class WildcardTag extends Wildcard {
        public WildcardTag() {
            super();
        }

        @Override
        public String toString() {
            return "*::tag";
        }
    }
}
