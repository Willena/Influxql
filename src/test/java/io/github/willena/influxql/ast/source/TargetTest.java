package io.github.willena.influxql.ast.source;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetTest {
    @Test
    void test() {
        assertEquals("INTO /.*/:MEASUREMENT", Target.of(Measurement.measurements(Pattern.compile(".*"))).toString());
        assertEquals("INTO \"name\"", Target.of(Measurement.measurement("name")).toString());
    }
}