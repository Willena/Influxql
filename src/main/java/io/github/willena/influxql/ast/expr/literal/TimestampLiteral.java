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
import java.time.Instant;
import java.util.Objects;

/** Time / Timestamp literal */
public class TimestampLiteral implements Literal<Instant> {
    private final Instant value;

    /**
     * Build timestamp literal form instant
     *
     * @param value instant
     */
    public TimestampLiteral(Instant value) {
        this.value = value;
        ensureDefined("value", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimestampLiteral that = (TimestampLiteral) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Build timestamp literal form instant
     *
     * @param instant instant
     * @return a new literal
     */
    public static TimestampLiteral of(Instant instant) {
        return new TimestampLiteral(instant);
    }

    @Override
    public Instant getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "'" + value.toString() + "'";
    }
}
