package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

public class Wildcard implements Expression {
    private Wildcard() {
    }

    public static Wildcard wildcard() {
        return new Wildcard();
    }

    public static Wildcard wildcardFields() {
        return new WildcardField();
    }

    public static Wildcard wildcardTags() {
        return new WildcardTag();
    }

    @Override
    public String toString() {
        return "*";
    }

    public static class WildcardField extends Wildcard {
        private WildcardField() {
            super();
        }

        @Override
        public String toString() {
            return super.toString() + "::field";
        }
    }

    public static class WildcardTag extends Wildcard {
        private WildcardTag() {
            super();
        }

        @Override
        public String toString() {
            return super.toString() + "::tag";
        }
    }
}
