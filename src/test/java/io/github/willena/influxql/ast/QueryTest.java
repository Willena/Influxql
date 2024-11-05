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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.willena.influxql.ast.statement.ShowDatabasesStatement;
import io.github.willena.influxql.ast.statement.ShowMeasurementsStatement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

class QueryTest {

    @Test
    void test() {
        Query q =
                Query.ofStatements(
                        new ShowDatabasesStatement(),
                        new ShowMeasurementsStatement.Builder().build());
        assertEquals("SHOW DATABASES; SHOW MEASUREMENTS", q.toString());
    }

    @Test
    void parse_multiple_statements() throws URISyntaxException, IOException {
        String str = Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource("query_many.influxql")).toURI()));
        Query q = Query.parse(str);
        assertNotNull(q);
        assertEquals(4, q.getStatements().size());
    }
}
