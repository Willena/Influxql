/*
 * InfluxQL Java package
 * Copyright 2024 Guillaume VILLENA also known as "Willena" on GitHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
