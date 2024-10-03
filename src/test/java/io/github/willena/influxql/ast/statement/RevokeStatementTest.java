package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.token.Privilege;

import java.util.List;

class RevokeStatementTest extends GenericStatementTest<RevokeStatement> {
    private static final List<GenericStatementTest.TestBody<RevokeStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .from("Usr")
                                    .privilege(Privilege.READ_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("REVOKE READ ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .from("Usr")
                                    .privilege(Privilege.WRITE_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("REVOKE WRITE ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .from("Usr")
                                    .privilege(Privilege.ALL_PRIVILEGES)
                                    .on("db")
                    )
                    .withExpectedSql("REVOKE ALL PRIVILEGES ON db FROM Usr")
                    .build(),
            new GenericStatementTest.TestBody.Builder<RevokeStatement>()
                    .withStatement(
                            new RevokeStatement.Builder()
                                    .from("Usr")
                                    .privilege(Privilege.NO_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("REVOKE NO PRIVILEGES ON db FROM Usr")
                    .build()
    );
}