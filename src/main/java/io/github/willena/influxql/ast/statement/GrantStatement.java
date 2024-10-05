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

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.ast.token.Privilege;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class GrantStatement implements Statement {
    private final Privilege privilege;
    private final String database;
    private final String username;

    private GrantStatement(Builder builder) {
        privilege = builder.privilege;
        database = builder.database;
        username = builder.username;
        ensureDefined("privilege", privilege);
        ensureDefined("user", username);
        ensureDefined("database", database);
    }

    @Override
    public String toString() {
        return "GRANT " +
                privilege.toString() +
                " ON " +
                quoteIdentifier(database) +
                " TO " +
                quoteIdentifier(username);
    }

    public static final class Builder implements Buildable<GrantStatement> {
        private Privilege privilege;
        private String database;
        private String username;

        public Builder() {
        }

        public Builder privilege(Privilege privilege) {
            this.privilege = privilege;
            return this;
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public Builder to(String username) {
            this.username = username;
            return this;
        }

        public GrantStatement build() {
            return new GrantStatement(this);
        }
    }
}
