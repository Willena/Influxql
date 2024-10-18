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
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;
import java.util.List;

class ShowTagValuesStatementTest extends GenericStatementTest<ShowTagValuesStatement> {
    private static final List<TestBody<ShowTagValuesStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowTagValuesStatement>()
                            .withStatement(new ShowTagValuesStatement.Builder())
                            .withShouldFail(true)
                            .withExpectedSql("missing fields")
                            .build(),
                    new TestBody.Builder<ShowTagValuesStatement>()
                            .withStatement(
                                    new ShowTagValuesStatement.Builder()
                                            .withKey(Operator.EQ, StringLiteral.of("kkk")))
                            .withExpectedSql("SHOW TAG VALUES WITH KEY = kkk")
                            .build(),
                    new TestBody.Builder<ShowTagValuesStatement>()
                            .withStatement(
                                    new ShowTagValuesStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("name")
                                                            .build())
                                            .orderBy(
                                                    new SortField.Builder()
                                                            .ascending(false)
                                                            .field("field")
                                                            .build())
                                            .on("db")
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("kkk"),
                                                            StringLiteral.of("jkj"),
                                                            Operator.NEQ))
                                            .limit(1)
                                            .offset(10)
                                            .withKey(Operator.EQ, StringLiteral.of("kkk")))
                            .withExpectedSql(
                                    "SHOW TAG VALUES ON db FROM \"name\" WITH KEY = kkk WHERE kkk != 'jkj' ORDER BY \"field\" DESC LIMIT 1 OFFSET 10")
                            .build());
}
