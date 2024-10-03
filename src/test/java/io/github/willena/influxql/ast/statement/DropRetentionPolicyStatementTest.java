package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropRetentionPolicyStatementTest extends GenericStatementTest<DropRetentionPolicyStatement> {
    private static final List<TestBody<DropRetentionPolicyStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropRetentionPolicyStatement>()
                    .withStatement(
                            new DropRetentionPolicyStatement.Builder()
                                    .on("database")
                                    .retentionPolicy("RetentionPolicy")
                    )
                    .withExpectedSql("DROP RETENTION POLICY RetentionPolicy ON \"database\"")
                    .build()
    );

}