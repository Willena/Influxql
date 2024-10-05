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
