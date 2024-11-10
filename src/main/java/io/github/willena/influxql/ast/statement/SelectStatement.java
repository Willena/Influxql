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

package io.github.willena.influxql.ast.statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.Dimensions;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.field.Fields;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.source.Target;
import io.github.willena.influxql.ast.token.FillOption;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

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
    private final Literal<?> fillValue;
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
            buf.append(String.format(" TZ('%s')", location.getID()));
        }
        return buf.toString();
    }

    public Fields getFields() {
        return fields;
    }

    public Target getTarget() {
        return target;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Sources getSources() {
        return sources;
    }

    public Expression getCondition() {
        return condition;
    }

    public SortFields getSortFields() {
        return sortFields;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getsLimit() {
        return sLimit;
    }

    public int getsOffset() {
        return sOffset;
    }

    public FillOption getFill() {
        return fill;
    }

    public Literal<?> getFillValue() {
        return fillValue;
    }

    public TimeZone getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectStatement that = (SelectStatement) o;
        return limit == that.limit
                && offset == that.offset
                && sLimit == that.sLimit
                && sOffset == that.sOffset
                && Objects.equals(fields, that.fields)
                && Objects.equals(target, that.target)
                && Objects.equals(dimensions, that.dimensions)
                && Objects.equals(sources, that.sources)
                && Objects.equals(condition, that.condition)
                && Objects.equals(sortFields, that.sortFields)
                && fill == that.fill
                && Objects.equals(fillValue, that.fillValue)
                && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                fields,
                target,
                dimensions,
                sources,
                condition,
                sortFields,
                limit,
                offset,
                sLimit,
                sOffset,
                fill,
                fillValue,
                location);
    }

    public static SelectStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::select_stmt, (c, a) -> a.visitSelect_stmt(c), sql);
    }

    /** {@code SelectStatement} builder static inner class. */
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
        private Literal<?> fillValue;
        private TimeZone location;

        public Builder() {}

        /**
         * Sets the {@code fields} and returns a reference to this Builder enabling method chaining.
         *
         * @param fields the {@code fields} to set
         * @return a reference to this Builder
         */
        public Builder select(Fields fields) {
            this.fields = fields;
            return this;
        }

        public Builder select(Field field, Field... fields) {
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
        public Builder into(Target target) {
            this.target = target;
            return this;
        }

        /**
         * Sets the {@code dimensions} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param dimensions the {@code dimensions} to set
         * @return a reference to this Builder
         */
        public Builder groupBy(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder groupBy(Dimension dimension, Dimension... dimensions) {
            if (this.dimensions == null) {
                this.dimensions = new Dimensions(Collections.emptyList());
            }
            this.dimensions.add(dimension);
            this.dimensions.addAll(List.of(dimensions));
            return this;
        }

        /**
         * Sets the {@code sources} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param sources the {@code sources} to set
         * @return a reference to this Builder
         */
        public Builder from(Sources sources) {
            this.sources = sources;
            return this;
        }

        public Builder from(Source source, Source... sources) {
            if (this.sources == null) {
                this.sources = new Sources(Collections.emptyList());
            }
            this.sources.add(source);
            this.sources.addAll(List.of(sources));
            return this;
        }

        /**
         * Sets the {@code condition} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param condition the {@code condition} to set
         * @return a reference to this Builder
         */
        public Builder where(Expression condition) {
            this.condition = condition;
            return this;
        }

        /**
         * Sets the {@code sortFields} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param sortFields the {@code sortFields} to set
         * @return a reference to this Builder
         */
        public Builder orderBy(SortFields sortFields) {
            this.sortFields = sortFields;
            return this;
        }

        public Builder orderBy(SortField sortField, SortField... sortFields) {
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
        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Sets the {@code offset} and returns a reference to this Builder enabling method chaining.
         *
         * @param offset the {@code offset} to set
         * @return a reference to this Builder
         */
        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Sets the {@code fill} and returns a reference to this Builder enabling method chaining.
         *
         * @param fill the {@code fill} to set
         * @return a reference to this Builder
         */
        public Builder fill(FillOption fill) {
            this.fill = fill;
            return this;
        }

        public Builder fill(FillOption fill, Literal<?> value) {
            this.fill = fill;
            this.fillValue(value);
            return this;
        }

        /**
         * Sets the {@code fillValue} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param fillValue the {@code fillValue} to set
         * @return a reference to this Builder
         */
        public Builder fillValue(Literal<?> fillValue) {
            this.fillValue = fillValue;
            return this;
        }

        /**
         * Sets the {@code location} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param location the {@code location} to set
         * @return a reference to this Builder
         */
        public Builder timezone(TimeZone location) {
            this.location = location;
            return this;
        }

        public Builder sLimit(int sLimit) {
            this.sLimit = sLimit;
            return this;
        }

        public Builder sOffset(int sOffset) {
            this.sOffset = sOffset;
            return this;
        }

        /**
         * Returns a {@code SelectStatement} built from the parameters previously set.
         *
         * @return a {@code SelectStatement} built with parameters of this {@code
         *     SelectStatement.Builder}
         */
        public SelectStatement build() {
            return new SelectStatement(this);
        }
    }
}
