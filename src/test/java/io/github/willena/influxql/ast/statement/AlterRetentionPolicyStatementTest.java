package io.github.willena.influxql.ast.statement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

class AlterRetentionPolicyStatementTest extends GenericStatementTest<AlterRetentionPolicyStatement> {

    public static final List<GenericStatementTest.TestBody<AlterRetentionPolicyStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\"")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                                    .default_()
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DEFAULT")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                                    .duration(Duration.of(1, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 1h")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName")
                                    .on("database")
                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                                    .replicationFactor(36)
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .policyName("PolicyName↔")
                                    .on("datab'ase")
                                    .shardDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .duration(Duration.of(3, ChronoUnit.HOURS))
                                    .replicationFactor(36)
                    )
                    .withExpectedSql("ALTER RETENTION POLICY \"PolicyName↔\" ON \"datab'ase\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build()
    );
}