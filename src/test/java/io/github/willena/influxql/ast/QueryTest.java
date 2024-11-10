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
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

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
        String str =
                Files.readString(
                        Paths.get(
                                Objects.requireNonNull(
                                                getClass().getResource("query_many.influxql"))
                                        .toURI()));
        Query q = Query.parse(str);
        assertNotNull(q);
        assertEquals(4, q.getStatements().size());
    }

    private static final List<Map.Entry<String, String>> INFLUXQL_STATEMENTS =
            List.<Map.Entry<String, String>>of(
                    Map.entry(
                            "ALTER RETENTION POLICY \"1h.cpu\" ON \"mydb\" DEFAULT",
                            "ALTER RETENTION POLICY \"1h.cpu\" ON mydb DEFAULT"),
                    Map.entry(
                            "ALTER RETENTION POLICY \"policy1\" ON \"somedb\" DURATION 1h REPLICATION 4",
                            "ALTER RETENTION POLICY policy1 ON somedb DURATION 1h REPLICATION 4"),
                    Map.entry(
                            "CREATE CONTINUOUS QUERY \"10m_event_count\" ON \"db_name\" BEGIN SELECT count(\"value\") INTO \"6_months\".\"events\" FROM \"events\" GROUP BY (10m) END",
                            "CREATE CONTINUOUS QUERY \"10m_event_count\" ON db_name BEGIN SELECT count(value) INTO \"6_months\".events FROM events GROUP BY (10m) END"),
                    Map.entry(
                            "CREATE CONTINUOUS QUERY \"1h_event_count\" ON \"db_name\" BEGIN SELECT sum(\"count\") as \"count\" INTO \"2_years\".\"events\" FROM \"6_months\".\"events\" GROUP BY time(1h) END",
                            "CREATE CONTINUOUS QUERY \"1h_event_count\" ON db_name BEGIN SELECT sum(count) AS count INTO \"2_years\".events FROM \"6_months\".events GROUP BY time(1h) END"),
                    Map.entry(
                            "CREATE CONTINUOUS QUERY \"cpu_mean\" ON \"db_name\" RESAMPLE EVERY 10s FOR 2m BEGIN SELECT mean(\"value\") INTO \"cpu_mean\" FROM \"cpu\" GROUP BY time(1m) END",
                            "CREATE CONTINUOUS QUERY cpu_mean ON db_name RESAMPLE EVERY 10s FOR 2m BEGIN SELECT mean(value) INTO cpu_mean FROM cpu GROUP BY time(1m) END"),
                    Map.entry("CREATE DATABASE \"foo\"", "CREATE DATABASE foo"),
                    Map.entry(
                            "CREATE DATABASE \"bar\" WITH DURATION 1d REPLICATION 1 SHARD DURATION 30m NAME \"myrp\"",
                            "CREATE DATABASE bar WITH DURATION 1d REPLICATION 1 SHARD DURATION 30m NAME myrp"),
                    Map.entry(
                            "CREATE DATABASE \"mydb\" WITH NAME \"myrp\"",
                            "CREATE DATABASE mydb WITH NAME myrp"),
                    Map.entry(
                            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2",
                            "CREATE RETENTION POLICY \"10m.events\" ON somedb DURATION 1h REPLICATION 2" // 1h == 60m; This behaviour is expected. When duration is serialized it uses to bigger round unit possible.
                            ),
                    Map.entry(
                            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2 DEFAULT",
                            "CREATE RETENTION POLICY \"10m.events\" ON somedb DURATION 1h REPLICATION 2 DEFAULT" // 1h == 60m; This behaviour is expected. When duration is serialized it uses to bigger round unit possible.
                            ),
                    Map.entry(
                            "CREATE RETENTION POLICY \"10m.events\" ON \"somedb\" DURATION 60m REPLICATION 2 SHARD DURATION 30m",
                            "CREATE RETENTION POLICY \"10m.events\" ON somedb DURATION 1h REPLICATION 2 SHARD DURATION 30m" // 1h == 60m; This behaviour is expected. When duration is serialized it uses to bigger round unit possible.
                            ),
                    Map.entry(
                            "CREATE SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\" DESTINATIONS ALL 'udp://example.com:9090'",
                            "CREATE SUBSCRIPTION sub0 ON mydb.autogen DESTINATIONS ALL 'udp://example.com:9090'"),
                    Map.entry(
                            "CREATE SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\" DESTINATIONS ANY 'udp://h1.example.com:9090', 'udp://h2.example.com:9090'",
                            "CREATE SUBSCRIPTION sub0 ON mydb.autogen DESTINATIONS ANY 'udp://h1.example.com:9090', 'udp://h2.example.com:9090'"),
                    Map.entry(
                            "CREATE USER \"jdoe\" WITH PASSWORD '1337password'",
                            "CREATE USER jdoe WITH PASSWORD '1337password'"),
                    Map.entry(
                            "CREATE USER \"jdoe\" WITH PASSWORD '133\\'7password' WITH ALL PRIVILEGES",
                            "CREATE USER jdoe WITH PASSWORD '133\\'7password' WITH ALL PRIVILEGES"),
                    Map.entry(
                            "CREATE USER \"jdoe\" WITH PASSWORD '1337password' WITH ALL PRIVILEGES",
                            "CREATE USER jdoe WITH PASSWORD '1337password' WITH ALL PRIVILEGES"),
                    Map.entry("DELETE FROM \"cpu\"", "DELETE FROM cpu"),
                    Map.entry(
                            "DELETE FROM \"cpu\" WHERE time < '2000-01-01T00:00:00Z'",
                            "DELETE FROM cpu WHERE time < '2000-01-01T00:00:00Z'"),
                    Map.entry(
                            "DELETE WHERE time < '2000-01-01T00:00:00Z'",
                            "DELETE WHERE time < '2000-01-01T00:00:00Z'"),
                    Map.entry(
                            "DROP CONTINUOUS QUERY \"myquery\" ON \"mydb\"",
                            "DROP CONTINUOUS QUERY myquery ON mydb"),
                    Map.entry("DROP DATABASE \"mydb\"", "DROP DATABASE mydb"),
                    Map.entry("DROP MEASUREMENT \"cpu\"", "DROP MEASUREMENT cpu"),
                    Map.entry(
                            "DROP RETENTION POLICY \"1h.cpu\" ON \"mydb\"",
                            "DROP RETENTION POLICY \"1h.cpu\" ON mydb"),
                    Map.entry(
                            "DROP SERIES FROM \"telegraf\".\"autogen\".\"cpu\" WHERE cpu = 'cpu8'",
                            "DROP SERIES FROM telegraf.autogen.cpu WHERE cpu = 'cpu8'"),
                    Map.entry("DROP SHARD 1", "DROP SHARD 1"),
                    Map.entry(
                            "DROP SUBSCRIPTION \"sub0\" ON \"mydb\".\"autogen\"",
                            "DROP SUBSCRIPTION sub0 ON mydb.autogen"),
                    Map.entry("DROP USER \"jdoe\"", "DROP USER jdoe"),
                    Map.entry(
                            "EXPLAIN SELECT SUM(pointReq) FROM \"_internal\".\"monitor\".\"write\" GROUP BY hostname",
                            "EXPLAIN SELECT SUM(pointReq) FROM _internal.monitor.\"write\" GROUP BY hostname" // "write" is a keyword quotes are required !
                            ),
                    Map.entry(
                            "EXPLAIN ANALYZE SELECT MEAN(usage_steal) FROM cpu WHERE time >= '2018-02-22T00:00:00Z' AND time < '2018-02-22T12:00:00Z'",
                            "EXPLAIN ANALYZE SELECT MEAN(usage_steal) FROM cpu WHERE time >= '2018-02-22T00:00:00Z' AND time < '2018-02-22T12:00:00Z'"),
                    Map.entry("GRANT ALL TO \"jdoe\"", "GRANT ALL PRIVILEGES TO jdoe"),
                    Map.entry("GRANT READ ON \"mydb\" TO \"jdoe\"", "GRANT READ ON mydb TO jdoe"),
                    Map.entry("KILL QUERY 36", "KILL QUERY 36"),
                    Map.entry("KILL QUERY 53 ON 'myhost:8088'", "KILL QUERY 53 ON 'myhost:8088'"),
                    Map.entry(
                            "REVOKE ALL PRIVILEGES FROM \"jdoe\"",
                            "REVOKE ALL PRIVILEGES FROM jdoe"),
                    Map.entry(
                            "REVOKE READ ON \"mydb\" FROM \"jdoe\"",
                            "REVOKE READ ON mydb FROM jdoe"),
                    //            Map.entry(
                    //                    "SELECT mean(\"value\") INTO \"cpu_1h\".:MEASUREMENT FROM
                    // /cpu.*/",
                    //                    "SELECT mean(value) INTO \"cpu_1h\".:MEASUREMENT FROM
                    // /cpu.*/"
                    //            ),
                    Map.entry(
                            "SELECT mean(\"value\") FROM \"cpu\" GROUP BY region, time(1d) fill(0) tz('America/Chicago')",
                            "SELECT mean(value) FROM cpu GROUP BY region, time(1d) fill(0) TZ('America/Chicago')"),
                    Map.entry("SHOW CONTINUOUS QUERIES", "SHOW CONTINUOUS QUERIES"),
                    Map.entry("SHOW DATABASES", "SHOW DATABASES"),
                    Map.entry("SHOW DIAGNOSTICS", "SHOW DIAGNOSTICS"),
                    Map.entry("SHOW FIELD KEY CARDINALITY", "SHOW FIELD KEY CARDINALITY"),
                    Map.entry(
                            "SHOW FIELD KEY EXACT CARDINALITY ON mydb",
                            "SHOW FIELD KEY EXACT CARDINALITY ON mydb"),
                    Map.entry("SHOW FIELD KEYS", "SHOW FIELD KEYS"),
                    Map.entry("SHOW FIELD KEYS FROM \"cpu\"", "SHOW FIELD KEYS FROM cpu"),
                    Map.entry("SHOW GRANTS FOR \"jdoe\"", "SHOW GRANTS FOR jdoe"),
                    Map.entry("SHOW MEASUREMENT CARDINALITY", "SHOW MEASUREMENT CARDINALITY"),
                    Map.entry(
                            "SHOW MEASUREMENT EXACT CARDINALITY ON mydb",
                            "SHOW MEASUREMENT EXACT CARDINALITY ON mydb"),
                    Map.entry("SHOW MEASUREMENTS", "SHOW MEASUREMENTS"),
                    Map.entry(
                            "SHOW MEASUREMENTS WHERE \"region\" = 'uswest' AND \"host\" = 'serverA'",
                            "SHOW MEASUREMENTS WHERE region = 'uswest' AND host = 'serverA'"),
                    Map.entry(
                            "SHOW MEASUREMENTS WITH MEASUREMENT =~ /h2o.*/",
                            "SHOW MEASUREMENTS WITH MEASUREMENT =~ /h2o.*/"),
                    Map.entry("SHOW QUERIES", "SHOW QUERIES"),
                    Map.entry(
                            "SHOW RETENTION POLICIES ON \"mydb\"",
                            "SHOW RETENTION POLICIES ON mydb"),
                    Map.entry(
                            "SHOW SERIES FROM \"telegraf\".\"autogen\".\"cpu\" WHERE cpu = 'cpu8'",
                            "SHOW SERIES FROM telegraf.autogen.cpu WHERE cpu = 'cpu8'"),
                    Map.entry("SHOW SERIES CARDINALITY", "SHOW SERIES CARDINALITY"),
                    Map.entry("SHOW SERIES CARDINALITY ON mydb", "SHOW SERIES CARDINALITY ON mydb"),
                    Map.entry("SHOW SERIES EXACT CARDINALITY", "SHOW SERIES EXACT CARDINALITY"),
                    Map.entry(
                            "SHOW SERIES EXACT CARDINALITY ON mydb",
                            "SHOW SERIES EXACT CARDINALITY ON mydb"),
                    Map.entry("SHOW SHARD GROUPS", "SHOW SHARD GROUPS"),
                    Map.entry("SHOW STATS", "SHOW STATS"),
                    Map.entry("SHOW STATS FOR 'indexes'", "SHOW STATS FOR 'indexes'"),
                    Map.entry("SHOW SUBSCRIPTIONS", "SHOW SUBSCRIPTIONS"),
                    Map.entry("SHOW TAG KEY CARDINALITY", "SHOW TAG KEY CARDINALITY"),
                    Map.entry("SHOW TAG KEY EXACT CARDINALITY", "SHOW TAG KEY EXACT CARDINALITY"),
                    Map.entry("SHOW TAG KEYS", "SHOW TAG KEYS"),
                    Map.entry("SHOW TAG KEYS FROM \"cpu\"", "SHOW TAG KEYS FROM cpu"),
                    Map.entry(
                            "SHOW TAG KEYS FROM \"cpu\" WHERE \"region\" = 'uswest'",
                            "SHOW TAG KEYS FROM cpu WHERE region = 'uswest'"),
                    Map.entry(
                            "SHOW TAG KEYS WHERE \"host\" = 'serverA'",
                            "SHOW TAG KEYS WHERE host = 'serverA'"),
                    Map.entry(
                            "SHOW TAG KEYS WITH KEY IN (\"region\", \"host\")",
                            "SHOW TAG KEYS WITH KEY IN (region, host)"),
                    Map.entry(
                            "SHOW TAG VALUES WITH KEY = \"region\"",
                            "SHOW TAG VALUES WITH KEY = region"),
                    Map.entry(
                            "SHOW TAG VALUES FROM \"cpu\" WITH KEY = \"region\"",
                            "SHOW TAG VALUES FROM cpu WITH KEY = region"),
                    Map.entry(
                            "SHOW TAG VALUES WITH KEY !~ /.*c.*/",
                            "SHOW TAG VALUES WITH KEY !~ /.*c.*/"),
                    Map.entry(
                            "SHOW TAG VALUES FROM \"cpu\" WITH KEY IN (\"region\", \"host\") WHERE \"service\" = 'redis'",
                            "SHOW TAG VALUES FROM cpu WITH KEY IN (region, host) WHERE service = 'redis'"),
                    Map.entry(
                            "SHOW TAG VALUES CARDINALITY WITH KEY = \"myTagKey\"",
                            "SHOW TAG VALUES CARDINALITY WITH KEY = myTagKey"),
                    Map.entry(
                            "SHOW TAG VALUES CARDINALITY WITH KEY = \"myTagKey\"",
                            "SHOW TAG VALUES CARDINALITY WITH KEY = myTagKey"),
                    Map.entry(
                            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = \"myTagKey\"",
                            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = myTagKey"),
                    Map.entry(
                            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = \"myTagKey\"",
                            "SHOW TAG VALUES EXACT CARDINALITY WITH KEY = myTagKey"),
                    Map.entry("SHOW USERS", "SHOW USERS"));

    /**
     * Test statements from <a
     * href="https://docs.influxdata.com/influxdb/v1/query_language/spec/">InfluxQl Doc</a>
     *
     * @param entry key is source; value is expected value
     */
    @ParameterizedTest
    @FieldSource("INFLUXQL_STATEMENTS")
    public void testManyStatements(Map.Entry<String, String> entry) {
        assertEquals(entry.getValue(), Query.parse(entry.getKey()).toString());
    }
}
