package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class DropShardStatement implements Statement {
    private final long id;

    public DropShardStatement(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DROP SHARD " + id;
    }
}
