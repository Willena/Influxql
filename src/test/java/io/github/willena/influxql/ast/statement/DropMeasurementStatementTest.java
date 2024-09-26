package io.github.willena.influxql.ast.statement;

import java.util.List;

class DropMeasurementStatementTest extends GenericStatementTest<DropMeasurementStatement> {
    private static final List<TestBody<DropMeasurementStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DropMeasurementStatement>()
                    .withStatement(
                            new DropMeasurementStatement.Builder()
                                    .withMeasurement("Measurement")
                    )
                    .withExpectedSql("DROP MEASUREMENT \"Measurement\"")
                    .build()
    );
}