package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;

import java.util.List;

class ShowFieldKeysStatementTest extends GenericStatementTest<ShowFieldKeysStatement> {
    private static final List<TestBody<ShowFieldKeysStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowFieldKeysStatement>()
                    .withStatement(
                            new ShowFieldKeysStatement.Builder()
                    )
                    .withExpectedSql("SHOW FIELD KEYS")
                    .build(),
            new TestBody.Builder<ShowFieldKeysStatement>()
                    .withStatement(
                            new ShowFieldKeysStatement.Builder()
                                    .from(new Measurement.Builder().withName("name").build())
                                    .orderBy(new SortField.Builder().field("field").ascending(false).build())
                                    .on("db")
                                    .limit(1)
                                    .offset(10)
                    )
                    .withExpectedSql("SHOW FIELD KEYS ON db FROM \"name\" ORDER BY \"field\" DESC LIMIT 1 OFFSET 10")
                    .build()
    );
}