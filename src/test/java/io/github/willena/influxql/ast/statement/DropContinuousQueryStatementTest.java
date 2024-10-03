package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropContinuousQueryStatementTest extends GenericStatementTest<DropContinuousQueryStatement> {

    private static final List<TestBody<DropContinuousQueryStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropContinuousQueryStatement>()
                    .withStatement(
                            new DropContinuousQueryStatement.Builder()
                                    .query("QueryName")
                                    .on("database")
                    )
                    .withExpectedSql("DROP CONTINUOUS QUERY QueryName ON \"database\"")
                    .build()
    );
}