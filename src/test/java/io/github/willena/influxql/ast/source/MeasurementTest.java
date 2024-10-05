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

package io.github.willena.influxql.ast.source;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class MeasurementTest {
    @Test
    void testMeasurement() {
        assertEquals("Meas", Measurement.measurement("Meas").toString());
        assertEquals("/.*/", Measurement.measurements(Pattern.compile(".*")).toString());
    }

    @Test
    void testBuilder() {
        assertEquals(
                "db.rp.Meas",
                new Measurement.Builder()
                        .withName("Meas")
                        .withRetentionPolicy("rp")
                        .withDatabase("db")
                        .build()
                        .toString());
        assertEquals(
                "db.rp.it",
                new Measurement.Builder()
                        .withSystemIterator("it")
                        .withRetentionPolicy("rp")
                        .withDatabase("db")
                        .build()
                        .toString());
        assertEquals(
                "db.rp./.*/",
                new Measurement.Builder()
                        .withRegex(Pattern.compile(".*"))
                        .withRetentionPolicy("rp")
                        .withDatabase("db")
                        .build()
                        .toString());
    }

    @Test
    void testError() {
        Pattern pattern = Pattern.compile("");
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurements(pattern));
        assertThrows(
                IllegalArgumentException.class, () -> Measurement.measurements((Pattern) null));
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurement(""));
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurement(null));
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder().build());
        assertThrows(
                IllegalArgumentException.class,
                () -> new Measurement.Builder().withName("Meas").withSystemIterator("dd").build());
        assertThrows(
                IllegalArgumentException.class,
                () -> new Measurement.Builder().withName("Meas").withRegex(pattern).build());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new Measurement.Builder()
                                .withName("Meas")
                                .withRegex(pattern)
                                .withSystemIterator("jkj")
                                .build());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new Measurement.Builder()
                                .withRegex(pattern)
                                .withSystemIterator("jkj")
                                .build());
    }
}
