package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.TimeLiteral;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Pattern;

class DeleteStatementTest extends GenericStatementTest<DeleteStatement> {
    private static final List<TestBody<DeleteStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .withFrom(new Measurement.Builder().withName("cpu").build())
                    )
                    .withExpectedSql("DELETE FROM cpu")
                    .build(),
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .withFrom(new Measurement.Builder().withName("cpu").build())
                                    .withWhere(new BinaryExpression(
                                            VarRef.of("time"),
                                            TimeLiteral.of(ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant()),
                                            Operator.LT
                                    ))
                    )
                    .withExpectedSql("DELETE FROM cpu WHERE time < '2000-01-01T00:00:00Z'")
                    .build(),
            new TestBody.Builder<DeleteStatement>()
                    .withStatement(
                            new DeleteStatement.Builder()
                                    .withFrom(new Measurement.Builder().withRegex(Pattern.compile(".*")).build())
                                    .withWhere(new BinaryExpression(
                                            VarRef.of("time"),
                                            TimeLiteral.of(ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant()),
                                            Operator.LT
                                    ))
                    )
                    .withExpectedSql("DELETE FROM /.*/ WHERE time < '2000-01-01T00:00:00Z'")
                    .build()
    );
}