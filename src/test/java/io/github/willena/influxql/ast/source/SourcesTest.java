package io.github.willena.influxql.ast.source;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourcesTest {
    @Test
    void test() {
        assertEquals(0, new Sources().size());
        assertEquals(0, Sources.of().size());
        assertEquals("\"name\", name2", Sources.of(Measurement.measurement("name"), Measurement.measurement("name2")).toString());
    }
}