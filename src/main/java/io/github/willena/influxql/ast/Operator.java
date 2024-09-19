package io.github.willena.influxql.ast;

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

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
