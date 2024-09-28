package io.github.willena.influxql.ast.statement;

import java.util.List;

class GrantAdminStatementTest extends GenericStatementTest<GrantAdminStatement> {
    private static final List<TestBody<GrantAdminStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<GrantAdminStatement>()
                    .withStatement(
                            new GrantAdminStatement.Builder()
                                    .withUsername("Usr")
                    )
                    .withExpectedSql("GRANT ALL PRIVILEGES TO Usr")
                    .build()
    );
}