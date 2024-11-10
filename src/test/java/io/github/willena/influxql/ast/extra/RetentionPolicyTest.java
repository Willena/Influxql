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

package io.github.willena.influxql.ast.extra;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class RetentionPolicyTest {
    @Test
    void retentionPolicy() {
        RetentionPolicy rp =
                new RetentionPolicy.Builder()
                        .duration(Duration.ofSeconds(1))
                        .retentionPolicyName("test")
                        .shardDuration(Duration.ofHours(1))
                        .replication(10)
                        .build();

        assertEquals("test", rp.getRetentionPolicyName());
        assertEquals(Duration.ofSeconds(1), rp.getRetentionPolicyDuration());
        assertEquals(Duration.ofHours(1), rp.getRetentionPolicyShardGroupDuration());
        assertEquals(10, rp.getRetentionPolicyReplication());
    }

    @Test
    void isEmpty() {
        assertTrue(new RetentionPolicy.Builder().build().isEmpty());
        assertFalse(new RetentionPolicy.Builder().retentionPolicyName("nn").build().isEmpty());
        assertFalse(new RetentionPolicy.Builder().duration(Duration.ZERO).build().isEmpty());
        assertFalse(new RetentionPolicy.Builder().shardDuration(Duration.ZERO).build().isEmpty());
        assertFalse(new RetentionPolicy.Builder().replication(2).build().isEmpty());
    }
}
