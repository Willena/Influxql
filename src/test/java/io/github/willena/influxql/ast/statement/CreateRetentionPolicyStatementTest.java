package io.github.willena.influxql.ast.statement;

import java.time.Duration;
import java.util.List;

class CreateRetentionPolicyStatementTest extends GenericStatementTest<CreateRetentionPolicyStatement> {

    public static final List<TestBody<CreateRetentionPolicyStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .name("PolicyName")
                                    .on("database")
                    )
                    .withExpectedSql("Missing replication, duration")
                    .withShouldFail(true)
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .name("PolicyName")
                                    .on("database")
                                    .withIsDefault(true)
                    )
                    .withShouldFail(true)
                    .withExpectedSql("Missing replication, duration")
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .name("PolicyName")
                                    .on("database")
                                    .duration(Duration.ofHours(1))
                    )
                    .withExpectedSql("Missing replication")
                    .withShouldFail(true)
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .name("PolicyName")
                                    .on("database")
                                    .shardDuration(Duration.ofHours(1))
                    )
                    .withShouldFail(true)
//                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 0s REPLICATION 0 DEFAULT")
                    .build(),

            new TestBody.Builder<CreateRetentionPolicyStatement>()
                    .withStatement(
                            new CreateRetentionPolicyStatement.Builder()
                                    .name("PolicyName")
                                    .on("database")
                                    .shardDuration(Duration.ofHours(1))
                                    .duration(Duration.ofHours(3))
                    )
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
                                    .replication(36)
                    )
                    .withExpectedSql("CREATE RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build()
    );
}