package io.github.willena.influxql.ast.utils;

/**
 * Parser utils. Mainly used to detect the kind of string being parsed.
 * When building queries, it is used to find if quoting is required or not.
 */
public final class ParserUtils {
    private ParserUtils() {
    }

    /**
     * Check char is a digit
     *
     * @param ch char to check
     * @return true if digit
     */
    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    /**
     * isLetter returns true if the char is a letter.
     *
     * @param ch char to check
     * @return true if letter
     */
    public static boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    /**
     * isIdentChar returns true if the rune can be used in an unquoted identifier.
     *
     * @param ch char to test
     * @return true if is a valid identifier char
     */
    public static boolean isIdentChar(char ch) {
        return isLetter(ch) || isDigit(ch) || ch == '_';
    }

    /**
     * Similar to isIdentChar but check the first char.
     * returns true if the rune can be used as the first char in an unquoted identifer.
     *
     * @param ch char to test
     * @return true if is a valid identifier first char
     */
    //
    public static boolean isIdentFirstChar(char ch) {
        return isLetter(ch) || ch == '_';
    }
}
