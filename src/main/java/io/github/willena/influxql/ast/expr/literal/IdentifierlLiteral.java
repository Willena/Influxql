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
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Literal;
import java.util.Objects;

public class IdentifierlLiteral implements Literal<String> {
    private final String value;

    /**
     * Build string literal
     *
     * @param value string
     */
    public IdentifierlLiteral(String value) {
        ensureDefined("value", value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifierlLiteral that = (IdentifierlLiteral) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Create string literal
     *
     * @param value string
     * @return literal
     */
    public static IdentifierlLiteral of(String value) {
        return new IdentifierlLiteral(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return quoteIdentifier(value);
    }
}
