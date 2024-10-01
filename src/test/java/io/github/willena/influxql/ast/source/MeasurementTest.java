package io.github.willena.influxql.ast.source;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeasurementTest {
    @Test
    void testMeasurement() {
        assertEquals("Meas", Measurement.measurement("Meas").toString());
        assertEquals("/.*/", Measurement.measurements(Pattern.compile(".*")).toString());
    }

    @Test
    void testBuilder() {
        assertEquals("db.rp.Meas", new Measurement.Builder()
                .withName("Meas")
                .withRetentionPolicy("rp")
                .withDatabase("db")
                .build().toString());
        assertEquals("db.rp.it", new Measurement.Builder()
                .withSystemIterator("it")
                .withRetentionPolicy("rp")
                .withDatabase("db")
                .build().toString());
        assertEquals("db.rp./.*/", new Measurement.Builder()
                .withRegex(Pattern.compile(".*"))
                .withRetentionPolicy("rp")
                .withDatabase("db")
                .build().toString());
    }

    @Test
    void testError() {
        Pattern pattern = Pattern.compile("");
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurements(pattern));
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurements(null));
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurement(""));
        assertThrows(IllegalArgumentException.class, () -> Measurement.measurement(null));
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder().build());
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder()
                .withName("Meas")
                .withSystemIterator("dd")
                .build());
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder()
                .withName("Meas")
                .withRegex(pattern)
                .build());
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder()
                .withName("Meas")
                .withRegex(pattern)
                .withSystemIterator("jkj")
                .build());
        assertThrows(IllegalArgumentException.class, () -> new Measurement.Builder()
                .withRegex(pattern)
                .withSystemIterator("jkj")
                .build());
    }
}