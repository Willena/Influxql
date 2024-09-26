package io.github.willena.influxql.ast.statement;

import java.time.Duration;
import java.util.List;

class CreateRetentionPolicyStatementTest extends GenericStatementTest<CreateRetentionPolicyStatement> {

    public static final List<TestBody<CreateRetentionPolicyStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                    )
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 0s REPLICATION 0")
                    .withShouldFail(true)
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withIsDefault(true)
                    )
                    .withShouldFail(true)
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 0s REPLICATION 0 DEFAULT")
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(

                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withDuration(Duration.ofHours(1))
                    )
                    .withShouldFail(true)
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 0s REPLICATION 0 DEFAULT")
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.ofHours(1))
                    )
                    .withShouldFail(true)
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 0s REPLICATION 0 DEFAULT")
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.ofHours(1))
                                    .withDuration(Duration.ofHours(3))
                    )
                    .withShouldFail(true)
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 0 SHARD DURATION 1h")
                    .build(),
            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.ofHours(1))
                                    .withDuration(Duration.ofHours(3))
                                    .withReplication(36)
                    )
                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build()
    );
}