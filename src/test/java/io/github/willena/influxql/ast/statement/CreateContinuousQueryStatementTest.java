package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.time.Duration;
import java.util.List;

class CreateContinuousQueryStatementTest extends GenericStatementTest<CreateContinuousQueryStatement> {

    public static final List<GenericStatementTest.TestBody<CreateContinuousQueryStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<CreateContinuousQueryStatement>()
                    .withStatement(new CreateContinuousQueryStatement.Builder()
                            .withName("ContinuousQuery")
                            .withDatabase("db")
                            .withResampleEvery(Duration.ofHours(12))
                            .withResampleFor(Duration.ofHours(1))
                    )
                    .withShouldFail(true)
                    .withExpectedSql("CREATE CONTINUOUS QUERY ContinuousQuery ON db RESAMPLE EVERY 12h FOR 1h BEGIN SELECT  END")
                    .build(),

            new GenericStatementTest.TestBody.Builder<CreateContinuousQueryStatement>()
                    .withStatement(new CreateContinuousQueryStatement.Builder()
                            .withName("ContinuousQuery")
                            .withDatabase("db")
                            .withResampleEvery(Duration.ofHours(12))
                            .withResampleFor(Duration.ofHours(1))
                            .withSelectStatement(new SelectStatement.Builder()
                                    .withSelect(new Field.Builder().withExpr(VarRef.of("F1")).build())
                                    .withFrom(new Measurement.Builder().withName("Toto").build())
                                    .withWhere(new BinaryExpression(
                                                    new VarRef("totoField"),
                                            new IntegerLiteral(32L),
                                                    Operator.EQ
                                            )
                                    ).build()
                            )
                    )
                    .withExpectedSql("CREATE CONTINUOUS QUERY ContinuousQuery ON db RESAMPLE EVERY 12h FOR 1h BEGIN SELECT F1 FROM Toto WHERE (totoField = 32) END")
                    .build()
    );
}