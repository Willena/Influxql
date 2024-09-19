package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import java.time.Duration;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.Utils.formatDuration;

public class CreateRetentionPolicyStatement implements Statement {
    private final String name;
    private final String database;
    private final Duration duration;
    private final int replication;
    private final boolean isDefault;
    private final Duration shardGroupDuration;

    private CreateRetentionPolicyStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        duration = builder.duration;
        replication = builder.replication;
        isDefault = builder.isDefault;
        shardGroupDuration = builder.shardGroupDuration;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("CREATE RETENTION POLICY ");
        buf.append(QuoteIdent(name));
        buf.append(" ON ");
        buf.append(QuoteIdent(database));
        buf.append(" DURATION ");
        buf.append(formatDuration(duration));
        buf.append(" REPLICATION ");
        buf.append(replication);
        if (shardGroupDuration != null && shardGroupDuration.compareTo(Duration.ZERO) > 0) {
            buf.append(" SHARD DURATION ");
            buf.append(formatDuration(shardGroupDuration));
        }
        if (isDefault) {
            buf.append(" DEFAULT");
        }
        return buf.toString();
    }

    /**
     * {@code CreateRetentionPolicyStatement} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String database;
        private Duration duration;
        private int replication;
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
        public Builder withName(String name) {
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
        public Builder withReplication(int replication) {
            this.replication = replication;
            return this;
        }

        /**
         * Sets the {@code isDefault} and returns a reference to this Builder enabling method chaining.
         *
         * @param isDefault the {@code isDefault} to set
         * @return a reference to this Builder
         */
        public Builder withIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        /**
         * Sets the {@code shardGroupDuration} and returns a reference to this Builder enabling method chaining.
         *
         * @param shardGroupDuration the {@code shardGroupDuration} to set
         * @return a reference to this Builder
         */
        public Builder withShardGroupDuration(Duration shardGroupDuration) {
            this.shardGroupDuration = shardGroupDuration;
            return this;
        }

        /**
         * Returns a {@code CreateRetentionPolicyStatement} built from the parameters previously set.
         *
         * @return a {@code CreateRetentionPolicyStatement} built with parameters of this {@code CreateRetentionPolicyStatement.Builder}
         */
        public CreateRetentionPolicyStatement build() {
            return new CreateRetentionPolicyStatement(this);
        }
    }
}
