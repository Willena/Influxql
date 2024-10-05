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

package io.github.willena.influxql.ast.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void escapeString() {
        assertEquals("\\\\\"", Utils.escapeString("\\\""));
        assertEquals("\\\\n", Utils.escapeString("\n"));
        assertEquals("\\'", Utils.escapeString("'"));
    }

    @Test
    void quoteString() {
        assertEquals("'abc'", Utils.quoteString("abc"));
        assertEquals("'ab\"c'", Utils.quoteString("ab\"c"));
        assertEquals("'ab\\'c'", Utils.quoteString("ab'c"));
    }

    @Test
    void formatDuration() {
        assertEquals("1h", Utils.formatDuration(Duration.ofHours(1)));
    }

    @Test
    void quoteIdentifier() {
        assertEquals("abc", Utils.quoteIdentifier("abc"));
        assertEquals("\"abc\".def", Utils.quoteIdentifier("abc", "def"));
        assertEquals("\"abc\".\"de\\\\nf\"", Utils.quoteIdentifier("abc", "de\nf"));
    }

    @Test
    void ensureDefined() {
        assertDoesNotThrow(() -> Utils.ensureDefined("value", "value"));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", ""));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", null));

        assertDoesNotThrow(() -> Utils.ensureDefined("value", new Object()));
        assertThrows(IllegalArgumentException.class, () -> Utils.ensureDefined("nullValue", null));
    }

    @Test
    void testFormatDuration() {
        assertEquals("0s", Utils.formatDuration(Duration.ofHours(0)));
        assertEquals("1w", Utils.formatDuration(Duration.ofDays(7)));
        assertEquals("1d", Utils.formatDuration(Duration.ofDays(1)));
        assertEquals("1h", Utils.formatDuration(Duration.ofHours(1)));
        assertEquals("1m", Utils.formatDuration(Duration.ofMinutes(1)));
        assertEquals("1s", Utils.formatDuration(Duration.ofSeconds(1)));
        assertEquals("1ms", Utils.formatDuration(Duration.ofMillis(1)));
        assertEquals("1u", Utils.formatDuration(Duration.ofNanos(1000)));
        assertEquals("1ns", Utils.formatDuration(Duration.ofNanos(1)));

        assertEquals("531351350u", Utils.formatDuration(Duration.ofNanos(531351350000L)));
        assertEquals("53135135ms", Utils.formatDuration(Duration.ofNanos(53135135000000L)));
    }
}
