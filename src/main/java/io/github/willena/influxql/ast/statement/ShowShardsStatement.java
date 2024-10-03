package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowShardsStatement implements Statement {
    public ShowShardsStatement() {
    }

    public static ShowShardsStatement showShards() {
        return new ShowShardsStatement();
    }

    @Override
    public String toString() {
        return "SHOW SHARDS";
    }
}
