package io.github.willena.influxql.ast.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringJoiningListTest {
    @Test
    void toStringTes() {
        StringJoiningList<String> list = new StringJoiningList<>(List.of("abc", "def", "ghi"));
        assertEquals("abc, def, ghi", list.toString());
    }
}