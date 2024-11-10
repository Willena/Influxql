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
import java.time.temporal.ChronoUnit;
import java.util.List;

class AlterRetentionPolicyStatementTest
        extends GenericStatementTest<AlterRetentionPolicyStatement> {

    public static final List<GenericStatementTest.TestBody<AlterRetentionPolicyStatement>>
            TEST_BODIES =
                    List.of(
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database"))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\"")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database")
                                                    .default_())
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\" DEFAULT")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database")
                                                    .duration(Duration.of(1, ChronoUnit.HOURS)))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 1h")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database")
                                                    .shardDuration(
                                                            Duration.of(1, ChronoUnit.HOURS)))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\" SHARD DURATION 1h")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database")
                                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                                    .duration(Duration.of(3, ChronoUnit.HOURS)))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h SHARD DURATION 1h")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName")
                                                    .on("database")
                                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                                                    .replicationFactor(36))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("PolicyName↔")
                                                    .on("datab'ase")
                                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                                                    .replicationFactor(36))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY \"PolicyName↔\" ON \"datab'ase\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                                    .build(),
                            new GenericStatementTest.TestBody.Builder<
                                            AlterRetentionPolicyStatement>()
                                    .withStatement(
                                            new AlterRetentionPolicyStatement.Builder()
                                                    .policyName("policy")
                                                    .on("dbName")
                                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                                                    .replicationFactor(36)
                                                    .futureLimit(Duration.ofHours(1))
                                                    .pastLimit(Duration.ofHours(2)))
                                    .withExpectedSql(
                                            "ALTER RETENTION POLICY \"policy\" ON dbName DURATION 3h REPLICATION 36 SHARD DURATION 1h FUTURE LIMIT 1h PAST LIMIT 2h")
                                    .build());
}
