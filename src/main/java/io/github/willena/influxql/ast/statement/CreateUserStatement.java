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

public class CreateUserStatement implements Statement {
    private final String name;
    private final String password;
    private final boolean admin;

    private CreateUserStatement(Builder builder) {
        name = builder.name;
        password = builder.password;
        admin = builder.admin;

        ensureDefined("name", name);
        ensureDefined("password", password);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("CREATE USER ");
        buf.append(quoteIdentifier(name));
        buf.append(" WITH PASSWORD ");
        buf.append(quoteString(password));
        if (admin) {
            buf.append(" WITH ALL PRIVILEGES");
        }
        return buf.toString();
    }

    /** {@code CreateUserStatement} builder static inner class. */
    public static final class Builder implements Buildable<CreateUserStatement> {
        private String name;
        private String password;
        private boolean admin;

        public Builder() {}

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder username(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param password the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the {@code admin} and returns a reference to this Builder enabling method chaining.
         *
         * @param admin the {@code admin} to set
         * @return a reference to this Builder
         */
        public Builder admin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public Builder admin() {
            return admin(true);
        }

        /**
         * Returns a {@code CreateUserStatement} built from the parameters previously set.
         *
         * @return a {@code CreateUserStatement} built with parameters of this {@code
         *     CreateUserStatement.Builder}
         */
        public CreateUserStatement build() {
            return new CreateUserStatement(this);
        }
    }
}
