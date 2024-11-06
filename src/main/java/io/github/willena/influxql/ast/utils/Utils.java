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

import io.github.willena.influxql.ast.token.Keywords;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.willena.influxql.ast.utils.ParserUtils.isIdentChar;
import static io.github.willena.influxql.ast.utils.ParserUtils.isIdentFirstChar;

/**
 * Utility class for String and conversion utils
 */
public final class Utils {

    private static final Pattern DURATION_PATTERN =
            Pattern.compile("([1-9][0-9]*)(ns|u|µ|ms|s|m|h|d|w)");
    private static final Map<String, ChronoUnit> UNITS_MAP =
            Map.ofEntries(
                    Map.entry("ns", ChronoUnit.NANOS),
                    Map.entry("u", ChronoUnit.MICROS),
                    Map.entry("µ", ChronoUnit.MICROS),
                    Map.entry("ms", ChronoUnit.MILLIS),
                    Map.entry("s", ChronoUnit.SECONDS),
                    Map.entry("m", ChronoUnit.MINUTES),
                    Map.entry("h", ChronoUnit.HOURS),
                    Map.entry("d", ChronoUnit.DAYS),
                    Map.entry("w", ChronoUnit.WEEKS));

    private Utils() {
    }

    /**
     * Escape the given string to be influxql compatible and safe.
     *
     * @param s the string to escape
     * @return the escaped string
     */
    public static String escapeString(String s) {
        return s.replaceAll("\n", "\\\\n").replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
    }

    /**
     * Quote a string escaping it if required to be a safe InfluxQL String
     *
     * @param s the string
     * @return the quoted and escaped string
     */
    public static String quoteString(String s) {
        return "'" + escapeString(s) + "'";
    }

    /**
     * Format a JavaDuration to an InfluxQL duration String. Only integer of the most précise unit
     * available will be generated
     *
     * @param duration the duration
     * @return an InfluxQL duration String
     */
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

    public static Duration parseDuration(String duration) {
        Matcher matcher = DURATION_PATTERN.matcher(duration);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration: " + duration);
        }
        long intValue = Long.parseLong(matcher.group(1));
        ChronoUnit unit =
                Optional.ofNullable(UNITS_MAP.get(matcher.group(2)))
                        .orElseThrow(() -> new UnsupportedOperationException("Unsupported unit"));

        return Duration.of(intValue, unit);
    }

    /**
     * Returns a quoted identifier if required, from multiple bare identifiers.
     *
     * @param segments identifier segments
     * @return a quoted identifier (if required)
     */
    public static String quoteIdentifier(String... segments) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < segments.length; i++) {
            var segment = segments[i];
            var needQuote =
                    identNeedsQuotes(segment)
                            || ((i < segments.length - 1) && !segment.isEmpty())
                            || // not last segment && not ""
                            ((i == 0 || i == segments.length - 1)
                                    && segment.isEmpty()); // the first or last segment and an empty
            // string

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

    /**
     * Ensure the given value is not null and not blank
     *
     * @param name  the value name (used to fill the exception)
     * @param value the current value
     */
    public static void ensureDefined(String name, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must be defined");
        }
    }

    /**
     * Ensure the given value is not null
     *
     * @param name  the value name (used to full the exception)
     * @param value the current value
     */
    public static void ensureDefined(String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must be defined");
        }
    }

    /**
     * Checks if the current identifier required quotes
     *
     * @param ident identifier
     * @return true if quotes are needed
     */
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

    /**
     * Escape char in identifier to make it safe
     *
     * @param ident identifier
     * @return safe escaped identifier
     */
    private static String escapeIdentifierChar(String ident) {
        // 	// Quote Ident replacer.
        //	qiReplacer = strings.NewReplacer("\n", `\n`, `\`, `\\`, `"`, `\"`)
        return ident.replaceAll("\n", "\\\\n")
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\"", "\\\\\"");
    }


}
