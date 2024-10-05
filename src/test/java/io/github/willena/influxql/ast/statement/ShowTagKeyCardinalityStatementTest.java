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
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;
import java.util.List;

class ShowTagKeyCardinalityStatementTest
        extends GenericStatementTest<ShowTagKeyCardinalityStatement> {

    private static final List<TestBody<ShowTagKeyCardinalityStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowTagKeyCardinalityStatement>()
                            .withStatement(new ShowTagKeyCardinalityStatement.Builder())
                            .withExpectedSql("SHOW TAG KEY CARDINALITY")
                            .build(),
                    new TestBody.Builder<ShowTagKeyCardinalityStatement>()
                            .withStatement(
                                    new ShowTagKeyCardinalityStatement.Builder()
                                            .exact()
                                            .on("db")
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("meas")
                                                            .build())
                                            .groupBy(new Dimension(VarRef.of("field")))
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("field"),
                                                            IntegerLiteral.of(0),
                                                            Operator.EQ))
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW TAG KEY EXACT CARDINALITY ON db FROM meas WHERE \"field\" = 0 GROUP BY \"field\" LIMIT 1 OFFSET 10")
                            .build());
}
