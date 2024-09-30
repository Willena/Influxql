package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowSeriesStatementTest extends GenericStatementTest<ShowSeriesStatement> {
    private static final List<TestBody<ShowSeriesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowSeriesStatement>()
                    .withStatement(
                            new ShowSeriesStatement.Builder()
                    )
                    .withExpectedSql("SHOW SERIES")
                    .build(),
            new TestBody.Builder<ShowSeriesStatement>()
                    .withStatement(
                            new ShowSeriesStatement.Builder()
                                    .withSources(new Measurement.Builder().withName("name").build())
                                    .withDatabase("db")
                                    .withSortFields(new SortField.Builder().withName("Field").withAscending(false).build())
                                    .withConditions(new BinaryExpression(VarRef.of("Field"), IntegerLiteral.of(110), Operator.EQ))
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW SERIES ON db FROM \"name\" WHERE \"Field\" = 110 ORDER BY Field DESC LIMIT 1 OFFSET 10")
                    .build()

    );
}