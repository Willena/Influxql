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

import io.github.willena.influxql.ast.extra.RetentionPolicy;
import java.time.Duration;
import java.util.List;

class CreateDatabaseStatementTest extends GenericStatementTest<CreateDatabaseStatement> {

    public static final List<TestBody<CreateDatabaseStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<CreateDatabaseStatement>()
                            .withStatement(
                                    new CreateDatabaseStatement.Builder().name("DatabaseName"))
                            .withExpectedSql("CREATE DATABASE DatabaseName")
                            .build(),
                    new TestBody.Builder<CreateDatabaseStatement>()
                            .withStatement(
                                    new CreateDatabaseStatement.Builder()
                                            .name("Datab'aseName")
                                            .withRetentionPolicy(
                                                    new RetentionPolicy.Builder()
                                                            .replication(3)
                                                            .duration(Duration.ofHours(1))
                                                            .shardDuration(Duration.ofHours(2))
                                                            .build()))
                            .withExpectedSql(
                                    "CREATE DATABASE \"Datab'aseName\" WITH DURATION 1h REPLICATION 3 SHARD DURATION 2h")
                            .build(),
                    new TestBody.Builder<CreateDatabaseStatement>()
                            .withStatement(
                                    new CreateDatabaseStatement.Builder()
                                            .name("myDb")
                                            .withRetentionPolicy(
                                                    new RetentionPolicy.Builder()
                                                            .replication(3)
                                                            .duration(Duration.ofHours(1))
                                                            .shardDuration(Duration.ofHours(2))
                                                            .futureLimit(Duration.ofHours(1))
                                                            .pastLimit(Duration.ofHours(12))
                                                            .build()))
                            .withExpectedSql(
                                    "CREATE DATABASE myDb WITH DURATION 1h REPLICATION 3 SHARD DURATION 2h FUTURE LIMIT 1h PAST LIMIT 12h")
                            .build());
}
