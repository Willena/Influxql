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

import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import java.util.List;

class ShowFieldKeysStatementTest extends GenericStatementTest<ShowFieldKeysStatement> {
    private static final List<TestBody<ShowFieldKeysStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<ShowFieldKeysStatement>()
                            .withStatement(new ShowFieldKeysStatement.Builder())
                            .withExpectedSql("SHOW FIELD KEYS")
                            .build(),
                    new TestBody.Builder<ShowFieldKeysStatement>()
                            .withStatement(
                                    new ShowFieldKeysStatement.Builder()
                                            .from(
                                                    new Measurement.Builder()
                                                            .withName("name")
                                                            .build())
                                            .orderBy(
                                                    new SortField.Builder()
                                                            .field("field")
                                                            .ascending(false)
                                                            .build())
                                            .on("db")
                                            .limit(1)
                                            .offset(10))
                            .withExpectedSql(
                                    "SHOW FIELD KEYS ON db FROM \"name\" ORDER BY \"field\" DESC LIMIT 1 OFFSET 10")
                            .build());
}
