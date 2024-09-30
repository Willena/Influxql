package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowSeriesCardinalityStatementTest extends GenericStatementTest<ShowSeriesCardinalityStatement> {
    private static final List<TestBody<ShowSeriesCardinalityStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowSeriesCardinalityStatement>()
                    .withStatement(
                            new ShowSeriesCardinalityStatement.Builder()
                    )
                    .withExpectedSql("SHOW SERIES CARDINALITY")
                    .build(),
            new TestBody.Builder<ShowSeriesCardinalityStatement>()
                    .withStatement(
                            new ShowSeriesCardinalityStatement.Builder()
                                    .withExact(true)
                                    .withSources(new Measurement.Builder().withName("name").build())
                                    .withDatabase("db")
                                    .withCondition(new BinaryExpression(VarRef.of("e"), StringLiteral.of("20"), Operator.EQ))
                                    .withDimensions(new Dimension(VarRef.of("dimension")))
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW SERIES EXACT CARDINALITY ON db FROM \"name\" WHERE e = '20' GROUP BY dimension LIMIT 1 OFFSET 10")
                    .build()
    );
}