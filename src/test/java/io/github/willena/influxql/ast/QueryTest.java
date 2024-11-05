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

import io.github.willena.influxql.ast.statement.ShowDatabasesStatement;
import io.github.willena.influxql.ast.statement.ShowMeasurementsStatement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    /**
     * Test statements from <a href="https://docs.influxdata.com/influxdb/v1/query_language/spec/">InfluxQl Doc</a>
     *
     * @param sourceSql influxql
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "ALTER RETENTION POLICY \"1h.cpu\" ON \"mydb\" DEFAULT",
            "ALTER RETENTION POLICY \"policy1\" ON \"somedb\" DURATION 1h REPLICATION 4",
            "CREATE CONTINUOUS QUERY \"10m_event_count\"\n" +
                    "ON \"db_name\"\n" +
                    "BEGIN\n" +
                    "  SELECT count(\"value\")\n" +
                    "  INTO \"6_months\".\"events\"\n" +
                    "  FROM \"events\"\n" +
                    "  GROUP BY (10m)\n" +
                    "END",
            "CREATE CONTINUOUS QUERY \"1h_event_count\"\n" +
                    "ON \"db_name\"\n" +
                    "BEGIN\n" +
                    "  SELECT sum(\"count\") as \"count\"\n" +
                    "  INTO \"2_years\".\"events\"\n" +
                    "  FROM \"6_months\".\"events\"\n" +
                    "  GROUP BY time(1h)\n" +
                    "END",
            "CREATE CONTINUOUS QUERY \"cpu_mean\"\n" +
                    "ON \"db_name\"\n" +
                    "RESAMPLE EVERY 10s FOR 2m\n" +
                    "BEGIN\n" +
                    "  SELECT mean(\"value\")\n" +
                    "  INTO \"cpu_mean\"\n" +
                    "  FROM \"cpu\"\n" +
                    "  GROUP BY time(1m)\n" +
                    "END",
            "CREATE DATABASE \"foo\"",
            "CREATE DATABASE \"bar\" WITH DURATION 1d REPLICATION 1 SHARD DURATION 30m NAME \"myrp\"",
            "CREATE DATABASE \"mydb\" WITH NAME \"myrp\"",
            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2",
            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2 DEFAULT",
            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2 SHARD DURATION 30m",
            "CREATE SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\" DESTINATIONS ALL 'udp://example.com:9090'",
            "CREATE SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\" DESTINATIONS ANY 'udp://h1.example.com:9090', 'udp://h2.example.com:9090'",
            "CREATE USER \"jdoe\" WITH PASSWORD '1337password'",
            "CREATE USER \"jdoe\" WITH PASSWORD '1337password' WITH ALL PRIVILEGES",
            "DELETE FROM \"cpu\"",
            "DELETE FROM \"cpu\" WHERE time < '2000-01-01T00:00:00Z'",
            "DELETE WHERE time < '2000-01-01T00:00:00Z'",
            "DROP CONTINUOUS QUERY \"myquery\" ON \"mydb\"",
            "DROP DATABASE \"mydb\"",
            "DROP MEASUREMENT \"cpu\"",
            "DROP RETENTION POLICY \"1h.cpu\" ON \"mydb\"",
            "DROP SERIES FROM \"telegraf\".\"autogen\".\"cpu\" WHERE cpu = 'cpu8'",
            "DROP SHARD 1",
            "DROP SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\"",
            "DROP USER \"jdoe\"",
            "EXPLAIN SELECT SUM(pointReq) FROM \"_internal\".\"monitor\".\"write\" GROUP BY hostname",
            "EXPLAIN ANALYZE SELECT MEAN(usage_steal) FROM cpu WHERE time >= '2018-02-22T00:00:00Z' AND time < '2018-02-22T12:00:00Z'",
            "GRANT ALL TO \"jdoe\"",
            "GRANT READ ON \"mydb\" TO \"jdoe\"",
            "KILL QUERY 36",
            "KILL QUERY 53 ON 'myhost:8088'",
            "REVOKE ALL PRIVILEGES FROM \"jdoe\"",
            "REVOKE READ ON \"mydb\" FROM \"jdoe\"",
            "SELECT mean(\"value\") INTO \"cpu_1h\".:MEASUREMENT FROM /cpu.*/",
            "SELECT mean(\"value\") FROM \"cpu\" GROUP BY region, time(1d) fill(0) tz('America/Chicago')",
            "SHOW CONTINUOUS QUERIES",
            "SHOW DATABASES",
            "SHOW DIAGNOSTICS",
            "SHOW FIELD KEY CARDINALITY",
            "SHOW FIELD KEY EXACT CARDINALITY ON mydb",
            "SHOW FIELD KEYS",
            "SHOW FIELD KEYS FROM \"cpu\"",
            "SHOW GRANTS FOR \"jdoe\"",
            "SHOW MEASUREMENT CARDINALITY",
            "SHOW MEASUREMENT EXACT CARDINALITY ON mydb",
            "SHOW MEASUREMENTS",
            "SHOW MEASUREMENTS WHERE \"region\" = 'uswest' AND \"host\" = 'serverA'",
            "SHOW MEASUREMENTS WITH MEASUREMENT =~ /h2o.*/",
            "SHOW QUERIES",
            "SHOW RETENTION POLICIES ON \"mydb\"",
            "SHOW SERIES FROM \"telegraf\".\"autogen\".\"cpu\" WHERE cpu = 'cpu8'",
            "SHOW SERIES CARDINALITY",
            "SHOW SERIES CARDINALITY ON mydb",
            "SHOW SERIES EXACT CARDINALITY",
            "SHOW SERIES EXACT CARDINALITY ON mydb",
            "SHOW SHARD GROUPS",
            "SHOW STATS",
            "SHOW STATS FOR 'indexes'",
            "SHOW SUBSCRIPTIONS",
            "SHOW TAG KEY CARDINALITY",
            "SHOW TAG KEY EXACT CARDINALITY",
            "SHOW TAG KEYS",
            "SHOW TAG KEYS FROM \"cpu\"",
            "SHOW TAG KEYS FROM \"cpu\" WHERE \"region\" = 'uswest'",
            "SHOW TAG KEYS WHERE \"host\" = 'serverA'",
            "SHOW TAG KEYS WITH KEY IN (\"region\", \"host\")",
            "SHOW TAG VALUES WITH KEY = \"region\"",
            "SHOW TAG VALUES FROM \"cpu\" WITH KEY = \"region\"",
            "SHOW TAG VALUES WITH KEY !~ /.*c.*/",
            "SHOW TAG VALUES FROM \"cpu\" WITH KEY IN (\"region\", \"host\") WHERE \"service\" = 'redis'",
            "SHOW TAG VALUES CARDINALITY WITH KEY = \"myTagKey\"",
            "SHOW TAG VALUES CARDINALITY WITH KEY = \"myTagKey\"",
            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = \"myTagKey\"",
            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = \"myTagKey\"",
            "SHOW USERS",
    })
    void checkParserAgainstAllInfluxdbDocStatements(String sourceSql) {
        assertEquals(sourceSql, Query.parse(sourceSql).toString());
    }
}
