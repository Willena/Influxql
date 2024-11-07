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

import io.github.willena.influxql.ast.Buildable;

import java.time.Duration;

/**
 * Helper class to build retention policy object; This one is not in the original AST but allow
 * deduplication of member and easier builder based usage
 */
public class RetentionPolicy {
    private final Duration retentionPolicyDuration;
    private final Integer retentionPolicyReplication;
    private final String retentionPolicyName;
    private final Duration retentionPolicyShardGroupDuration;

    private RetentionPolicy(Builder builder) {
        retentionPolicyDuration = builder.retentionPolicyDuration;
        retentionPolicyReplication = builder.retentionPolicyReplication;
        retentionPolicyName = builder.retentionPolicyName;
        retentionPolicyShardGroupDuration = builder.retentionPolicyShardGroupDuration;
    }

    public boolean isEmpty() {
        return retentionPolicyName == null &&
                retentionPolicyDuration == null &&
                retentionPolicyReplication == null &&
                retentionPolicyShardGroupDuration == null;
    }

    /**
     * Get the RP duration
     *
     * @return the duration
     */
    public Duration getRetentionPolicyDuration() {
        return retentionPolicyDuration;
    }

    /**
     * Get the RP replication
     *
     * @return the replication
     */
    public Integer getRetentionPolicyReplication() {
        return retentionPolicyReplication;
    }

    /**
     * Get the RP name
     *
     * @return the name
     */
    public String getRetentionPolicyName() {
        return retentionPolicyName;
    }

    /**
     * Get the RP shard duration
     *
     * @return the shard duration
     */
    public Duration getRetentionPolicyShardGroupDuration() {
        return retentionPolicyShardGroupDuration;
    }

    /**
     * {@code RetentionPolicy} builder static inner class.
     */
    public static final class Builder implements Buildable<RetentionPolicy> {
        private Duration retentionPolicyDuration;
        private Integer retentionPolicyReplication;
        private String retentionPolicyName;
        private Duration retentionPolicyShardGroupDuration;

        public Builder() {
        }

        /**
         * Sets the {@code retentionPolicyDuration} and returns a reference to this Builder enabling
         * method chaining.
         *
         * @param retentionPolicyDuration the {@code retentionPolicyDuration} to set
         * @return a reference to this Builder
         */
        public Builder duration(Duration retentionPolicyDuration) {
            this.retentionPolicyDuration = retentionPolicyDuration;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyReplication} and returns a reference to this Builder
         * enabling method chaining.
         *
         * @param retentionPolicyReplication the {@code retentionPolicyReplication} to set
         * @return a reference to this Builder
         */
        public Builder replication(Integer retentionPolicyReplication) {
            this.retentionPolicyReplication = retentionPolicyReplication;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyName} and returns a reference to this Builder enabling
         * method chaining.
         *
         * @param retentionPolicyName the {@code retentionPolicyName} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyName(String retentionPolicyName) {
            this.retentionPolicyName = retentionPolicyName;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyShardGroupDuration} and returns a reference to this
         * Builder enabling method chaining.
         *
         * @param retentionPolicyShardGroupDuration the {@code retentionPolicyShardGroupDuration} to
         *                                          set
         * @return a reference to this Builder
         */
        public Builder shardDuration(Duration retentionPolicyShardGroupDuration) {
            this.retentionPolicyShardGroupDuration = retentionPolicyShardGroupDuration;
            return this;
        }

        /**
         * Returns a {@code RetentionPolicy} built from the parameters previously set.
         *
         * @return a {@code RetentionPolicy} built with parameters of this {@code
         * RetentionPolicy.Builder}
         */
        public RetentionPolicy build() {
            return new RetentionPolicy(this);
        }
    }
}
