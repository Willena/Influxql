package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.TimestampLiteral;
import io.github.willena.influxql.ast.source.Measurement;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Pattern;

class DeleteStatementTest extends GenericStatementTest<DeleteStatement> {
    private static final List<TestBody<DeleteStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .from(Measurement.measurement("cpu"))
                    )
                    .withExpectedSql("DELETE FROM cpu")
                    .build(),
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .from(Measurement.measurement("cpu"))
                                    .where(BinaryExpression.lt(
                                            VarRef.of("time"),
                                            TimestampLiteral.of(ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant())
                                    ))
                    )
                    .withExpectedSql("DELETE FROM cpu WHERE time < '2000-01-01T00:00:00Z'")
                    .build(),
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .from(Measurement.measurements(Pattern.compile(".*")))
                                    .where(BinaryExpression.lt(
                                            VarRef.of("time"),
                                            TimestampLiteral.of(ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant())
                                    ))
                    )
                    .withExpectedSql("DELETE FROM /.*/ WHERE time < '2000-01-01T00:00:00Z'")
                    .build()
    );
}