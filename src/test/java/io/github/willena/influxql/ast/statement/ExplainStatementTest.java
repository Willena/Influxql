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
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ExplainStatementTest extends GenericStatementTest<ExplainStatement> {
    private static final List<TestBody<ExplainStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ExplainStatement>()
                    .withStatement(
                            new ExplainStatement.Builder()
                                    .select(s -> s
                                            .select(new Field.Builder().withExpr(VarRef.of("F1")).build())
                                            .from(new Measurement.Builder().withName("Toto").build())
                                            .where(new BinaryExpression(VarRef.of("totoField"), IntegerLiteral.of(32), Operator.EQ))
                                    )
                    )
                    .withExpectedSql("EXPLAIN SELECT F1 FROM Toto WHERE totoField = 32")
                    .build(),
            new TestBody.Builder<ExplainStatement>()
                    .withStatement(
                            new ExplainStatement.Builder()
                                    .analyse()
                                    .verbose()
                                    .select(s -> s
                                            .select(new Field.Builder().withExpr(VarRef.of("F1")).build())
                                            .from(new Measurement.Builder().withName("Toto").build())
                                            .where(new BinaryExpression(VarRef.of("totoField"), IntegerLiteral.of(32), Operator.EQ))
                                    )
                    )
                    .withExpectedSql("EXPLAIN ANALYZE VERBOSE SELECT F1 FROM Toto WHERE totoField = 32")
                    .build()

    );
}