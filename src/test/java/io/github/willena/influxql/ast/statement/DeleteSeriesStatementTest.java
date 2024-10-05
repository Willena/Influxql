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

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;

import java.util.List;
import java.util.regex.Pattern;

class DeleteSeriesStatementTest extends GenericStatementTest<DeleteSeriesStatement> {
    private static final List<TestBody<DeleteSeriesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(Measurement.measurement("M"))
                    )
                    .withExpectedSql("DELETE FROM M")
                    .build(),
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(new Measurement.Builder()
                                            .withName("M")
                                            .withDatabase("db")
                                            .withRetentionPolicy("policy1")
                                            .build()
                                    )
                    )
                    .withExpectedSql("DELETE FROM db.policy1.M")
                    .build(),

            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(new Measurement.Builder()
                                            .withRegex(Pattern.compile(".*"))
                                            .build()
                                    )
                                    .where(BinaryExpression.eq(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2")
                                    ))
                    )
                    .withExpectedSql("DELETE FROM /.*/ WHERE cpu = 'cpu2'")
                    .build(),

            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .where(BinaryExpression.eq(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2")
                                    ))
                    )
                    .withExpectedSql("DELETE WHERE cpu = 'cpu2'")
                    .build()
    );
}