package io.github.willena.influxql.ast.statement;

import java.util.List;

class CreateUserStatementTest extends GenericStatementTest<CreateUserStatement> {
    public static final List<TestBody<CreateUserStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<CreateUserStatement>()
                    .withStatement(
                            new CreateUserStatement.Builder()
                                    .username("UserName")
                                    .password("password!&é!$^ù")
                    )
                    .withExpectedSql("CREATE USER UserName WITH PASSWORD 'password!&é!$^ù'")
                    .build(),
            new TestBody.Builder<CreateUserStatement>()
                    .withStatement(
                            new CreateUserStatement.Builder()
                                    .username("UserName")
                                    .password("password!&é!$^ù")
                                    .admin(true)
                    )
                    .withExpectedSql("CREATE USER UserName WITH PASSWORD 'password!&é!$^ù' WITH ALL PRIVILEGES")
                    .build()
    );
}