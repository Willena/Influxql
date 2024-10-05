package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;

/**
 * Wildcard expressions
 */
public class Wildcard implements Expression {
    private Wildcard() {
    }

    /**
     * Simple wildcard
     *
     * @return wildcard
     */
    public static Wildcard wildcard() {
        return new Wildcard();
    }

    /**
     * Field only wildcard
     *
     * @return wildcard
     */
    public static Wildcard wildcardFields() {
        return new WildcardField();
    }

    /**
     * Tags only wildcard
     *
     * @return wildcard
     */
    public static Wildcard wildcardTags() {
        return new WildcardTag();
    }

    @Override
    public String toString() {
        return "*";
    }

    /**
     * Field only wildcard
     */
    public static class WildcardField extends Wildcard {
        private WildcardField() {
            super();
        }

        @Override
        public String toString() {
            return super.toString() + "::field";
        }
    }

    /**
     * Tags only wildcard
     */
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
