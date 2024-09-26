package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpr;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;
import java.util.regex.Pattern;

class DeleteSeriesStatementTest extends GenericStatementTest<DeleteSeriesStatement> {
    private static final List<TestBody<DeleteSeriesStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .withFrom(new Measurement.Builder().withName("M").build())
                    )
                    .withExpectedSql("DELETE FROM M")
                    .build(),
            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(

                            new DeleteSeriesStatement.Builder()
                                    .withFrom(new Measurement.Builder()
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
                                    .withFrom(new Measurement.Builder()
                                            .withRegex(Pattern.compile("/.*/"))
                                            .build()
                                    )
                                    .withWhere(new BinaryExpr(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2"),
                                            Operator.EQ
                                    ))
                    )
                    .withExpectedSql("DELETE FROM /.*/ WHERE cpu = 'cpu2'")
                    .build(),

            new TestBody.Builder<DeleteSeriesStatement>()
                    .withStatement(
                            new DeleteSeriesStatement.Builder()
                                    .withWhere(new BinaryExpr(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2"),
                                            Operator.EQ
                                    ))
                    )
                    .withExpectedSql("DELETE WHERE cpu = 'cpu2'")
                    .build()
    );
}