package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowShardGroupsStatement.showShardGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowShardGroupsStatementTest {
    @Test
    void test() {
        assertEquals("SHOW SHARD GROUPS", new ShowShardGroupsStatement().toString());
        assertEquals("SHOW SHARD GROUPS", showShardGroups().toString());
    }
}