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

package io.github.willena.influxql.ast.expr.literal;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import io.github.willena.influxql.ast.utils.Utils;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * List of string value literal
 */
public class ListLiteral implements Literal<List<Literal<?>>> {
    private final List<Literal<?>> values;

    /**
     * New list literal given an input list of string
     *
     * @param values list
     */
    public ListLiteral(final List<Literal<?>> values) {
        this.values = new StringJoiningList<>(values);
        ensureDefined("values", values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListLiteral that = (ListLiteral) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }

    /**
     * New list literal given an input list of string
     *
     * @param values list
     * @return ListLiteral
     */
    public static ListLiteral of(final List<Literal<?>> values) {
        return new ListLiteral(values);
    }

    /**
     * New list literal given strings
     *
     * @param values list
     * @return ListLiteral
     */
    public static ListLiteral of(Literal<?>... values) {
        return of(List.of(values));
    }

    public static ListLiteral of(String... values) {
        return of(Stream.of(values).map(IdentifierlLiteral::of).collect(Collectors.toList()));
    }

    public static ListLiteral of(Duration... values) {
        return of(Stream.of(values).map(DurationLiteral::of).collect(Collectors.toList()));
    }

    public static ListLiteral of(Number... values) {
        return of(Stream.of(values).map(NumberLiteral::of).collect(Collectors.toList()));
    }

    @Override
    public List<Literal<?>> getValue() {
        return values;
    }

    @Override
    public String toString() {
        return "(" + values.toString() + ")";
    }
}
