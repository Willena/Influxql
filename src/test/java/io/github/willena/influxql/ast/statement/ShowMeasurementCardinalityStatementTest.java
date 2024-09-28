package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpr;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowMeasurementCardinalityStatementTest extends GenericStatementTest<ShowMeasurementCardinalityStatement> {
    private static final List<TestBody<ShowMeasurementCardinalityStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowMeasurementCardinalityStatement>()
                    .withStatement(
                            new ShowMeasurementCardinalityStatement.Builder()

                    )
                    .withExpectedSql("SHOW MEASUREMENT CARDINALITY")
                    .build(),
            new TestBody.Builder<ShowMeasurementCardinalityStatement>()
                    .withStatement(
                            new ShowMeasurementCardinalityStatement.Builder()
                                    .withExact(true)
                                    .withFrom(new Measurement.Builder().withName("name").build())
                                    .withDatabase("database")
                                    .withWhere(new BinaryExpr(VarRef.of("Field"), StringLiteral.of("value"), Operator.EQ))
                                    .withGroupBy(new Dimension(VarRef.of("TAG")))
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW MEASUREMENT EXACT CARDINALITY ON \"database\" FROM \"name\" WHERE \"Field\" = 'value' GROUP BY \"TAG\" LIMIT 1 OFFSET 10")
                    .build()
    );
}