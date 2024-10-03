package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowTagKeyCardinalityStatementTest extends GenericStatementTest<ShowTagKeyCardinalityStatement> {

    private static final List<TestBody<ShowTagKeyCardinalityStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowTagKeyCardinalityStatement>()
                    .withStatement(
                            new ShowTagKeyCardinalityStatement.Builder()
                    )
                    .withExpectedSql("SHOW TAG KEY CARDINALITY")
                    .build(),
            new TestBody.Builder<ShowTagKeyCardinalityStatement>()
                    .withStatement(
                            new ShowTagKeyCardinalityStatement.Builder()
                                    .exact()
                                    .on("db")
                                    .from(new Measurement.Builder().withName("meas").build())
                                    .groupBy(new Dimension(VarRef.of("field")))
                                    .where(new BinaryExpression(VarRef.of("field"), IntegerLiteral.of(0), Operator.EQ))
                                    .limit(1)
                                    .offset(10)
                    )
                    .withExpectedSql("SHOW TAG KEY EXACT CARDINALITY ON db FROM meas WHERE \"field\" = 0 GROUP BY \"field\" LIMIT 1 OFFSET 10")
                    .build()
    );
}