package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.token.SubscriptionMode;

import java.util.List;

class CreateSubscriptionStatementTest extends GenericStatementTest<CreateSubscriptionStatement> {
    public static final List<TestBody> TEST_BODIES = List.of(
            new TestBody.Builder<CreateSubscriptionStatement>()
                    .withStatement(
                            new CreateSubscriptionStatement.Builder()
                                    .withName("Name")
                                    .withDatabase("db")
                                    .withRetentionPolicyName("policy")
                                    .withMode(SubscriptionMode.ALL)
                                    .withDestinations("host1")
                    )
                    .withExpectedSql("CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ALL 'host1'")
                    .build(),
            new TestBody.Builder<CreateSubscriptionStatement>()
                    .withStatement(
                            new CreateSubscriptionStatement.Builder()
                                    .withName("Name")
                                    .withDatabase("db")
                                    .withRetentionPolicyName("policy")
                                    .withMode(SubscriptionMode.ANY)
                                    .withDestinations("host1")
                    )
                    .withExpectedSql("CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ANY 'host1'")
                    .build(),
            new TestBody.Builder<CreateSubscriptionStatement>()
                    .withStatement(
                            new CreateSubscriptionStatement.Builder()
                                    .withName("Name")
                                    .withDatabase("db")
                                    .withRetentionPolicyName("policy")
                                    .withMode(SubscriptionMode.ANY)
                                    .withDestinations("host1", "host2")
                    )
                    .withExpectedSql("CREATE SUBSCRIPTION \"Name\" ON db.\"policy\" DESTINATIONS ANY 'host1', 'host2'")
                    .build()
    );
}