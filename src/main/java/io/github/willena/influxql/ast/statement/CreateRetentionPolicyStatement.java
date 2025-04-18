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

package io.github.willena.influxql.ast.statement;

import static io.github.willena.influxql.ast.utils.Utils.*;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.time.Duration;
import java.util.Objects;

public class CreateRetentionPolicyStatement implements Statement {
    private final String name;
    private final String database;
    private final Duration duration;
    private final Integer replication;
    private final boolean isDefault;
    private final Duration shardGroupDuration;
    private final Duration futureLimit;
    private final Duration pastLimit;

    private CreateRetentionPolicyStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        duration = builder.duration;
        replication = builder.replication;
        isDefault = builder.isDefault;
        shardGroupDuration = builder.shardGroupDuration;
        futureLimit = builder.futureLimit;
        pastLimit = builder.pastLimit;

        ensureDefined("name", name);
        ensureDefined("database", database);
        ensureDefined("duration", duration);
        ensureDefined("replication", replication);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("CREATE RETENTION POLICY ");
        buf.append(quoteIdentifier(name));
        buf.append(" ON ");
        buf.append(quoteIdentifier(database));
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

        if (futureLimit != null && futureLimit.compareTo(Duration.ZERO) > 0) {
            buf.append(" FUTURE LIMIT ");
            buf.append(formatDuration(futureLimit));
        }

        if (pastLimit != null && pastLimit.compareTo(Duration.ZERO) > 0) {
            buf.append(" PAST LIMIT ");
            buf.append(formatDuration(pastLimit));
        }

        return buf.toString();
    }

    public String getName() {
        return name;
    }

    public String getDatabase() {
        return database;
    }

    public Duration getDuration() {
        return duration;
    }

    public Integer getReplication() {
        return replication;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Duration getShardGroupDuration() {
        return shardGroupDuration;
    }

    public Duration getFutureLimit() {
        return futureLimit;
    }

    public Duration getPastLimit() {
        return pastLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateRetentionPolicyStatement that = (CreateRetentionPolicyStatement) o;
        return isDefault == that.isDefault
                && Objects.equals(name, that.name)
                && Objects.equals(database, that.database)
                && Objects.equals(duration, that.duration)
                && Objects.equals(replication, that.replication)
                && Objects.equals(shardGroupDuration, that.shardGroupDuration)
                && Objects.equals(futureLimit, that.futureLimit)
                && Objects.equals(pastLimit, that.pastLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                database,
                duration,
                replication,
                isDefault,
                shardGroupDuration,
                futureLimit,
                pastLimit);
    }

    public static CreateRetentionPolicyStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::create_retention_policy_stmt,
                (c, a) -> a.visitCreate_retention_policy_stmt(c),
                sql);
    }

    /** {@code CreateRetentionPolicyStatement} builder static inner class. */
    public static final class Builder implements Buildable<CreateRetentionPolicyStatement> {
        private String name;
        private String database;
        private Duration duration;
        private Integer replication;
        private boolean isDefault;
        private Duration shardGroupDuration;
        public Duration futureLimit;
        public Duration pastLimit;

        public Builder() {}

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code database} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param database the {@code database} to set
         * @return a reference to this Builder
         */
        public Builder on(String database) {
            this.database = database;
            return this;
        }

        /**
         * Sets the {@code duration} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param duration the {@code duration} to set
         * @return a reference to this Builder
         */
        public Builder duration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the {@code replication} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param replication the {@code replication} to set
         * @return a reference to this Builder
         */
        public Builder replication(Integer replication) {
            this.replication = replication;
            return this;
        }

        /**
         * Sets the {@code isDefault} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param isDefault the {@code isDefault} to set
         * @return a reference to this Builder
         */
        public Builder withIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        /**
         * Sets the {@code shardGroupDuration} and returns a reference to this Builder enabling
         * method chaining.
         *
         * @param shardGroupDuration the {@code shardGroupDuration} to set
         * @return a reference to this Builder
         */
        public Builder shardDuration(Duration shardGroupDuration) {
            this.shardGroupDuration = shardGroupDuration;
            return this;
        }

        /**
         * Future limit builder.
         *
         * @param duration the duration
         * @return the builder
         */
        public Builder futureLimit(Duration duration) {
            this.futureLimit = duration;
            return this;
        }

        /**
         * Past limit builder.
         *
         * @param duration the duration
         * @return the builder
         */
        public Builder pastLimit(Duration duration) {
            this.pastLimit = duration;
            return this;
        }

        /**
         * Returns a {@code CreateRetentionPolicyStatement} built from the parameters previously
         * set.
         *
         * @return a {@code CreateRetentionPolicyStatement} built with parameters of this {@code
         *     CreateRetentionPolicyStatement.Builder}
         */
        public CreateRetentionPolicyStatement build() {
            return new CreateRetentionPolicyStatement(this);
        }
    }
}
