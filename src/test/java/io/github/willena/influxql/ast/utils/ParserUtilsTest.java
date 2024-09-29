package io.github.willena.influxql.ast.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserUtilsTest {

    @Test
    void isDigit() {
        assertTrue(ParserUtils.isDigit('0'));
        assertFalse(ParserUtils.isDigit('d'));
    }

    @Test
    void isLetter() {
        assertTrue(ParserUtils.isLetter('a'));
        assertFalse(ParserUtils.isLetter('6'));
    }

    @Test
    void isIdentChar() {
        assertTrue(ParserUtils.isIdentChar('a'));
        assertTrue(ParserUtils.isIdentChar('6'));
        assertTrue(ParserUtils.isIdentChar('_'));
        assertFalse(ParserUtils.isIdentChar('-'));
    }

    @Test
    void isIdentFirstChar() {
        assertTrue(ParserUtils.isIdentFirstChar('a'));
        assertTrue(ParserUtils.isIdentFirstChar('_'));
        assertFalse(ParserUtils.isIdentFirstChar('5'));
    }
}