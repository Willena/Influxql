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
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;
import java.util.List;
import java.util.regex.Pattern;

class ShowMeasurementsStatementTest extends GenericStatementTest<ShowMeasurementsStatement> {
    private static final List<TestBody<ShowMeasurementsStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowMeasurementsStatement>()
                            .withStatement(new ShowMeasurementsStatement.Builder())
                            .withExpectedSql("SHOW MEASUREMENTS")
                            .build(),
                    new TestBody.Builder<ShowMeasurementsStatement>()
                            .withStatement(
                                    new ShowMeasurementsStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("name")
                                                            .build())
                                            .on("db")
                                            .retentionPolicy("policy")
                                            .orderBy(new SortField.Builder().field("name").build())
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("field"),
                                                            StringLiteral.of("ok"),
                                                            Operator.EQ))
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW MEASUREMENTS ON db.policy WITH MEASUREMENT = \"name\" WHERE \"field\" = 'ok' ORDER BY \"name\" DESC LIMIT 1 OFFSET 10")
                            .build(),
                    new TestBody.Builder<ShowMeasurementsStatement>()
                            .withStatement(
                                    new ShowMeasurementsStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("name")
                                                            .build())
                                            .on("db")
                                            .retentionPolicy("policy")
                                            .orderBy(
                                                    new SortFields(
                                                            List.of(
                                                                    new SortField.Builder()
                                                                            .field("name")
                                                                            .build())))
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("field"),
                                                            StringLiteral.of("ok"),
                                                            Operator.EQ))
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW MEASUREMENTS ON db.policy WITH MEASUREMENT = \"name\" WHERE \"field\" = 'ok' ORDER BY \"name\" DESC LIMIT 1 OFFSET 10")
                            .build(),
                    new TestBody.Builder<ShowMeasurementsStatement>()
                            .withStatement(
                                    new ShowMeasurementsStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withRegex(Pattern.compile(".*"))
                                                            .build())
                                            .on("db")
                                            .retentionPolicy("*")
                                            .orderBy(new SortField.Builder().field("name").build())
                                            .where(
                                                    new BinaryExpression(
                                                            VarRef.of("field"),
                                                            StringLiteral.of("ok"),
                                                            Operator.EQ))
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW MEASUREMENTS ON db.* WITH MEASUREMENT =~ /.*/ WHERE \"field\" = 'ok' ORDER BY \"name\" DESC LIMIT 1 OFFSET 10")
                            .build());
}
