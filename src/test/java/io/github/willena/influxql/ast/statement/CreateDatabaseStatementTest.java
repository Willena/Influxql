package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.extra.RetentionPolicy;

import java.time.Duration;
import java.util.List;

class CreateDatabaseStatementTest extends GenericStatementTest<CreateDatabaseStatement> {

    public static final List<TestBody<CreateDatabaseStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<CreateDatabaseStatement>()
                    .withStatement(
                            new CreateDatabaseStatement.Builder()
                                    .name("DatabaseName")
                    )
                    .withExpectedSql("CREATE DATABASE DatabaseName")
                    .build(),

            new TestBody.Builder<CreateDatabaseStatement>()
                    .withStatement(new CreateDatabaseStatement.Builder()
                            .name("Datab'aseName")
                            .withRetentionPolicy(new RetentionPolicy.Builder()
                                    .replication(3)
                                    .duration(Duration.ofHours(1))
                                    .shardDuration(Duration.ofHours(2))
                                    .build()
                            )
                    )
                    .withExpectedSql("CREATE DATABASE \"Datab'aseName\" WITH DURATION 1h REPLICATION 3 SHARD DURATION 2h")
                    .build()
    );
}