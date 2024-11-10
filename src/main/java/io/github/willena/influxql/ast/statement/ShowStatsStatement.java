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

import static io.github.willena.influxql.ast.utils.Utils.quoteString;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import java.util.Objects;

public class ShowStatsStatement implements Statement {
    private final String module;

    private ShowStatsStatement(Builder builder) {
        module = builder.module;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW STATS");
        if (module != null && !module.isEmpty()) {
            buf.append(" FOR ");
            buf.append(quoteString(module));
        }
        return buf.toString();
    }

    public String getModule() {
        return module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowStatsStatement that = (ShowStatsStatement) o;
        return Objects.equals(module, that.module);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(module);
    }

    public static ShowStatsStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::show_stats_stmt, (c, a) -> a.visitShow_stats_stmt(c), sql);
    }

    public static final class Builder implements Buildable<ShowStatsStatement> {
        private String module;

        public Builder() {}

        public Builder for_(String module) {
            this.module = module;
            return this;
        }

        public ShowStatsStatement build() {
            return new ShowStatsStatement(this);
        }
    }
}
