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

public class ShowGrantsForUserStatement implements Statement {
    private final String name;

    private ShowGrantsForUserStatement(Builder builder) {
        name = builder.name;
        ensureDefined("name", name);
    }

    @Override
    public String toString() {
        return "SHOW GRANTS FOR " + quoteIdentifier(name);
    }

    public static ShowGrantsForUserStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::show_grants_stmt, (c, a) -> a.visitShow_grants_stmt(c), sql);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowGrantsForUserStatement that = (ShowGrantsForUserStatement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static final class Builder implements Buildable<ShowGrantsForUserStatement> {
        private String name;

        public Builder() {}

        public Builder for_(String name) {
            this.name = name;
            return this;
        }

        public ShowGrantsForUserStatement build() {
            return new ShowGrantsForUserStatement(this);
        }
    }
}
