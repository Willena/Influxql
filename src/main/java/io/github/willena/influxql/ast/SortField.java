package io.github.willena.influxql.ast;

public class SortField {
    private final String name;
    private final boolean ascending;

    private SortField(Builder builder) {
        name = builder.name;
        ascending = builder.ascending;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        if (!name.isEmpty()) {
            buf.append(name);
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
    public static final class Builder {
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
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code ascending} and returns a reference to this Builder enabling method chaining.
         *
         * @param ascending the {@code ascending} to set
         * @return a reference to this Builder
         */
        public Builder withAscending(boolean ascending) {
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
