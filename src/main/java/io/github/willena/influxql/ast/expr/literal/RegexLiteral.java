package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.util.regex.Pattern;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Regex literal
 */
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

    /**
     * Create regex literal using a  string pattern
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
        return "/" +
                pattern.toString().replaceAll("/", "\\\\/") +
                "/";
    }
}
