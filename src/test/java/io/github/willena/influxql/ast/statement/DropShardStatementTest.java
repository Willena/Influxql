package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropShardStatementTest extends GenericStatementTest<DropShardStatement> {
    private static final List<TestBody<DropShardStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropShardStatement>()
                    .withStatement(
                            new DropShardStatement.Builder()
                                    .withShardId(1L)
                    )
                    .withExpectedSql("DROP SHARD 1")
                    .build(),
            new TestBody.Builder<DropShardStatement>()
                    .withStatement(new DropShardStatement.Builder())
                    .withShouldFail(true)
                    .withExpectedSql("Required shard ID")
                    .build()
    );
}