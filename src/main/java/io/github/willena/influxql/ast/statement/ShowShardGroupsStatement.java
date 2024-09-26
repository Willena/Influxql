package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowShardGroupsStatement implements Statement {
    public ShowShardGroupsStatement() {
    }

    @Override
    public String toString() {
        return "SHOW SHARD GROUPS";
    }
}
