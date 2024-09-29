package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.addons.RetentionPolicy;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.*;

public class CreateDatabaseStatement implements Statement {
    private final String name;
    private final RetentionPolicy retentionPolicy;

    private CreateDatabaseStatement(Builder builder) {
        name = builder.name;
        retentionPolicy = builder.retentionPolicy;

        ensureDefined("name", name);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("CREATE DATABASE ");
        buf.append(quoteIdentifier(name));
        if (retentionPolicy != null) {
            buf.append(" WITH");
            if (retentionPolicy.getRetentionPolicyDuration() != null) {
                buf.append(" DURATION ");
                buf.append(formatDuration(retentionPolicy.getRetentionPolicyDuration()));
            }
            if (retentionPolicy.getRetentionPolicyReplication() != null) {
                buf.append(" REPLICATION ");
                buf.append(retentionPolicy.getRetentionPolicyReplication());
            }
            if (retentionPolicy.getRetentionPolicyShardGroupDuration().compareTo(Duration.ZERO) > 0) {
                buf.append(" SHARD DURATION ");
                buf.append(formatDuration(retentionPolicy.getRetentionPolicyShardGroupDuration()));
            }
            if (retentionPolicy.getRetentionPolicyName() != null && !retentionPolicy.getRetentionPolicyName().isBlank()) {
                buf.append(" NAME ");
                buf.append(quoteIdentifier(retentionPolicy.getRetentionPolicyName()));
            }
        }
        return buf.toString();
    }

    /**
     * {@code CreateDatabaseStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<CreateDatabaseStatement> {
        private String name;
        private RetentionPolicy retentionPolicy;

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
         * @param retentionPolicy the {@code retentionPolicyCreate} to set
         * @return a reference to this Builder
         */
        public Builder withRetentionPolicy(RetentionPolicy retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
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
