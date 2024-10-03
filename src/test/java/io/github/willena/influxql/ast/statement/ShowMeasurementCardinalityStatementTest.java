package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
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
                                    .exact()
                                    .from(new Measurement.Builder().withName("name").build())
                                    .on("database")
                                    .where(new BinaryExpression(VarRef.of("Field"), StringLiteral.of("value"), Operator.EQ))
                                    .groupBy(new Dimension(VarRef.of("TAG")))
                                    .limit(1)
                                    .offset(10)
                    )
                    .withExpectedSql("SHOW MEASUREMENT EXACT CARDINALITY ON \"database\" FROM \"name\" WHERE \"Field\" = 'value' GROUP BY \"TAG\" LIMIT 1 OFFSET 10")
                    .build()
    );
}