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

import io.github.willena.influxql.ast.Statement;
import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;

public class ShowDatabasesStatement implements Statement {
    public ShowDatabasesStatement() {}

    public static ShowDatabasesStatement showDatabases() {
        return new ShowDatabasesStatement();
    }

    public static ShowDatabasesStatement parse(String sql) {
        return DefaultParser.parseFrom(
                InfluxqlParser::show_databases_stmt, (c, a) -> a.visitShow_databases_stmt(c), sql);
    }

    @Override
    public String toString() {
        return "SHOW DATABASES";
    }
}
