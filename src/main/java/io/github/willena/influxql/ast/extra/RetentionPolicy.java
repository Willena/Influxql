package io.github.willena.influxql.ast.extra;

import io.github.willena.influxql.ast.Buildable;

import java.time.Duration;

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

    public Duration getRetentionPolicyDuration() {
        return retentionPolicyDuration;
    }

    public Integer getRetentionPolicyReplication() {
        return retentionPolicyReplication;
    }

    public String getRetentionPolicyName() {
        return retentionPolicyName;
    }

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
         * Sets the {@code retentionPolicyDuration} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicyDuration the {@code retentionPolicyDuration} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyDuration(Duration retentionPolicyDuration) {
            this.retentionPolicyDuration = retentionPolicyDuration;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyReplication} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicyReplication the {@code retentionPolicyReplication} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyReplication(Integer retentionPolicyReplication) {
            this.retentionPolicyReplication = retentionPolicyReplication;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyName} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicyName the {@code retentionPolicyName} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyName(String retentionPolicyName) {
            this.retentionPolicyName = retentionPolicyName;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyShardGroupDuration} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicyShardGroupDuration the {@code retentionPolicyShardGroupDuration} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyShardGroupDuration(Duration retentionPolicyShardGroupDuration) {
            this.retentionPolicyShardGroupDuration = retentionPolicyShardGroupDuration;
            return this;
        }

        /**
         * Returns a {@code RetentionPolicy} built from the parameters previously set.
         *
         * @return a {@code RetentionPolicy} built with parameters of this {@code RetentionPolicy.Builder}
         */
        public RetentionPolicy build() {
            return new RetentionPolicy(this);
        }
    }
}
