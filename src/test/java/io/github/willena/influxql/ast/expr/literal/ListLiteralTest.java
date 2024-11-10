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

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListLiteralTest {
    @Test
    void listTest() {
        assertEquals("(A, B)", ListLiteral.of("A", "B").toString());
        assertEquals(
                List.of(IdentifierlLiteral.of("A"), IdentifierlLiteral.of("B")),
                ListLiteral.of("A", "B").getValue());
        assertEquals(
                List.of(StringLiteral.of("A"), StringLiteral.of("B")),
                ListLiteral.of(List.of(StringLiteral.of("A"), StringLiteral.of("B"))).getValue());
    }

    @Test
    void listOfDuration() {
        assertEquals(
                "(1d, 1h)", ListLiteral.of(Duration.ofDays(1), Duration.ofHours(1)).toString());
    }

    @Test
    void listOfNumber() {
        assertEquals("(1.0, 2.0)", ListLiteral.of(1, 2).toString());
    }

    @Test
    void listOfLiteral() {
        assertEquals(
                "('A', 'B')",
                ListLiteral.of(StringLiteral.of("A"), StringLiteral.of("B")).toString());
    }
}
