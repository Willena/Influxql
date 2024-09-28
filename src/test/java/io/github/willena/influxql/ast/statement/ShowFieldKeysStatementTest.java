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
                                    .withSources(new Measurement.Builder().withName("name").build())
                                    .withSortFields(new SortField.Builder().withName("field").withAscending(false).build())
                                    .withDatabase("db")
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW FIELD KEYS ON db FROM \"name\" ORDER BY field DESC LIMIT 1 OFFSET 10")
                    .build()
    );
}