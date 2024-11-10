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

package io.github.willena.influxql.ast.field;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Node;
import java.util.Objects;

/** A Field used to sort the data. */
public class SortField implements Node {
    private final String name;
    private final boolean ascending;

    private SortField(Builder builder) {
        name = builder.name;
        ascending = builder.ascending;
        ensureDefined("name", name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortField sortField = (SortField) o;
        return ascending == sortField.ascending && Objects.equals(name, sortField.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ascending);
    }

    /**
     * Create an ASC based sort field
     *
     * @param name the field name
     * @return a sort field
     */
    public static SortField asc(String name) {
        return new Builder().field(name).ascending(true).build();
    }

    /**
     * Create an DESC based sort field
     *
     * @param name the field name
     * @return a sort field
     */
    public static SortField desc(String name) {
        return new Builder().field(name).ascending(false).build();
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append(quoteIdentifier(name));
        buf.append(" ");

        if (ascending) {
            buf.append("ASC");
        } else {
            buf.append("DESC");
        }
        return buf.toString();
    }

    /** {@code SortField} builder static inner class. */
    public static final class Builder implements Buildable<SortField> {
        private String name;
        private boolean ascending;

        public Builder() {}

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder field(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code ascending} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param ascending the {@code ascending} to set
         * @return a reference to this Builder
         */
        public Builder ascending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        /**
         * Returns a {@code SortField} built from the parameters previously set.
         *
         * @return a {@code SortField} built with parameters of this {@code SortField.Builder}
         */
        public SortField build() {
            return new SortField(this);
        }
    }
}
