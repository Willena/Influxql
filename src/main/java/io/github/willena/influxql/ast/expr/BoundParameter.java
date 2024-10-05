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

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

/**
 * Bound parameter expression
 */
public class BoundParameter implements Expression {
    private final String name;

    /**
     * Create named bound parameter
     *
     * @param name param
     */
    public BoundParameter(String name) {
        this.name = name;
    }

    /**
     * Create named bound parameter
     *
     * @param name param
     * @return the BoundParameter
     */
    public static BoundParameter named(String name) {
        return new BoundParameter(name);
    }

    @Override
    public String toString() {
        return "$" + quoteIdentifier(name);
    }
}
