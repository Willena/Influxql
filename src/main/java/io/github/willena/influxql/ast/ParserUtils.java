package io.github.willena.influxql.ast;

public final class ParserUtils {
    private ParserUtils() {
    }

    // isDigit returns true if the rune is a digit.
    public static boolean isDigit(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    // isLetter returns true if the rune is a letter.
    public static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    // isIdentChar returns true if the rune can be used in an unquoted identifier.
    public static boolean isIdentChar(char ch) {
        return isLetter(ch) || isDigit(ch) || ch == '_';
    }

    // isIdentFirstChar returns true if the rune can be used as the first char in an unquoted identifer.
    public static boolean isIdentFirstChar(char ch) {
        return isLetter(ch) || ch == '_';
    }
}
