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

import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

public class ShowRetentionPoliciesStatement implements Statement {
    private final String database;

    private ShowRetentionPoliciesStatement(Builder builder) {
        database = builder.database;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW RETENTION POLICIES");
        if (database != null && !database.isEmpty()) {
            buf.append(" ON ");
            buf.append(quoteIdentifier(database));
        }
        return buf.toString();
    }

    public static final class Builder implements Buildable<ShowRetentionPoliciesStatement> {
        private String database;

        public Builder() {
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public ShowRetentionPoliciesStatement build() {
            return new ShowRetentionPoliciesStatement(this);
        }
    }
}
