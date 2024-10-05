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

class DropSeriesStatementTest extends GenericStatementTest<DropSeriesStatement> {
    private static final List<GenericStatementTest.TestBody<DropSeriesStatement>> TEST_BODIES =
            List.of(
                    new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                            .withStatement(
                                    new DropSeriesStatement.Builder()
                                            .from(new Measurement.Builder().withName("M").build()))
                            .withExpectedSql("DROP SERIES FROM M")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                            .withStatement(
                                    new DropSeriesStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("M")
                                                            .withDatabase("db")
                                                            .withRetentionPolicy("policy1")
                                                            .build()))
                            .withExpectedSql("DROP SERIES FROM db.policy1.M")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                            .withStatement(
                                    new DropSeriesStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withRegex(Pattern.compile(".*"))
                                                            .build())
                                            .where(
                                                    BinaryExpression.eq(
                                                            VarRef.of("cpu"),
                                                            StringLiteral.of("cpu2"))))
                            .withExpectedSql("DROP SERIES FROM /.*/ WHERE cpu = 'cpu2'")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                            .withStatement(
                                    new DropSeriesStatement.Builder()
                                            .where(
                                                    BinaryExpression.eq(
                                                            VarRef.of("cpu"),
                                                            StringLiteral.of("cpu2"))))
                            .withExpectedSql("DROP SERIES WHERE cpu = 'cpu2'")
                            .build());
}
