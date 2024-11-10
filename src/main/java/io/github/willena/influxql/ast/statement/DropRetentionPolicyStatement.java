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
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.Objects;

public class DropRetentionPolicyStatement implements Statement {

    private final String name;
    private final String database;

    private DropRetentionPolicyStatement(Builder builder) {
        name = builder.name;
        database = builder.database;
        ensureDefined("name", name);
        ensureDefined("database", database);
    }

    @Override
    public String toString() {
        return "DROP RETENTION POLICY "
                + quoteIdentifier(name)
                + " ON "
                + quoteIdentifier(database);
    }

    public String getName() {
        return name;
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DropRetentionPolicyStatement that = (DropRetentionPolicyStatement) o;
        return Objects.equals(name, that.name) && Objects.equals(database, that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, database);
    }

    public static DropRetentionPolicyStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::drop_retention_policy_stmt,
                (c, a) -> a.visitDrop_retention_policy_stmt(c),
                sql);
    }

    public static final class Builder implements Buildable<DropRetentionPolicyStatement> {
        private String name;
        private String database;

        public Builder() {}

        public Builder retentionPolicy(String name) {
            this.name = name;
            return this;
        }

        public Builder on(String database) {
            this.database = database;
            return this;
        }

        public DropRetentionPolicyStatement build() {
            return new DropRetentionPolicyStatement(this);
        }
    }
}
