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

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.token.DestinationMode;
import io.github.willena.influxql.ast.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateSubscriptionStatement implements Statement {
    private final String name;
    private final String database;
    private final String retentionPolicy;
    private final List<String> destinations;
    private final DestinationMode mode;

    private CreateSubscriptionStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        retentionPolicy = builder.retentionPolicyName;
        destinations = builder.destinations;
        mode = builder.mode;

        ensureDefined("name", name);
        ensureDefined("database", database);
        ensureDefined("retentionPolicy", retentionPolicy);
        ensureDefined("mode", mode);
        ensureDefined("destinations", destinations);
    }

    @Override
    public String toString() {
        return "CREATE SUBSCRIPTION "
                + quoteIdentifier(name)
                + " ON "
                + quoteIdentifier(database)
                + "."
                + quoteIdentifier(retentionPolicy)
                + " DESTINATIONS "
                + mode
                + " "
                + destinations.stream().map(Utils::quoteString).collect(Collectors.joining(", "));
    }

    /** {@code CreateSubscriptionStatement} builder static inner class. */
    public static final class Builder implements Buildable<CreateSubscriptionStatement> {
        private String name;
        private String database;
        private String retentionPolicyName;
        private List<String> destinations;
        private DestinationMode mode;

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
         * Sets the {@code retentionPolicy} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param retentionPolicy the {@code retentionPolicy} to set
         * @return a reference to this Builder
         */
        public Builder retentionPolicy(String retentionPolicy) {
            this.retentionPolicyName = retentionPolicy;
            return this;
        }

        /**
         * Sets the {@code destinations} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param destinations the {@code destinations} to set
         * @return a reference to this Builder
         */
        public Builder destinations(List<String> destinations) {
            this.destinations = destinations;
            return this;
        }

        public Builder destinations(String destination, String... destinations) {
            if (this.destinations == null) {
                this.destinations = new LinkedList<>();
            }
            this.destinations.add(destination);
            this.destinations.addAll(List.of(destinations));
            return this;
        }

        /**
         * Sets the {@code mode} and returns a reference to this Builder enabling method chaining.
         *
         * @param mode the {@code mode} to set
         * @return a reference to this Builder
         */
        public Builder destinationMode(DestinationMode mode) {
            this.mode = mode;
            return this;
        }

        /**
         * Returns a {@code CreateSubscriptionStatement} built from the parameters previously set.
         *
         * @return a {@code CreateSubscriptionStatement} built with parameters of this {@code
         *     CreateSubscriptionStatement.Builder}
         */
        public CreateSubscriptionStatement build() {
            return new CreateSubscriptionStatement(this);
        }
    }
}
