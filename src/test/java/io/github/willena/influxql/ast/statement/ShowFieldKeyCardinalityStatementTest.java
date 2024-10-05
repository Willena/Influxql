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
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;
import java.util.List;

class ShowFieldKeyCardinalityStatementTest
        extends GenericStatementTest<ShowFieldKeyCardinalityStatement> {
    private static final List<TestBody<ShowFieldKeyCardinalityStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowFieldKeyCardinalityStatement>()
                            .withStatement(new ShowFieldKeyCardinalityStatement.Builder())
                            .withExpectedSql("SHOW FIELD KEY CARDINALITY")
                            .build(),
                    new TestBody.Builder<ShowFieldKeyCardinalityStatement>()
                            .withStatement(
                                    new ShowFieldKeyCardinalityStatement.Builder()
                                            .exact()
                                            .on("database")
                                            .groupBy(new Dimension(VarRef.of("tag")))
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("tag"),
                                                            StringLiteral.of("ok"),
                                                            Operator.EQ))
                                            .limit(1)
                                            .offset(10)
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("Measurment")
                                                            .build()))
                            .withExpectedSql(
                                    "SHOW FIELD KEY EXACT CARDINALITY ON \"database\" FROM Measurment WHERE \"tag\" = 'ok' GROUP BY \"tag\" LIMIT 1 OFFSET 10")
                            .build());
}
