package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropUserStatementTest extends GenericStatementTest<DropUserStatement> {
    private static final List<TestBody<DropUserStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropUserStatement>()
                    .withStatement(
                            new DropUserStatement.Builder()
                                    .withUsername("Usr")
                    )
                    .withExpectedSql("DROP USER Usr")
                    .build()
    );
}