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

package io.github.willena.influxql.ast.source;

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Source;
import java.util.Objects;
import java.util.regex.Pattern;

/** An influxQL measurement in FROM clauses */
public class Measurement implements Source {
    private final String database;
    private final String retentionPolicy;
    private final String name;
    private final Pattern regex;
    private final String systemIterator;

    private Measurement(Builder builder) {
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;
        name = builder.name;
        regex = builder.regex;
        systemIterator = builder.systemIterator;

        if ((name == null || name.isBlank())
                && (regex == null || regex.pattern().isBlank())
                && (systemIterator == null || systemIterator.isBlank())) {
            throw new IllegalArgumentException("name, regex, systemIterator are required");
        }

        if ((name != null && systemIterator != null)
                || (regex != null && name != null)
                || (regex != null && systemIterator != null)) {
            throw new IllegalArgumentException(
                    "name, regex, systemIterator are mutually exclusive");
        }
    }

    /**
     * Build a measurement given its name
     *
     * @param name name
     * @return the {@link Measurement}
     */
    public static Measurement measurement(String name) {
        return new Builder().withName(name).build();
    }

    /**
     * Build a measurement given a regex pattern
     *
     * @param pattern the regex pattern
     * @return the {@link Measurement}
     */
    public static Measurement measurements(Pattern pattern) {
        return new Builder().withRegex(pattern).build();
    }

    /**
     * Build a measurement given a regex pattern
     *
     * @param pattern the regex pattern
     * @return the {@link Measurement}
     */
    public static Measurement measurements(String pattern) {
        return new Builder().withRegex(Pattern.compile(pattern)).build();
    }

    /**
     * Get name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the regex
     *
     * @return the regex
     */
    public Pattern getRegex() {
        return regex;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        if (database != null && !database.isBlank()) {
            buf.append(quoteIdentifier(database));
            buf.append(".");
        }

        if (retentionPolicy != null && !retentionPolicy.isBlank()) {
            buf.append(quoteIdentifier(retentionPolicy));
        }

        if ((database != null && !database.isBlank())
                || (retentionPolicy != null && !retentionPolicy.isBlank())) {
            buf.append(".");
        }

        if ((name != null && !name.isBlank())
                && (systemIterator == null || systemIterator.isBlank())) {
            buf.append(quoteIdentifier(name));
        } else if (systemIterator != null && !systemIterator.isBlank()) {
            buf.append(quoteIdentifier(systemIterator));
        } else if (regex != null) {
            buf.append("/");
            buf.append(regex.toString().replaceAll("/", "\\\\/"));
            buf.append("/");
        }

        return buf.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(database, that.database)
                && Objects.equals(retentionPolicy, that.retentionPolicy)
                && Objects.equals(name, that.name)
                && Objects.equals(regex, that.regex)
                && Objects.equals(systemIterator, that.systemIterator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, retentionPolicy, name, regex, systemIterator);
    }

    /** {@code Measurement} builder static inner class. */
    public static final class Builder implements Buildable<Measurement> {
        private String database;
        private String retentionPolicy;
        private String name;
        private Pattern regex;
        private String systemIterator;

        /** Create new Builder */
        public Builder() {}

        /**
         * Sets the {@code database} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param database the {@code database} to set
         * @return a reference to this Builder
         */
        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code retentionPolicy} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param retentionPolicy the {@code retentionPolicy} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicy(String retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code regex} and returns a reference to this Builder enabling method chaining.
         *
         * @param regex the {@code regex} to set
         * @return a reference to this Builder
         */
        public Builder withRegex(Pattern regex) {
            this.regex = regex;
            return this;
        }

        /**
         * Sets the system iterator
         *
         * @param systemIterator it
         * @return the builder
         */
        public Builder withSystemIterator(String systemIterator) {
            this.systemIterator = systemIterator;
            return this;
        }

        /**
         * Returns a {@code Measurement} built from the parameters previously set.
         *
         * @return a {@code Measurement} built with parameters of this {@code Measurement.Builder}
         */
        public Measurement build() {
            return new Measurement(this);
        }
    }
}
