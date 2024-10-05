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

import io.github.willena.influxql.ast.token.Privilege;

import java.util.List;

class GrantStatementTest extends GenericStatementTest<GrantStatement> {
    private static final List<TestBody<GrantStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.READ_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT READ ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.WRITE_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT WRITE ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.ALL_PRIVILEGES)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT ALL PRIVILEGES ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.NO_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT NO PRIVILEGES ON db TO Usr")
                    .build()
    );
}