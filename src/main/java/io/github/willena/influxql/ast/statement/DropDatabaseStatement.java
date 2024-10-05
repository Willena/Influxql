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

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

public class DropDatabaseStatement implements Statement {
    private final String name;

    private DropDatabaseStatement(Builder builder) {
        name = builder.name;
    }

    @Override
    public String toString() {
        return "DROP DATABASE " + quoteIdentifier(name);
    }

    public static final class Builder implements Buildable<DropDatabaseStatement> {
        private String name;

        public Builder() {}

        public Builder database(String name) {
            this.name = name;
            return this;
        }

        public DropDatabaseStatement build() {
            return new DropDatabaseStatement(this);
        }
    }
}
