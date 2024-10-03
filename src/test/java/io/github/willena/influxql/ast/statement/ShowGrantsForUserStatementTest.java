package io.github.willena.influxql.ast.statement;

import java.util.List;

class ShowGrantsForUserStatementTest extends GenericStatementTest<ShowGrantsForUserStatement> {
    private static final List<TestBody<ShowGrantsForUserStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowGrantsForUserStatement>()
                    .withStatement(
                            new ShowGrantsForUserStatement.Builder()
                    )
                    .withExpectedSql("Missing name")
                    .withShouldFail(true)
                    .build(),
            new TestBody.Builder<ShowGrantsForUserStatement>()
                    .withStatement(
                            new ShowGrantsForUserStatement.Builder()
                                    .for_("username")
                    )
                    .withExpectedSql("SHOW GRANTS FOR username")
                    .build()
    );
}