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

import java.util.List;

class ShowRetentionPoliciesStatementTest extends GenericStatementTest<ShowRetentionPoliciesStatement> {
    private static final List<TestBody<ShowRetentionPoliciesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowRetentionPoliciesStatement>()
                    .withStatement(
                            new ShowRetentionPoliciesStatement.Builder()
                    )
                    .withExpectedSql("SHOW RETENTION POLICIES")
                    .build(),
            new TestBody.Builder<ShowRetentionPoliciesStatement>()
                    .withStatement(
                            new ShowRetentionPoliciesStatement.Builder()
                                    .on("db")
                    )
                    .withExpectedSql("SHOW RETENTION POLICIES ON db")
                    .build()
    );

}