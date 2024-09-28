package io.github.willena.influxql.ast.statement;

import java.util.List;

class ShowRetentionPoliciesStatementTest extends GenericStatementTest<ShowRetentionPoliciesStatement> {
    private static final List<TestBody<ShowRetentionPoliciesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowRetentionPoliciesStatement>()
                    .withStatement(
                            new ShowRetentionPoliciesStatement.Builder()
                    )
                    .withExpectedSql("SHOW RETENTION POLICIES")
                    .build(),
            new TestBody.Builder<ShowRetentionPoliciesStatement>()
                    .withStatement(
                            new ShowRetentionPoliciesStatement.Builder()
                                    .withDatabase("db")
                    )
                    .withExpectedSql("SHOW RETENTION POLICIES ON db")
                    .build()
    );

}