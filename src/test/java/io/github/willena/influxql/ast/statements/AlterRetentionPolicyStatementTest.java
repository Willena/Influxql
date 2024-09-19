package io.github.willena.influxql.ast.statements;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlterRetentionPolicyStatementTest {
    public static Stream<Arguments> testValidProvider() {
        return Stream.of(
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database"),
                        "ALTER RETENTION POLICY PolicyName ON \"database\"",
                        false
                ),
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database")
                                .isDefault(true),
                        "ALTER RETENTION POLICY PolicyName ON \"database\" DEFAULT",
                        false
                ),
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database")
                                .withDuration(Duration.of(1, ChronoUnit.HOURS)),
                        "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 1h",
                        false
                ),
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database")
                                .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS)),
                        "ALTER RETENTION POLICY PolicyName ON \"database\" SHARD DURATION 1h",
                        false
                ),
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database")
                                .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                .withDuration(Duration.of(3, ChronoUnit.HOURS)),
                        "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h SHARD DURATION 1h",
                        false
                ),
                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName")
                                .withDatabase("database")
                                .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                .withDuration(Duration.of(3, ChronoUnit.HOURS))
                                .withReplicationFactor(36),
                        "ALTER RETENTION POLICY PolicyName ON \"database\" DURATION 3h REPLICATION 36 SHARD DURATION 1h ",
                        false
                ),

                Arguments.of(
                        new AlterRetentionPolicyStatement.Builder()
                                .withPolicyName("PolicyName↔")
                                .withDatabase("datab'ase")
                                .withShardGroupDuration(Duration.of(1, ChronoUnit.HOURS))
                                .withDuration(Duration.of(3, ChronoUnit.HOURS))
                                .withReplicationFactor(36),
                        "ALTER RETENTION POLICY \"PolicyName↔\" ON \"datab'ase\" DURATION 3h REPLICATION 36 SHARD DURATION 1h ",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testValidProvider")
    void testStatement(AlterRetentionPolicyStatement.Builder statement, String expected, boolean shouldFail) {
        if (shouldFail) {
            assertThrows(Exception.class, statement::build);
        } else {
            AlterRetentionPolicyStatement sql = assertDoesNotThrow(statement::build);
            assertEquals(expected, sql.toString());
        }
    }
}