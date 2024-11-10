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
import static io.github.willena.influxql.ast.utils.Utils.quoteString;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

public class KillQueryStatement implements Statement {
    private final Long queryId;
    private final String host;

    private KillQueryStatement(Builder builder) {
        queryId = builder.queryId;
        host = builder.host;
        ensureDefined("queryId", queryId);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("KILL QUERY ");
        buf.append(queryId);
        if (host != null && !host.isBlank()) {
            buf.append(" ON ");
            buf.append(quoteString(host));
        }
        return buf.toString();
    }

    /** {@code KillQueryStatement} builder static inner class. */
    public static final class Builder implements Buildable<KillQueryStatement> {
        private Long queryId;
        private String host;

        public Builder() {}

        /**
         * Sets the {@code queryId} and returns a reference to this Builder enabling method
         * chaining.
         *
         * @param queryId the {@code queryId} to set
         * @return a reference to this Builder
         */
        public Builder queryId(Long queryId) {
            this.queryId = queryId;
            return this;
        }

        /**
         * Sets the {@code host} and returns a reference to this Builder enabling method chaining.
         *
         * @param host the {@code host} to set
         * @return a reference to this Builder
         */
        public Builder on(String host) {
            this.host = host;
            return this;
        }

        /**
         * Returns a {@code KillQueryStatement} built from the parameters previously set.
         *
         * @return a {@code KillQueryStatement} built with parameters of this {@code
         *     KillQueryStatement.Builder}
         */
        public KillQueryStatement build() {
            return new KillQueryStatement(this);
        }
    }
}
