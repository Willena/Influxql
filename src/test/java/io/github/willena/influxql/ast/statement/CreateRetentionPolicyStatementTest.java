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

import java.time.Duration;
import java.util.List;

class CreateRetentionPolicyStatementTest
        extends GenericStatementTest<CreateRetentionPolicyStatement> {

    public static final List<TestBody<CreateRetentionPolicyStatement>> TEST_BODIES =
            List.of(
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database"))
                            .withExpectedSql("Missing replication, duration")
                            .withShouldFail(true)
                            .build(),
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database")
                                            .withIsDefault(true))
                            .withShouldFail(true)
                            .withExpectedSql("Missing replication, duration")
                            .build(),
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database")
                                            .duration(Duration.ofHours(1)))
                            .withExpectedSql("Missing replication")
                            .withShouldFail(true)
                            .build(),
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database")
                                            .shardDuration(Duration.ofHours(1)))
                            .withShouldFail(true)
                            //                    .withExpectedSql("CREATE RETENTION POLICY
                            // PolicyName ON \"database\" DURATION 0s REPLICATION 0 DEFAULT")
                            .build(),
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database")
                                            .shardDuration(Duration.ofHours(1))
                                            .duration(Duration.ofHours(3)))
                            .withShouldFail(true)
                            .withExpectedSql("Missing replication")
                            .build(),
                    new TestBody.Builder<CreateRetentionPolicyStatement>()
                            .withStatement(
                                    new CreateRetentionPolicyStatement.Builder()
                                            .name("PolicyName")
                                            .on("database")
                                            .shardDuration(Duration.ofHours(1))
                                            .duration(Duration.ofHours(3))
                                            .replication(36))
                            .withExpectedSql(
                                    "CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                            .build());
}
