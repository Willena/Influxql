package io.github.willena.influxql.ast.expr;

public enum DataType {
    Unknown("unknown"),
    // Float means the data type is a float.
    Float("float"),
    // Integer means the data type is an integer.
    Integer("integer"),
    // String means the data type is a string of text.
    String("string"),
    // Boolean means the data type is a boolean.
    Boolean("boolean"),
    // Time means the data type is a time.
    Time("time"),
    // Duration means the data type is a duration of time.
    Duration("duration"),
    // Tag means the data type is a tag.
    Tag("tag"),
    // AnyField means the data type is any field.
    AnyField("field"),
    // Unsigned means the data type is an unsigned integer.
    Unsigned("unsigned");

    private final String value;

    DataType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
