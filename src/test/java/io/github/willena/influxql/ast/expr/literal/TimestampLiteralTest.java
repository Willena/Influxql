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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class TimestampLiteralTest {
    @Test
    void testTimeLiteral() {
        assertEquals(
                Instant.ofEpochMilli(123456789),
                TimestampLiteral.of(Instant.ofEpochMilli(123456789)).getValue());
        assertEquals(
                "'1970-01-02T10:17:36.789Z'",
                TimestampLiteral.of(Instant.ofEpochMilli(123456789)).toString());
        assertThrows(IllegalArgumentException.class, () -> TimestampLiteral.of(null));
    }
}
