package io.github.willena.influxql.ast.expr;

/**
 * InfluxQL / InfluxDB known types
 */
public enum DataType {
    UNKNOWN("unknown"),
    // Float means the data type is a float.
    FLOAT("float"),
    // Integer means the data type is an integer.
    INTEGER("integer"),
    // String means the data type is a string of text.
    STRING("string"),
    // Boolean means the data type is a boolean.
    BOOLEAN("boolean"),
    // Time means the data type is a time.
    TIME("time"),
    // Duration means the data type is a duration of time.
    DURATION("duration"),
    // Tag means the data type is a tag.
    TAG("tag"),
    // AnyField means the data type is any field.
    ANY_FIELD("field"),
    // Unsigned means the data type is an unsigned integer.
    UNSIGNED("unsigned");

    private final String value;

    /**
     * Create type from its influxQl representation
     *
     * @param value representation
     */
    DataType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
