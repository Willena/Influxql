package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowTagValuesStatementTest extends GenericStatementTest<ShowTagValuesStatement> {
    private static final List<TestBody<ShowTagValuesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowTagValuesStatement>()
                    .withStatement(
                            new ShowTagValuesStatement.Builder()
                    )
                    .withShouldFail(true)
                    .withExpectedSql("missing fields")
                    .build(),
            new TestBody.Builder<ShowTagValuesStatement>()
                    .withStatement(
                            new ShowTagValuesStatement.Builder()
                                    .withOperator(Operator.EQ)
                                    .withTagKeyExpr(StringLiteral.of("kkk"))
                    )
                    .withExpectedSql("SHOW TAG VALUES WITH KEY = kkk")
                    .build(),
            new TestBody.Builder<ShowTagValuesStatement>()
                    .withStatement(
                            new ShowTagValuesStatement.Builder()
                                    .withSources(new Measurement.Builder().withName("name").build())
                                    .withSortFields(new SortField.Builder().withAscending(false).withName("field").build())
                                    .withDatabase("db")
                                    .withCondition(new BinaryExpression(VarRef.of("kkk"), StringLiteral.of("jkj"), Operator.NEQ))
                                    .withLimit(1)
                                    .withOffset(10)
                                    .withOperator(Operator.EQ)
                                    .withTagKeyExpr(StringLiteral.of("kkk"))
                    )
                    .withExpectedSql("SHOW TAG VALUES ON db FROM \"name\" WITH KEY = kkk WHERE kkk != 'jkj' ORDER BY field DESC LIMIT 1 OFFSET 10")
                    .build()

    );
}