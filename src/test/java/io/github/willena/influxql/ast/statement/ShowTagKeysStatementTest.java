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

class ShowTagKeysStatementTest extends GenericStatementTest<ShowTagKeysStatement> {
    private static final List<TestBody<ShowTagKeysStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowTagKeysStatement>()
                    .withStatement(
                            new ShowTagKeysStatement.Builder()
                    )
                    .withExpectedSql("SHOW TAG KEYS")
                    .build(),
            new TestBody.Builder<ShowTagKeysStatement>()
                    .withStatement(
                            new ShowTagKeysStatement.Builder()
                                    .from(new Measurement.Builder().withName("name").build())
                                    .on("db")
                                    .orderBy(new SortField.Builder().field("name").build())
                                    .where(new BinaryExpression(VarRef.of("oooo"), IntegerLiteral.of(10), Operator.EQ))
                                    .limit(1)
                                    .sLimit(2)
                                    .offset(10)
                                    .sOffset(11)
                    )
                    .withExpectedSql("SHOW TAG KEYS ON db FROM \"name\" WHERE oooo = 10 ORDER BY \"name\" DESC LIMIT 1 OFFSET 10 SLIMIT 2 SOFFSET 11")
                    .build()
    );
}