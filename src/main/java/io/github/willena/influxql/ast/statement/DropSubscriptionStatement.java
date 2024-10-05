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

public class DropSubscriptionStatement implements Statement {
    private final String name;
    private final String database;
    private final String retentionPolicy;

    private DropSubscriptionStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        retentionPolicy = builder.retentionPolicy;

        ensureDefined("name", name);
        ensureDefined("database", database);
        ensureDefined("retentionPolicy", retentionPolicy);
    }

    @Override
    public String toString() {
        return "DROP SUBSCRIPTION "
                + quoteIdentifier(name)
                + " ON "
                + quoteIdentifier(database)
                + "."
                + quoteIdentifier(retentionPolicy);
    }

    public static final class Builder implements Buildable<DropSubscriptionStatement> {
        private String name;
        private String database;
        private String retentionPolicy;

        public Builder() {}

        public Builder subscription(String name) {
            this.name = name;
            return this;
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public Builder retentionPolicy(String retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
            return this;
        }

        public DropSubscriptionStatement build() {
            return new DropSubscriptionStatement(this);
        }
    }
}
