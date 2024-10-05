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
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;
import java.util.List;

class ShowSeriesStatementTest extends GenericStatementTest<ShowSeriesStatement> {
    private static final List<TestBody<ShowSeriesStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowSeriesStatement>()
                            .withStatement(new ShowSeriesStatement.Builder())
                            .withExpectedSql("SHOW SERIES")
                            .build(),
                    new TestBody.Builder<ShowSeriesStatement>()
                            .withStatement(
                                    new ShowSeriesStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("name")
                                                            .build())
                                            .on("db")
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("Field"),
                                                            IntegerLiteral.of(110),
                                                            Operator.EQ))
                                            .orderBy(
                                                    new SortField.Builder()
                                                            .field("Field")
                                                            .ascending(false)
                                                            .build())
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW SERIES ON db FROM \"name\" WHERE \"Field\" = 110 ORDER BY \"Field\" DESC LIMIT 1 OFFSET 10")
                            .build());
}
