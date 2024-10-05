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

import io.github.willena.influxql.ast.Expression;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Distinct expression
 */
public class Distinct implements Expression {
    private final String value;

    /**
     * Build using name
     *
     * @param value name
     */
    public Distinct(String value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Build using name
     *
     * @param value name
     * @return Distinct expression
     */
    public static Distinct of(String value) {
        return new Distinct(value);
    }

    @Override
    public String toString() {
        return "DISTINCT " + value;
    }
}
