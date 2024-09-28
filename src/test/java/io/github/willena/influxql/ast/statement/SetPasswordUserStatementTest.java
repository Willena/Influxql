package io.github.willena.influxql.ast.statement;

import java.util.List;

class SetPasswordUserStatementTest extends GenericStatementTest<SetPasswordUserStatement> {
    private static final List<TestBody<SetPasswordUserStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<SetPasswordUserStatement>()
                    .withStatement(
                            new SetPasswordUserStatement.Builder()
                                    .withUsername("name")
                                    .withPassword("password")
                    )
                    .withExpectedSql("SET PASSWORD FOR \"name\" = 'password'")
                    .build(),
            new TestBody.Builder<SetPasswordUserStatement>()
                    .withStatement(
                            new SetPasswordUserStatement.Builder()
                                    .withUsername("name")
                                    .withPassword("password")
                                    .withRedacted(true)
                    )
                    .withExpectedSql("SET PASSWORD FOR \"name\" = [REDACTED]")
                    .build()
    );
}