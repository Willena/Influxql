package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.source.Target;

import java.util.TimeZone;

public class SelectStatement implements Statement {
    private final Fields fields;
    private final Target target;
    private final Dimensions dimensions;
    private final Sources sources;
    private final Expr condition;
    private final SortFields sortFields;
    private final int limit;
    private final int offset;
    private final int sLimit;
    private final int sOffset;
    private final FillOption fill;
    private final Object fillValue;
    private final TimeZone location;

    private SelectStatement(Builder builder) {
        fields = builder.fields;
        target = builder.target;
        dimensions = builder.dimensions;
        sources = builder.sources;
        condition = builder.condition;
        sortFields = builder.sortFields;
        limit = builder.limit;
        offset = builder.offset;
        sLimit = builder.sLimit;
        sOffset = builder.sOffset;
        fill = builder.fill;
        fillValue = builder.fillValue;
        location = builder.location;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SELECT ");
        buf.append(fields);

        if (target != null) {
            buf.append(" ");
            buf.append(target);
        }
        if (sources != null && !sources.isEmpty()) {
            buf.append(" FROM ");
            buf.append(sources);
        }
        if (condition != null) {
            buf.append(" WHERE ");
            buf.append(condition);
        }
        if (dimensions != null && !dimensions.isEmpty()) {
            buf.append(" GROUP BY ");
            buf.append(dimensions);
        }

        if (fill != null) {
            buf.append(" ").append(fill.getSqlForValue(fillValue));
        }

        if (sortFields != null && !sortFields.isEmpty()) {
            buf.append(" ORDER BY ");
            buf.append(sortFields);
        }
        if (limit > 0) {
            buf.append(String.format(" LIMIT %d", limit));
        }
        if (offset > 0) {
            buf.append(String.format(" OFFSET %d", offset));
        }
        if (sLimit > 0) {
            buf.append(String.format(" SLIMIT %d", sLimit));
        }
        if (sOffset > 0) {
            buf.append(String.format(" SOFFSET %d", sOffset));
        }
        if (location != null) {
            buf.append(String.format("TZ('%s')", location.getID()));
        }
        return buf.toString();
    }

    /**
     * {@code SelectStatement} builder static inner class.
     */
    public static final class Builder {
        private Fields fields;
        private Target target;
        private Dimensions dimensions;
        private Sources sources;
        private Expr condition;
        private SortFields sortFields;
        private int limit;
        private int offset;
        private int sLimit;
        private int sOffset;
        private FillOption fill;
        private Object fillValue;
        private TimeZone location;

        public Builder() {
        }

        /**
         * Sets the {@code fields} and returns a reference to this Builder enabling method chaining.
         *
         * @param fields the {@code fields} to set
         * @return a reference to this Builder
         */
        public Builder withFields(Fields fields) {
            this.fields = fields;
            return this;
        }

        /**
         * Sets the {@code target} and returns a reference to this Builder enabling method chaining.
         *
         * @param target the {@code target} to set
         * @return a reference to this Builder
         */
        public Builder withTarget(Target target) {
            this.target = target;
            return this;
        }

        /**
         * Sets the {@code dimensions} and returns a reference to this Builder enabling method chaining.
         *
         * @param dimensions the {@code dimensions} to set
         * @return a reference to this Builder
         */
        public Builder withDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withSources(Sources sources) {
            this.sources = sources;
            return this;
        }

        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder withCondition(Expr condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder withSortFields(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        /**
         * Sets the {@code limit} and returns a reference to this Builder enabling method chaining.
         *
         * @param limit the {@code limit} to set
         * @return a reference to this Builder
         */
        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the {@code offset} and returns a reference to this Builder enabling method chaining.
         *
         * @param offset the {@code offset} to set
         * @return a reference to this Builder
         */
        public Builder withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Sets the {@code fill} and returns a reference to this Builder enabling method chaining.
         *
         * @param fill the {@code fill} to set
         * @return a reference to this Builder
         */
        public Builder withFill(FillOption fill) {
            this.fill = fill;
            return this;
        }

        /**
         * Sets the {@code fillValue} and returns a reference to this Builder enabling method chaining.
         *
         * @param fillValue the {@code fillValue} to set
         * @return a reference to this Builder
         */
        public Builder withFillValue(Object fillValue) {
            this.fillValue = fillValue;
            return this;
        }

        /**
         * Sets the {@code location} and returns a reference to this Builder enabling method chaining.
         *
         * @param location the {@code location} to set
         * @return a reference to this Builder
         */
        public Builder withLocation(TimeZone location) {
            this.location = location;
            return this;
        }

        public Builder withSLimit(int sLimit) {
            this.sLimit = sLimit;
            return this;
        }

        public Builder withSOffset(int sOffset) {
            this.sOffset = sOffset;
            return this;
        }

        /**
         * Returns a {@code SelectStatement} built from the parameters previously set.
         *
         * @return a {@code SelectStatement} built with parameters of this {@code SelectStatement.Builder}
         */
        public SelectStatement build() {
            return new SelectStatement(this);
        }
    }
}
