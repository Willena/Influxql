package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.token.Privilege;

import java.util.List;

class RevokeStatementTest extends GenericStatementTest<RevokeStatement> {
    private static final List<GenericStatementTest.TestBody<RevokeStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.READ_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("REVOKE READ ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.WRITE_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("REVOKE WRITE ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.ALL_PRIVILEGES)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("REVOKE ALL PRIVILEGES ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.NO_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("REVOKE NO PRIVILEGES ON db FROM Usr")
                    .build()
    );
}