package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Node;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

/**
 * A Field used to sort the data.
 */
public class SortField implements Node {
    private final String name;
    private final boolean ascending;

    private SortField(Builder builder) {
        name = builder.name;
        ascending = builder.ascending;
        ensureDefined("name", name);
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
        if (name != null && !name.isEmpty()) {
            // TODO: not sure about that
            // buf.append(name);
            buf.append(quoteIdentifier(name));
            buf.append(" ");
        }
        if (ascending) {
            buf.append("ASC");
        } else {
            buf.append("DESC");
        }
        return buf.toString();
    }

    /**
     * {@code SortField} builder static inner class.
     */
    public static final class Builder implements Buildable<SortField> {
        private String name;
        private boolean ascending;

        public Builder() {
        }

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
         * Sets the {@code ascending} and returns a reference to this Builder enabling method chaining.
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
