package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowShardGroupsStatementTest {
    @Test
    void test() {
        assertEquals("SHOW SHARD GROUPS", new ShowShardGroupsStatement().toString());
    }
}