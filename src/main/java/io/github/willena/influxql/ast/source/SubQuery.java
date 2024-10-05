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

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.statement.SelectStatement;

import java.util.function.Function;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Define a subquery containing a single {@link SelectStatement}
 */
public class SubQuery implements Source {
    private final SelectStatement statement;

    /**
     * Build a new {@link SubQuery}
     *
     * @param statement select statement builder
     */
    public SubQuery(SelectStatement statement) {
        this.statement = statement;
        ensureDefined("statement", statement);
    }

    /**
     * Helper method to build a subquery
     *
     * @param statement {@link SelectStatement} value
     * @return the {@link SubQuery}
     */
    public static SubQuery of(SelectStatement statement) {
        return new SubQuery(statement);
    }

    /**
     * Helper method to build a subquery
     *
     * @param statement select statement builder
     * @return the {@link SubQuery}
     */
    public static SubQuery of(Function<SelectStatement.Builder, SelectStatement.Builder> statement) {
        return new SubQuery(statement.apply(new SelectStatement.Builder()).build());
    }

    @Override
    public String toString() {
        return "(" + statement.toString() + ")";
    }
}
