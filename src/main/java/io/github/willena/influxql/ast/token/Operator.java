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
