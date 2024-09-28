package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpr;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ShowTagKeysStatementTest extends GenericStatementTest<ShowTagKeysStatement> {
    private static final List<TestBody<ShowTagKeysStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowTagKeysStatement>()
                    .withStatement(
                            new ShowTagKeysStatement.Builder()
                    )
                    .withExpectedSql("SHOW TAG KEYS")
                    .build(),
            new TestBody.Builder<ShowTagKeysStatement>()
                    .withStatement(
                            new ShowTagKeysStatement.Builder()
                                    .withSources(new Measurement.Builder().withName("name").build())
                                    .withDatabase("db")
                                    .withSortFields(new SortField.Builder().withName("name").build())
                                    .withCondition(new BinaryExpr(VarRef.of("oooo"), IntegerLiteral.of(10), Operator.EQ))
                                    .withLimit(1)
                                    .withSLimit(2)
                                    .withOffset(10)
                                    .withSOffset(11)
                    )
                    .withExpectedSql("SHOW TAG KEYS ON db FROM \"name\" WHERE oooo = 10 ORDER BY name DESC LIMIT 1 OFFSET 10 SLIMIT 2 SOFFSET 11")
                    .build()
    );
}