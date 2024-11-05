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

package io.github.willena.influxql.ast;

import io.github.willena.influxql.parser.DefaultParser;
import io.github.willena.influxql.parser.InfluxqlAstAdapter;
import io.github.willena.influxql.parser.InfluxqlParser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Top level {@link Node} is a {@link Query} Each query can contain multiple statements
 */
public class Query implements Node {
    private final List<Statement> statements;

    /**
     * Build a new Query using a list of statements
     *
     * @param statements list of statements
     */
    public Query(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    /**
     * Build a query based on provided statements (at least one)
     *
     * @param statement  a statement
     * @param statements other statements
     * @return a query with provided statements
     */
    public static Query ofStatements(Statement statement, Statement... statements) {
        List<Statement> list = new LinkedList<>();
        list.add(statement);
        list.addAll(List.of(statements));
        return new Query(list);
    }

    public static Query parse(String sql) {
        return DefaultParser.parseFrom(InfluxqlParser::query, (c, a) -> a.visitQuery(c), sql);
    }

    @Override
    public String toString() {
        return statements.stream().map(Statement::toString).collect(Collectors.joining("; "));
    }
}
