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

public class GrantAdminStatement implements Statement {
    private final String user;

    private GrantAdminStatement(Builder builder) {
        user = builder.user;
        ensureDefined("user", user);
    }

    @Override
    public String toString() {
        return "GRANT ALL PRIVILEGES TO " + quoteIdentifier(user);
    }

    public static final class Builder implements Buildable<GrantAdminStatement> {
        private String user;

        public Builder() {}

        public Builder to(String user) {
            this.user = user;
            return this;
        }

        public GrantAdminStatement build() {
            return new GrantAdminStatement(this);
        }
    }
}
