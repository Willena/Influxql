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

class RevokeStatementTest extends GenericStatementTest<RevokeStatement> {
    private static final List<GenericStatementTest.TestBody<RevokeStatement>> TEST_BODIES =
            List.of(
                    new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                            .withStatement(
                                    new RevokeStatement.Builder()
                                            .from("Usr")
                                            .privilege(Privilege.READ_PRIVILEGE)
                                            .on("db"))
                            .withExpectedSql("REVOKE READ ON db FROM Usr")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                            .withStatement(
                                    new RevokeStatement.Builder()
                                            .from("Usr")
                                            .privilege(Privilege.WRITE_PRIVILEGE)
                                            .on("db"))
                            .withExpectedSql("REVOKE WRITE ON db FROM Usr")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                            .withStatement(
                                    new RevokeStatement.Builder()
                                            .from("Usr")
                                            .privilege(Privilege.ALL_PRIVILEGES)
                                            .on("db"))
                            .withExpectedSql("REVOKE ALL PRIVILEGES ON db FROM Usr")
                            .build(),
                    new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                            .withStatement(
                                    new RevokeStatement.Builder()
                                            .from("Usr")
                                            .privilege(Privilege.NO_PRIVILEGE)
                                            .on("db"))
                            .withExpectedSql("REVOKE NO PRIVILEGES ON db FROM Usr")
                            .build());
}
