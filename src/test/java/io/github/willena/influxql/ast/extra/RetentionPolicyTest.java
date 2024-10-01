package io.github.willena.influxql.ast.extra;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetentionPolicyTest {
    @Test
    void retentionPolicy() {
        RetentionPolicy rp = new RetentionPolicy.Builder()
                .withRetentionPolicyDuration(Duration.ofSeconds(1))
                .withRetentionPolicyName("test")
                .withRetentionPolicyShardGroupDuration(Duration.ofHours(1))
                .withRetentionPolicyReplication(10)
                .build();

        assertEquals("test", rp.getRetentionPolicyName());
        assertEquals(Duration.ofSeconds(1), rp.getRetentionPolicyDuration());
        assertEquals(Duration.ofHours(1), rp.getRetentionPolicyShardGroupDuration());
        assertEquals(10, rp.getRetentionPolicyReplication());
    }
}