package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.util.regex.Pattern;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class RegexLiteral implements Literal<Pattern> {
    private final Pattern pattern;

    public RegexLiteral(Pattern pattern) {
        this.pattern = pattern;
        ensureDefined("pattern", pattern);
    }

    public static RegexLiteral of(String regex) {
        return of(Pattern.compile(regex));
    }

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
