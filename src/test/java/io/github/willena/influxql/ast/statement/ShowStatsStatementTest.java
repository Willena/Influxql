package io.github.willena.influxql.ast.statement;

import java.util.List;

class ShowStatsStatementTest extends GenericStatementTest<ShowStatsStatement> {
    private static final List<TestBody<ShowStatsStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowStatsStatement>()
                    .withStatement(
                            new ShowStatsStatement.Builder()
                    )
                    .withExpectedSql("SHOW STATS")
                    .build(),
            new TestBody.Builder<ShowStatsStatement>()
                    .withStatement(
                            new ShowStatsStatement.Builder()
                                    .withModule("mod")
                    )
                    .withExpectedSql("SHOW STATS FOR 'mod'")
                    .build()
    );

}