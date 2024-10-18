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

import io.github.willena.influxql.ast.token.DestinationMode;
import java.util.List;

class CreateSubscriptionStatementTest extends GenericStatementTest<CreateSubscriptionStatement> {
    public static final List<TestBody<CreateSubscriptionStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<CreateSubscriptionStatement>()
                            .withStatement(
                                    new CreateSubscriptionStatement.Builder()
                                            .name("Name")
                                            .on("db")
                                            .retentionPolicy("policy")
                                            .destinationMode(DestinationMode.ALL)
                                            .destinations("host1"))
                            .withExpectedSql(
                                    "CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ALL 'host1'")
                            .build(),
                    new TestBody.Builder<CreateSubscriptionStatement>()
                            .withStatement(
                                    new CreateSubscriptionStatement.Builder()
                                            .name("Name")
                                            .on("db")
                                            .retentionPolicy("policy")
                                            .destinationMode(DestinationMode.ANY)
                                            .destinations("host1"))
                            .withExpectedSql(
                                    "CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ANY 'host1'")
                            .build(),
                    new TestBody.Builder<CreateSubscriptionStatement>()
                            .withStatement(
                                    new CreateSubscriptionStatement.Builder()
                                            .name("Name")
                                            .on("db")
                                            .retentionPolicy("policy")
                                            .destinationMode(DestinationMode.ANY)
                                            .destinations("host1", "host2"))
                            .withExpectedSql(
                                    "CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ANY 'host1', 'host2'")
                            .build());
}
