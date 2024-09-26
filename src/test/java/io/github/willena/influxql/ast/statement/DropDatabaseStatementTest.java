package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropDatabaseStatementTest extends GenericStatementTest<DropDatabaseStatement> {

    private static final List<TestBody<DropDatabaseStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropDatabaseStatement>()
                    .withStatement(
                            new DropDatabaseStatement.Builder()
                                    .withDatabase("database")
                    )
                    .withExpectedSql("DROP DATABASE \"database\"")
                    .build()
    );
}