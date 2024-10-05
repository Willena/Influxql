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

package io.github.willena.influxql.ast.expr.literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Literal;

/** Floating number literal */
public class NumberLiteral implements Literal<Double>, NumericLiteral {
    private final Double value;

    /**
     * Create a new number literal
     *
     * @param value double value
     */
    public NumberLiteral(Double value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Create numberLiteral from number
     *
     * @param value number
     * @return NumberLiteral
     */
    public static NumberLiteral of(Number value) {
        ensureDefined("value", value);
        return new NumberLiteral(value.doubleValue());
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value.isNaN()) {
            return "NaN";
        }

        if (value.isInfinite()) {
            return value == Double.POSITIVE_INFINITY ? "+Inf" : "-Inf";
        }

        return value.toString();
    }
}
