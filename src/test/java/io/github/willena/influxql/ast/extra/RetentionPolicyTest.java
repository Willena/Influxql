package io.github.willena.influxql.ast.extra;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetentionPolicyTest {
    @Test
    void retentionPolicy() {
        RetentionPolicy rp = new RetentionPolicy.Builder()
                .duration(Duration.ofSeconds(1))
                .withRetentionPolicyName("test")
                .shardDuration(Duration.ofHours(1))
                .replication(10)
                .build();

        assertEquals("test", rp.getRetentionPolicyName());
        assertEquals(Duration.ofSeconds(1), rp.getRetentionPolicyDuration());
        assertEquals(Duration.ofHours(1), rp.getRetentionPolicyShardGroupDuration());
        assertEquals(10, rp.getRetentionPolicyReplication());
    }
}