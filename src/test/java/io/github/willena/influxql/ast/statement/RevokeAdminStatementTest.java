package io.github.willena.influxql.ast.statement;

import java.util.List;

class RevokeAdminStatementTest extends GenericStatementTest<RevokeAdminStatement> {
    private static final List<TestBody<RevokeAdminStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<RevokeAdminStatement>()
                    .withStatement(
                            new RevokeAdminStatement.Builder()
                                    .withUsername("Usr")
                    )
                    .withExpectedSql("REVOKE ALL PRIVILEGES FROM Usr")
                    .build()
    );
}