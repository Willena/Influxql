package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import java.time.Duration;

import static io.github.willena.influxql.ast.utils.Utils.*;

public class AlterRetentionPolicyStatement implements Statement {
    // Name of policy to alter.
    private final String name;
    // Name of the database this policy belongs to.
    private final String database;
    // Duration data written to this policy will be retained.
    private final Duration duration;
    // Replication factor for data written to this policy.
    private final Integer replication;
    // Should this policy be set as defalut for the database?
    private final boolean isDefault;
    // Duration of the Shard.
    private final Duration shardGroupDuration;

    private AlterRetentionPolicyStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        duration = builder.duration;
        replication = builder.replication;
        isDefault = builder.isDefault;
        shardGroupDuration = builder.shardGroupDuration;

        ensureDefined("name", name);
        ensureDefined("database", database);

    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("ALTER RETENTION POLICY ");
        buf.append(quoteIdentifier(name));
        buf.append(" ON ");
        buf.append(quoteIdentifier(database));

        if (duration != null) {
            buf.append(" DURATION ");
            buf.append(formatDuration(duration));
        }

        if (replication != null) {
            buf.append(" REPLICATION ");
            buf.append(replication);
        }

        if (shardGroupDuration != null) {
            buf.append(" SHARD DURATION ");
            buf.append(formatDuration(shardGroupDuration));
        }

        if (isDefault) {
            buf.append(" DEFAULT");
        }

        return buf.toString();

    }

    /**
     * {@code AlterRetentionPolicyStatement} builder static inner class.
     */
    public static final class Builder implements Buildable<AlterRetentionPolicyStatement> {
        private String name;
        private String database;
        private Duration duration;
        private Integer replication;
        private boolean isDefault;
        private Duration shardGroupDuration;

        public Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withPolicyName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code database} and returns a reference to this Builder enabling method chaining.
         *
         * @param database the {@code database} to set
         * @return a reference to this Builder
         */
        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code duration} and returns a reference to this Builder enabling method chaining.
         *
         * @param duration the {@code duration} to set
         * @return a reference to this Builder
         */
        public Builder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the {@code replication} and returns a reference to this Builder enabling method chaining.
         *
         * @param replication the {@code replication} to set
         * @return a reference to this Builder
         */
        public Builder withReplicationFactor(Integer replication) {
            this.replication = replication;
            return this;
        }

        /**
         * Sets the {@code isDefault} and returns a reference to this Builder enabling method chaining.
         *
         * @param isDefault the {@code isDefault} to set
         * @return a reference to this Builder
         */
        public Builder isDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Builder withShardGroupDuration(Duration shardGroupDuration) {
            this.shardGroupDuration = shardGroupDuration;
            return this;
        }

        /**
         * Returns a {@code AlterRetentionPolicyStatement} built from the parameters previously set.
         *
         * @return a {@code AlterRetentionPolicyStatement} built with parameters of this {@code AlterRetentionPolicyStatement.Builder}
         */
        public AlterRetentionPolicyStatement build() {
            return new AlterRetentionPolicyStatement(this);
        }
    }
}

