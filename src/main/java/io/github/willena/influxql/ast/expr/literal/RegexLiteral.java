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

package io.github.willena.influxql.ast.expr.literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Literal;

import java.util.Objects;
import java.util.regex.Pattern;

/** Regex literal */
public class RegexLiteral implements Literal<Pattern> {
    private final Pattern pattern;

    /**
     * Create regex literal using a pattern
     *
     * @param pattern regex
     */
    public RegexLiteral(Pattern pattern) {
        this.pattern = pattern;
        ensureDefined("pattern", pattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegexLiteral that = (RegexLiteral) o;
        return Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pattern);
    }

    /**
     * Create regex literal using a string pattern
     *
     * @param regex regex
     * @return RegexLiteral
     */
    public static RegexLiteral of(String regex) {
        return of(Pattern.compile(regex));
    }

    /**
     * Create regex literal using a pattern
     *
     * @param compile regex
     * @return RegexLiteral
     */
    public static RegexLiteral of(Pattern compile) {
        return new RegexLiteral(compile);
    }

    @Override
    public Pattern getValue() {
        return pattern;
    }

    @Override
    public String toString() {
        return "/" + pattern.toString().replaceAll("/", "\\\\/") + "/";
    }
}
