package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.source.Measurement;

import java.time.Duration;
import java.util.List;

class CreateContinuousQueryStatementTest extends GenericStatementTest<CreateContinuousQueryStatement> {

    public static final List<GenericStatementTest.TestBody<CreateContinuousQueryStatement>> TEST_BODIES = List.of(
            new GenericStatementTest.TestBody.Builder<CreateContinuousQueryStatement>()
                    .withStatement(new CreateContinuousQueryStatement.Builder()
                            .name("ContinuousQuery")
                            .on("db")
                            .resampleEvery(Duration.ofHours(12))
                            .for_(Duration.ofHours(1))
                    )
                    .withShouldFail(true)
                    .withExpectedSql("CREATE CONTINUOUS QUERY ContinuousQuery ON db RESAMPLE EVERY 12h FOR 1h BEGIN SELECT  END")
                    .build(),

            new GenericStatementTest.TestBody.Builder<CreateContinuousQueryStatement>()
                    .withStatement(new CreateContinuousQueryStatement.Builder()
                            .name("ContinuousQuery")
                            .on("db")
                            .resampleEvery(Duration.ofHours(12))
                            .for_(Duration.ofHours(1))
                            .select(s -> s
                                    .select(Field.field("F1"))
                                    .from(Measurement.measurement("Toto"))
                                    .where(BinaryExpression.eq(
                                            VarRef.of("totoField"),
                                            IntegerLiteral.of(32L)
                                            )
                                    )
                            )
                    )
                    .withExpectedSql("CREATE CONTINUOUS QUERY ContinuousQuery ON db RESAMPLE EVERY 12h FOR 1h BEGIN SELECT F1 FROM Toto WHERE totoField = 32 END")
                    .build()
    );
}