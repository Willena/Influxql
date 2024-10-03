package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class DropShardStatement implements Statement {
    private final Long id;


    private DropShardStatement(Builder builder) {
        id = builder.id;
        ensureDefined("id", id);
    }

    @Override
    public String toString() {
        return "DROP SHARD " + id;
    }

    public static final class Builder implements Buildable<DropShardStatement> {
        private Long id;

        public Builder() {
        }

        public Builder shardId(Long id) {
            this.id = id;
            return this;
        }

        public DropShardStatement build() {
            return new DropShardStatement(this);
        }
    }
}
