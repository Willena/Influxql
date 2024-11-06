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

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

/**
 * Use {@link RevokeStatement}
 */
@Deprecated(forRemoval = true)
public class RevokeAdminStatement implements Statement {
    private final String user;

    private RevokeAdminStatement(Builder builder) {
        user = builder.user;
        ensureDefined("user", user);
    }

    @Override
    public String toString() {
        return "REVOKE ALL PRIVILEGES FROM " + quoteIdentifier(user);
    }

    /**
     * Use {@link RevokeStatement.Builder}
     */
    @Deprecated(forRemoval = true)
    public static final class Builder implements Buildable<RevokeAdminStatement> {
        private String user;

        public Builder() {}

        public Builder from(String user) {
            this.user = user;
            return this;
        }

        public RevokeAdminStatement build() {
            return new RevokeAdminStatement(this);
        }
    }
}
