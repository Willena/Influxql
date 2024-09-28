package io.github.willena.influxql.ast.statement;

import java.util.List;

class KillQueryStatementTest extends GenericStatementTest<KillQueryStatement> {
    private static final List<TestBody<KillQueryStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<KillQueryStatement>()
                    .withStatement(
                            new KillQueryStatement.Builder()
                                    .withQueryId(1L)
                    )
                    .withExpectedSql("KILL QUERY 1")
                    .build(),
            new TestBody.Builder<KillQueryStatement>().withStatement(
                            new KillQueryStatement.Builder()
                                    .withQueryId(1L)
                                    .withHost("hostname")
                    )
                    .withExpectedSql("KILL QUERY 1 ON hostname")
                    .build()
    );
}