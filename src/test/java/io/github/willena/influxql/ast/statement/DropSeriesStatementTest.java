package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpr;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;
import java.util.regex.Pattern;

class DropSeriesStatementTest extends GenericStatementTest<DropSeriesStatement> {
    private static final List<GenericStatementTest.TestBody<DropSeriesStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                    .withStatement(
                            new DropSeriesStatement.Builder()
                                    .withFrom(new Measurement.Builder().withName("M").build())
                    )
                    .withExpectedSql("DROP SERIES FROM M")
                    .build(),
            new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                    .withStatement(

                            new DropSeriesStatement.Builder()
                                    .withFrom(new Measurement.Builder()
                                            .withName("M")
                                            .withDatabase("db")
                                            .withRetentionPolicy("policy1")
                                            .build()
                                    )

                    )
                    .withExpectedSql("DROP SERIES FROM db.policy1.M")
                    .build(),

            new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                    .withStatement(
                            new DropSeriesStatement.Builder()
                                    .withFrom(new Measurement.Builder()
                                            .withRegex(Pattern.compile(".*"))
                                            .build()
                                    )
                                    .withWhere(new BinaryExpr(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2"),
                                            Operator.EQ
                                    ))
                    )
                    .withExpectedSql("DROP SERIES FROM /.*/ WHERE cpu = 'cpu2'")
                    .build(),

            new GenericStatementTest.TestBody.Builder<DropSeriesStatement>()
                    .withStatement(
                            new DropSeriesStatement.Builder()
                                    .withWhere(new BinaryExpr(
                                            VarRef.of("cpu"),
                                            StringLiteral.of("cpu2"),
                                            Operator.EQ
                                    ))
                    )
                    .withExpectedSql("DROP SERIES WHERE cpu = 'cpu2'")
                    .build()
    );
}