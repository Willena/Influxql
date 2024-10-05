package io.github.willena.influxql.ast.token;

/**
 * InfluxQl well known Operators
 */
public enum Operator {
    // ADD and the following are InfluxQL Operators
    ADD("+"),      // +
    SUB("-"),       // -
    MUL("*"),        // *
    DIV("/"),       // /
    MOD("%"),      // %
    BITWISE_AND("&"), // &
    BITWISE_OR("|"), // |
    BITWISE_XOR("^"), // ^

    AND("AND"), // AND
    OR("OR"),  // OR

    EQ("="),      // =
    NEQ("!="),     // !=
    EQREGEX("=~"),  // =~
    NEQREGEX("!~"), // !~
    LT("<"),      // <
    LTE("<="),     // <=
    GT(">"),     // >
    GTE(">=");     // >=

    private final String value;

    /**
     * Create a new Operator given the operator representation
     *
     * @param value the representation
     */
    Operator(String value) {
        this.value = value;
    }

    /**
     * Get the String representation as InfluxQL for the operator
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        return value;
    }
}
