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

class SetPasswordUserStatementTest extends GenericStatementTest<SetPasswordUserStatement> {
    private static final List<TestBody<SetPasswordUserStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<SetPasswordUserStatement>()
                            .withStatement(
                                    new SetPasswordUserStatement.Builder()
                                            .for_("name")
                                            .password("password"))
                            .withExpectedSql("SET PASSWORD FOR \"name\" = 'password'")
                            .build(),
                    new TestBody.Builder<SetPasswordUserStatement>()
                            .withStatement(
                                    new SetPasswordUserStatement.Builder()
                                            .for_("name")
                                            .password("password")
                                            .redacted())
                            .withExpectedSql("SET PASSWORD FOR \"name\" = [REDACTED]")
                            .build());
}
