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

class ShowTagValuesCardinalityStatementTest extends GenericStatementTest<ShowTagValuesCardinalityStatement> {
    private static final List<TestBody<ShowTagValuesCardinalityStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowTagValuesCardinalityStatement>()
                    .withStatement(
                            new ShowTagValuesCardinalityStatement.Builder()
                    )
                    .withExpectedSql("Missing some fields")
                    .withShouldFail(true)
                    .build(),
            new TestBody.Builder<ShowTagValuesCardinalityStatement>()
                    .withStatement(
                            new ShowTagValuesCardinalityStatement.Builder()
                                    .withKey(Operator.EQ, StringLiteral.of("value"))
                    )
                    .withExpectedSql("SHOW TAG VALUES CARDINALITY WITH KEY = value")
                    .build(),
            new TestBody.Builder<ShowTagValuesCardinalityStatement>()
                    .withStatement(
                            new ShowTagValuesCardinalityStatement.Builder()
                                    .exact()
                                    .withKey(Operator.EQ, StringLiteral.of("value"))
                                    .on("db")
                                    .from(new Measurement.Builder().withName("name").build())
                                    .groupBy(new Dimension(VarRef.of("field")))
                                    .where(new BinaryExpression(VarRef.of("kkk"), StringLiteral.of("oooo"), Operator.NEQ))
                                    .limit(1)
                                    .offset(10)
                    )
                    .withExpectedSql("SHOW TAG VALUES EXACT CARDINALITY ON db FROM \"name\" WITH KEY = value WHERE kkk != 'oooo' GROUP BY \"field\" LIMIT 1 OFFSET 10")
                    .build()
    );
}