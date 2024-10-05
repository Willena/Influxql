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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberLiteralTest {
    @Test
    void numberLiteral() {
        assertEquals(11.5, NumberLiteral.of(11.5).getValue());
        assertEquals(11.0, NumberLiteral.of(11).getValue());
        assertEquals("11.0", NumberLiteral.of(11).toString());
        assertEquals("11.5", NumberLiteral.of(11.5).toString());
        assertEquals("NaN", NumberLiteral.of(Double.NaN).toString());
        assertEquals("-Inf", NumberLiteral.of(Double.NEGATIVE_INFINITY).toString());
        assertEquals("+Inf", NumberLiteral.of(Double.POSITIVE_INFINITY).toString());
        assertThrows(IllegalArgumentException.class, () -> NumberLiteral.of(null));
    }
}