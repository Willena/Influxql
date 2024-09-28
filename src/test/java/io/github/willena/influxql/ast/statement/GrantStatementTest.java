package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.token.Privilege;

import java.util.List;

class GrantStatementTest extends GenericStatementTest<GrantStatement> {
    private static final List<TestBody<GrantStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.READ_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("GRANT READ ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.WRITE_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("GRANT WRITE ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.ALL_PRIVILEGES)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("GRANT ALL PRIVILEGES ON db TO Usr")
                    .build(),
            new TestBody.Builder<GrantStatement>()
                    .withStatement(
                            new GrantStatement.Builder()
                                    .withUsername("Usr")
                                    .withPrivilege(Privilege.NO_PRIVILEGE)
                                    .withDatabase("db")
                    )
                    .withExpectedSql("GRANT NO PRIVILEGES ON db TO Usr")
                    .build()
    );
}