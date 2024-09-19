package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import java.time.Duration;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.Utils.formatDuration;

public class CreateDatabaseStatement implements Statement {
    private final String name;
    private final boolean retentionPolicyCreate;
    private final Duration retentionPolicyDuration;
    private final Integer retentionPolicyReplication;
    private final String retentionPolicyName;
    private final Duration retentionPolicyShardGroupDuration;

    private CreateDatabaseStatement(Builder builder) {
        name = builder.name;
        retentionPolicyCreate = builder.retentionPolicyCreate;
        retentionPolicyDuration = builder.retentionPolicyDuration;
        retentionPolicyReplication = builder.retentionPolicyReplication;
        retentionPolicyName = builder.retentionPolicyName;
        retentionPolicyShardGroupDuration = builder.retentionPolicyShardGroupDuration;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("CREATE DATABASE ");
        buf.append(QuoteIdent(name));
        if (retentionPolicyCreate) {
            buf.append(" WITH");
            if (retentionPolicyDuration != null) {
                buf.append(" DURATION ");
                buf.append(formatDuration(retentionPolicyDuration));
            }
            if (retentionPolicyReplication != null) {
                buf.append(" REPLICATION ");
                buf.append(retentionPolicyReplication);
            }
            if (retentionPolicyShardGroupDuration.compareTo(Duration.ZERO) > 0) {
                buf.append(" SHARD DURATION ");
                buf.append(formatDuration(retentionPolicyShardGroupDuration));
            }
            if (!retentionPolicyName.isBlank()) {
                buf.append(" NAME ");
                buf.append(QuoteIdent(retentionPolicyName));
            }
        }
        return buf.toString();
    }

    /**
     * {@code CreateDatabaseStatement} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private boolean retentionPolicyCreate;
        private Duration retentionPolicyDuration;
        private Integer retentionPolicyReplication;
        private String retentionPolicyName;
        private Duration retentionPolicyShardGroupDuration;

        public Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code retentionPolicyCreate} and returns a reference to this Builder enabling method chaining.
         *
         * @param retentionPolicyCreate the {@code retentionPolicyCreate} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicyCreate(boolean retentionPolicyCreate) {
            this.retentionPolicyCreate = retentionPolicyCreate;
            return this;
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
         * Returns a {@code CreateDatabaseStatement} built from the parameters previously set.
         *
         * @return a {@code CreateDatabaseStatement} built with parameters of this {@code CreateDatabaseStatement.Builder}
         */
        public CreateDatabaseStatement build() {
            return new CreateDatabaseStatement(this);
        }
    }
}
