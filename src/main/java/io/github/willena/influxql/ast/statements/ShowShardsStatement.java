package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class ShowShardsStatement implements Statement {
    public ShowShardsStatement() {
    }

    @Override
    public String toString() {
        return "SHOW SHARDS";
    }
}
