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

import io.github.willena.influxql.ast.Literal;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.formatDuration;

/**
 * Duration literal
 */
public class DurationLiteral implements Literal<Duration> {
    private final Duration value;

    /**
     * Create from java duration
     *
     * @param value duration
     */
    public DurationLiteral(final Duration value) {
        this.value = value;
        ensureDefined("value", value);
    }

    /**
     * Create from java duration
     *
     * @param value duration
     * @return DurationLiteral
     */
    public static DurationLiteral of(Duration value) {
        return new DurationLiteral(value);
    }

    @Override
    public Duration getValue() {
        return value;
    }

    @Override
    public String toString() {
        return formatDuration(value);
    }
}
