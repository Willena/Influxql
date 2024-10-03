package io.github.willena.influxql.ast.utils;

import io.github.willena.influxql.ast.token.Keywords;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.github.willena.influxql.ast.utils.ParserUtils.isIdentChar;
import static io.github.willena.influxql.ast.utils.ParserUtils.isIdentFirstChar;

public final class Utils {

    private Utils() {
    }


    public static String escapeString(String s) {
        return s.replaceAll("\n", "\\\\n")
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("'", "\\\\'");
    }

    // QuoteString returns a quoted string.
    public static String quoteString(String s) {
        return "'" + escapeString(s) + "'";
    }

    public static String formatDuration(Duration duration) {
        long secs = duration.getSeconds();
        long nanos = duration.getNano();

        long asNano = TimeUnit.SECONDS.toNanos(secs) + nanos;

        // if d == 0 {
        //		return "0s"
        if (duration.isZero()) {
            return "0s";
        }

        //	} else if d%(7*24*time.Hour) == 0 {
        //		return fmt.Sprintf("%dw", d/(7*24*time.Hour))
        if (asNano % TimeUnit.DAYS.toNanos(7) == 0) {
            return String.format("%dw", asNano / TimeUnit.DAYS.toNanos(7));
        }
        //	} else if d%(24*time.Hour) == 0 {
        //		return fmt.Sprintf("%dd", d/(24*time.Hour))
        else if (asNano % TimeUnit.DAYS.toNanos(1) == 0) {
            return String.format("%dd", asNano / TimeUnit.DAYS.toNanos(1));
        }
        //	} else if d%time.Hour == 0 {
        //		return fmt.Sprintf("%dh", d/time.Hour)
        else if (asNano % TimeUnit.HOURS.toNanos(1) == 0) {
            return String.format("%dh", asNano / TimeUnit.HOURS.toNanos(1));
        }
        //	} else if d%time.Minute == 0 {
        //		return fmt.Sprintf("%dm", d/time.Minute)
        else if (asNano % TimeUnit.MINUTES.toNanos(1) == 0) {
            return String.format("%dm", asNano / TimeUnit.MINUTES.toNanos(1));
        }
        //	} else if d%time.Second == 0 {
        //		return fmt.Sprintf("%ds", d/time.Second)
        else if (asNano % TimeUnit.SECONDS.toNanos(1) == 0) {
            return String.format("%ds", asNano / TimeUnit.SECONDS.toNanos(1));
        }
        //	} else if d%time.Millisecond == 0 {
        //		return fmt.Sprintf("%dms", d/time.Millisecond)
        else if (asNano % TimeUnit.MILLISECONDS.toNanos(1) == 0) {
            return String.format("%dms", asNano / TimeUnit.MILLISECONDS.toNanos(1));
        }

        //	} else if d%time.Microsecond == 0 {
        //		// Although we accept both "u" and "µ" when reading microsecond durations,
        //		// we output with "u", which can be represented in 1 byte,
        //		// instead of "µ", which requires 2 bytes.
        //		return fmt.Sprintf("%du", d/time.Microsecond)
        //	}
        else if (asNano % TimeUnit.MICROSECONDS.toNanos(1) == 0) {
            return String.format("%du", asNano / TimeUnit.MICROSECONDS.toNanos(1));
        }
        //	return fmt.Sprintf("%dns", d)
        else {
            return String.format("%dns", asNano);
        }
    }


    // QuoteIdent returns a quoted identifier from multiple bare identifiers.
    public static String quoteIdentifier(String... segments) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < segments.length; i++) {
            var segment = segments[i];
            var needQuote = identNeedsQuotes(segment) ||
                    ((i < segments.length - 1) && !segment.isEmpty()) || // not last segment && not ""
                    ((i == 0 || i == segments.length - 1) && segment.isEmpty()); // the first or last segment and an empty string

            if (needQuote) {
                buf.append('"');
            }

            buf.append(escapeIdentifierChar(segment));

            if (needQuote) {
                buf.append('"');
            }

            if (i < segments.length - 1) {
                buf.append('.');
            }
        }

        return buf.toString();
    }


    public static void ensureDefined(String name, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must be defined");
        }
    }

    public static void ensureDefined(String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must be defined");
        }
    }

    // IdentNeedsQuotes returns true if the ident string given would require quotes.
    private static boolean identNeedsQuotes(String ident) {
        // check if this identifier is a keyword
        if (Keywords.hasValue(ident)) {
            return true;
        }
        for (int i = 0; i < ident.length(); i++) {
            if (i == 0 && !isIdentFirstChar(ident.charAt(i))) {
                return true;
            } else if (i > 0 && !isIdentChar(ident.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static String escapeIdentifierChar(String ident) {
        // 	// Quote Ident replacer.
        //	qiReplacer = strings.NewReplacer("\n", `\n`, `\`, `\\`, `"`, `\"`)
        return ident.replaceAll("\n", "\\\\n")
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\"", "\\\\\"");

    }
}
