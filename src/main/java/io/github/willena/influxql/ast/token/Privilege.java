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

package io.github.willena.influxql.ast.token;

/**
 * Known privileges
 */
public enum Privilege {
    NO_PRIVILEGE("NO PRIVILEGES"),
    READ_PRIVILEGE("READ"),
    WRITE_PRIVILEGE("WRITE"),
    ALL_PRIVILEGES("ALL PRIVILEGES");

    private final String sql;

    /**
     * New privilege with influxQL String
     *
     * @param sql the InfluxQl representation
     */
    Privilege(String sql) {
        this.sql = sql;
    }

    /**
     * Return the representation
     *
     * @return representation String
     */
    @Override
    public String toString() {
        return sql;
    }
}
