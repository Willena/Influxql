package io.github.willena.influxql.ast.statement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

class AlterRetentionPolicyStatementTest extends GenericStatementTest<AlterRetentionPolicyStatement> {

    public static final List<GenericStatementTest.TestBody<AlterRetentionPolicyStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\"")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .isDefault(true)
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DEFAULT")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withDuration(Duration.of(1, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DEFAULT")
                    .build(),
            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .withDuration(Duration.of(3, ChronoUnit.HOURS))
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName")
                                    .withDatabase("database")
                                    .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .withDuration(Duration.of(3, ChronoUnit.HOURS))
                                    .withReplicationFactor(36)
                    )
                    .withExpectedSql("ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build(),

            new GenericStatementTest.TestBody.Builder<AlterRetentionPolicyStatement>()
                    .withStatement(
                            new AlterRetentionPolicyStatement.Builder()
                                    .withPolicyName("PolicyName↔")
                                    .withDatabase("datab'ase")
                                    .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                    .withDuration(Duration.of(3, ChronoUnit.HOURS))
                                    .withReplicationFactor(36)
                    )
                    .withExpectedSql("ALTER RETENTION POLICY \"PolicyName↔\" ON \"datab'ase\" DURATION 3h REPLICATION 36 SHARD DURATION 1h")
                    .build()
    );
}