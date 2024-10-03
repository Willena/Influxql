package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropSubscriptionStatementTest extends GenericStatementTest<DropSubscriptionStatement> {
    private static final List<TestBody<DropSubscriptionStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropSubscriptionStatement>()
                    .withStatement(
                            new DropSubscriptionStatement.Builder()
                                    .subscription("Subscription")
                                    .on("database")
                                    .retentionPolicy("policy")
                    )
                    .withExpectedSql("DROP SUBSCRIPTION \"Subscription\" ON \"database\".\"policy\"")
                    .build()
    );
}