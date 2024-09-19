package io.github.willena.influxql.ast.expr.literal;

import io.github.willena.influxql.ast.Literal;

import java.util.regex.Pattern;

public class RegexLiteral implements Literal<Pattern> {
    private final Pattern pattern;

    public RegexLiteral(String regex) {
        this(Pattern.compile(regex));
    }

    public RegexLiteral(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Pattern getValue() {
        return pattern;
    }

    @Override
    public String toString() {
        if (pattern == null) {
            return "";
        }

        return "/" +
                pattern.toString().replaceAll("/", "\\\\/") +
                "/";
    }
}
