package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.StringLiteral;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;
import java.util.regex.Pattern;

class ShowMeasurementsStatementTest extends GenericStatementTest<ShowMeasurementsStatement> {
    private static final List<TestBody<ShowMeasurementsStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowMeasurementsStatement>()
                    .withStatement(
                            new ShowMeasurementsStatement.Builder()
                    )
                    .withExpectedSql("SHOW MEASUREMENTS")
                    .build(),
            new TestBody.Builder<ShowMeasurementsStatement>()
                    .withStatement(
                            new ShowMeasurementsStatement.Builder()
                                    .withFrom(new Measurement.Builder().withName("name").build())
                                    .withDatabase("db")
                                    .withSortFields(new SortField.Builder().withName("name").build())
                                    .withWhere(new BinaryExpression(VarRef.of("field"), StringLiteral.of("ok"), Operator.EQ))
                                    .withRetentionPolicy("policy")
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW MEASUREMENTS ON db.policy WITH MEASUREMENT = \"name\" WHERE \"field\" = 'ok' ORDER BY name DESC LIMIT 1 OFFSET 10")
                    .build(),
            new TestBody.Builder<ShowMeasurementsStatement>()
                    .withStatement(
                            new ShowMeasurementsStatement.Builder()
                                    .withFrom(new Measurement.Builder().withRegex(Pattern.compile(".*")).build())
                                    .withDatabase("db")
                                    .withSortFields(new SortField.Builder().withName("name").build())
                                    .withWhere(new BinaryExpression(VarRef.of("field"), StringLiteral.of("ok"), Operator.EQ))
                                    .withRetentionPolicy("*")
                                    .withLimit(1)
                                    .withOffset(10)
                    )
                    .withExpectedSql("SHOW MEASUREMENTS ON db.* WITH MEASUREMENT =~ /.*/ WHERE \"field\" = 'ok' ORDER BY name DESC LIMIT 1 OFFSET 10")
                    .build()
    );
}