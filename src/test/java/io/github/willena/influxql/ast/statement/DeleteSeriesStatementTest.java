package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;

import java.util.List;
import java.util.regex.Pattern;

class DeleteSeriesStatementTest extends GenericStatementTest<DeleteSeriesStatement> {
    private static final List<TestBody<DeleteSeriesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(Measurement.measurement("M"))
                    )
                    .withExpectedSql("DELETE FROM M")
                    .build(),
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(new Measurement.Builder()
                                            .withName("M")
                                            .withDatabase("db")
                                            .withRetentionPolicy("policy1")
                                            .build()
                                    )
                    )
                    .withExpectedSql("DELETE FROM db.policy1.M")
                    .build(),

            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .from(new Measurement.Builder()
                                            .withRegex(Pattern.compile(".*"))
                                            .build()
                                    )
                                    .where(BinaryExpression.eq(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2")
                                    ))
                    )
                    .withExpectedSql("DELETE FROM /.*/ WHERE cpu = 'cpu2'")
                    .build(),

            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .where(BinaryExpression.eq(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2")
                                    ))
                    )
                    .withExpectedSql("DELETE WHERE cpu = 'cpu2'")
                    .build()
    );
}