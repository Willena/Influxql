package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.token.Privilege;

import java.util.List;

class GrantStatementTest extends GenericStatementTest<GrantStatement> {
    private static final List<TestBody<GrantStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.READ_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT READ ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.WRITE_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT WRITE ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.ALL_PRIVILEGES)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT ALL PRIVILEGES ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .to("Usr")
                                    .privilege(Privilege.NO_PRIVILEGE)
                                    .on("db")
                    )
                    .withExpectedSql("GRANT NO PRIVILEGES ON db TO Usr")
                    .build()
    );
}