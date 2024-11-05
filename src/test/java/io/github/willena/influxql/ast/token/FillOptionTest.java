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

import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.expr.literal.NumberLiteral;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillOptionTest {

    @Test
    void notRequireValue_getSqlForValue() {
        assertEquals("fill(null)", FillOption.NULL.getSqlForValue(null));
        assertEquals("fill(none)", FillOption.NONE.getSqlForValue(null));
        assertEquals("fill(linear)", FillOption.LINEAR.getSqlForValue(null));
        assertEquals("fill(previous)", FillOption.PREVIOUS.getSqlForValue(null));
    }

    @Test
    void requireValue_getSqlForValue() {
        assertEquals("fill(10)", FillOption.NUMBER.getSqlForValue(IntegerLiteral.of(10)));
        assertEquals("fill(10.33)", FillOption.NUMBER.getSqlForValue(NumberLiteral.of(10.33)));
        assertThrows(IllegalArgumentException.class, () -> FillOption.NUMBER.getSqlForValue(StringLiteral.of("toto")));
    }
}