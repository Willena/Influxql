package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.field.Fields;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.source.Target;
import io.github.willena.influxql.ast.token.FillOption;

import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class SelectStatement implements Statement {
    private final Fields fields;
    private final Target target;
    private final Dimensions dimensions;
    private final Sources sources;
    private final Expression condition;
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

        ensureDefined("field", fields);
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
    public static final class Builder implements Buildable<SelectStatement> {
        private Fields fields;
        private Target target;
        private Dimensions dimensions;
        private Sources sources;
        private Expression condition;
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
        public Builder withSelect(Fields fields) {
            this.fields = fields;
            return this;
        }

        public Builder withSelect(Field field, Field... fields) {
            if (this.fields == null) {
                this.fields = new Fields();
            }
            this.fields.add(field);
            this.fields.addAll(List.of(fields));
            return this;
        }

        /**
         * Sets the {@code target} and returns a reference to this Builder enabling method chaining.
         *
         * @param target the {@code target} to set
         * @return a reference to this Builder
         */
        public Builder withInto(Target target) {
            this.target = target;
            return this;
        }

        /**
         * Sets the {@code dimensions} and returns a reference to this Builder enabling method chaining.
         *
         * @param dimensions the {@code dimensions} to set
         * @return a reference to this Builder
         */
        public Builder withGroupBy(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder withGroupBy(Dimension dimension, Dimension... dimensions) {
            if (this.dimensions == null) {
                this.dimensions = new Dimensions(Collections.emptyList());
            }
            this.dimensions.add(dimension);
            this.dimensions.addAll(List.of(dimensions));
            return this;
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder withFrom(Sources sources) {
            this.sources = sources;
            return this;
        }

        public Builder withFrom(Source source, Source... sources) {
            if (this.sources == null) {
                this.sources = new Sources(Collections.emptyList());
            }
            this.sources.add(source);
            this.sources.addAll(List.of(sources));
            return this;
        }


        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder withWhere(Expression condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder withOrderBy(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        public Builder withOrderBy(SortField sortField, SortField... sortFields) {
            if (this.sortFields == null) {
                this.sortFields = new SortFields(Collections.emptyList());
            }
            this.sortFields.add(sortField);
            this.sortFields.addAll(List.of(sortFields));
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

        public Builder withFill(FillOption fill, Object value) {
            this.fill = fill;
            this.fillValue = value;
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
        public Builder withTimeZone(TimeZone location) {
            this.location = location;
            return this;
        }

        public Builder withSeriesLimit(int sLimit) {
            this.sLimit = sLimit;
            return this;
        }

        public Builder withSeriesOffset(int sOffset) {
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
