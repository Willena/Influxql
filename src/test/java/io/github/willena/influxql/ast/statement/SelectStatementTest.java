package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.*;
import io.github.willena.influxql.ast.expr.literal.*;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.source.SubQuery;
import io.github.willena.influxql.ast.source.Target;
import io.github.willena.influxql.ast.token.FillOption;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.TimeZone;

class SelectStatementTest extends GenericStatementTest<SelectStatement> {
    private static final List<TestBody<SelectStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("FieldA"))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT FieldA FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("FieldA↓"))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT \"FieldA↓\" FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("Fiel'dA"))
                                    .from(Measurement.measurement("MyMea'surement"))
                    )
                    .withExpectedSql("SELECT \"Fiel'dA\" FROM \"MyMea'surement\"")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("Fiel\"dA"))
                                    .from(Measurement.measurement("MyMea\"surement"))
                    )
                    .withExpectedSql("SELECT \"Fiel\\\"dA\" FROM \"MyMea\\\"surement\"")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(RegexLiteral.of(".*")))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT /.*/ FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("FieldA"))
                                    .from(Measurement.measurements(".*"))
                    )
                    .withExpectedSql("SELECT FieldA FROM /.*/")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(StringLiteral.of("FieldA"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT MEAN('FieldA') FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(
                                                    VarRef.of("COL"),
                                                    IntegerLiteral.of(12),
                                                    TimeLiteral.of(Instant.parse("2015-08-18T00:00:00Z"))
                                            )
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT MEAN(COL, 12, '2015-08-18T00:00:00Z') FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(
                                                    VarRef.of("CO'L"),
                                                    IntegerLiteral.of(12),
                                                    TimeLiteral.of(Instant.parse("2015-08-18T00:00:00Z"))
                                            )
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT MEAN(\"CO'L\", 12, '2015-08-18T00:00:00Z') FROM MyMeasurement")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(VarRef.of("COL"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .fill(FillOption.NUMBER, IntegerLiteral.of(123))
                    )
                    .withExpectedSql("SELECT MEAN(COL) FROM MyMeasurement fill(123)")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(VarRef.of("COL"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .fill(FillOption.PREVIOUS)
                    )
                    .withExpectedSql("SELECT MEAN(COL) FROM MyMeasurement fill(previous)")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(VarRef.of("COL"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .fill(FillOption.LINEAR)
                    )
                    .withExpectedSql("SELECT MEAN(COL) FROM MyMeasurement fill(linear)")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(VarRef.of("COL"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .fill(FillOption.NULL)
                    )
                    .withExpectedSql("SELECT MEAN(COL) FROM MyMeasurement fill(null)")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(new Call.Builder()
                                            .function("MEAN")
                                            .withArguments(VarRef.of("COL"))
                                            .build()))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .groupBy(Dimension.field("COLA"), Dimension.sampledBy(Duration.ofHours(1)))
                                    .fill(FillOption.NUMBER, IntegerLiteral.of(123))
                    )
                    .withExpectedSql("SELECT MEAN(COL) FROM MyMeasurement GROUP BY COLA, time(1h) fill(123)")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .offset(125)
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement OFFSET 125")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .sOffset(125)
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement SOFFSET 125")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .limit(125)
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement LIMIT 125")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .sLimit(125)
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement SLIMIT 125")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(BinaryExpression.add(VarRef.of("FieldC"), IntegerLiteral.of(51))))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .sLimit(125)
                    )
                    .withExpectedSql("SELECT FieldC + 51 FROM MyMeasurement SLIMIT 125")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .orderBy(SortField.asc("time"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement ORDER BY time ASC")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .orderBy(SortField.desc("time"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement ORDER BY time DESC")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .orderBy(SortField.desc("time"))
                                    .timezone(TimeZone.getTimeZone("Europe/Paris"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement ORDER BY time DESC TZ('Europe/Paris')")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .where(
                                            BinaryExpression.and(
                                                    BinaryExpression.eq(VarRef.of("toto"), IntegerLiteral.of(56)),
                                                    BinaryExpression.lt(VarRef.of("time"), TimeLiteral.of(Instant.parse("2020-05-16T00:00:00.000000153Z")))
                                            )
                                    )
                                    .orderBy(SortField.desc("time"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement WHERE toto = 56 AND time < '2020-05-16T00:00:00.000000153Z' ORDER BY time DESC")
                    .build(),
            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .where(
                                            Parentheses.of(
                                                    BinaryExpression.and(
                                                            BinaryExpression.or(
                                                                    BinaryExpression.and(
                                                                            BinaryExpression.eq(VarRef.of("toto"), IntegerLiteral.of(56)),
                                                                            BinaryExpression.lt(VarRef.of("time"), TimeLiteral.of(Instant.parse("2020-05-16T00:00:00.000000153Z")))
                                                                    ),
                                                                    Parentheses.of(
                                                                            BinaryExpression.and(
                                                                                    BinaryExpression.eq(
                                                                                            StringLiteral.of("tutututu"),
                                                                                            IntegerLiteral.of(12)
                                                                                    ),
                                                                                    BinaryExpression.eq(
                                                                                            StringLiteral.of("aaa"),
                                                                                            StringLiteral.of("A")
                                                                                    )
                                                                            )
                                                                    )
                                                            ),
                                                            Parentheses.of(
                                                                    BinaryExpression.and(
                                                                            BinaryExpression.gte(VarRef.of("value"), IntegerLiteral.of(323)),
                                                                            BinaryExpression.and(
                                                                                    BinaryExpression.eq(
                                                                                            VarRef.of("computer"),
                                                                                            StringLiteral.of("toto")
                                                                                    ),
                                                                                    BinaryExpression.gt(
                                                                                            VarRef.of("ptio"),
                                                                                            Parentheses.of(
                                                                                                    BinaryExpression.add(
                                                                                                            IntegerLiteral.of(15),
                                                                                                            NumberLiteral.of(35.3)
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                                    .orderBy(SortField.desc("time"))
                    )
                    .withExpectedSql("SELECT * FROM MyMeasurement WHERE (toto = 56 AND time < '2020-05-16T00:00:00.000000153Z' OR ('tutututu' = 12 AND 'aaa' = 'A') AND (value >= 323 AND computer = 'toto' AND ptio > (15 + 35.3))) ORDER BY time DESC")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(new Field.Builder()
                                            .withExpr(
                                                    BinaryExpression.add(VarRef.of("FieldC"), IntegerLiteral.of(12))
                                            )
                                            .withAlias("math")
                                            .build())
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT FieldC + 12 AS math FROM MyMeasurement")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("field1"))
                                    .select(Field.field("field2"))
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT field1, field2 FROM MyMeasurement")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("field1"))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .from(Measurement.measurement("From2"))
                    )
                    .withExpectedSql("SELECT field1 FROM MyMeasurement, From2")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("field1"))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .from(Measurement.measurements(".*"))
                    )
                    .withExpectedSql("SELECT field1 FROM MyMeasurement, /.*/")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("field1"))
                                    .from(Measurement.measurement("MyMeasurement"))
                                    .groupBy(Dimension.field("f1"))
                                    .groupBy(Dimension.sampledBy(Duration.ofHours(1)))
                    )
                    .withExpectedSql("SELECT field1 FROM MyMeasurement GROUP BY f1, time(1h)")
                    .build(),


            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(SubQuery.of(b -> b
                                            .select(Field.field("F2"), Field.field("F3"))
                                            .from(Measurement.measurement("Table"))
                                    ))
                    )
                    .withExpectedSql("SELECT * FROM (SELECT F2, F3 FROM Table)")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("table2"))
                                    .from(SubQuery.of(b -> b
                                            .select(Field.field("F2"), Field.field("F3"))
                                            .from(Measurement.measurement("Table"))
                                    ))
                    )
                    .withExpectedSql("SELECT * FROM table2, (SELECT F2, F3 FROM Table)")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.wildcard())
                                    .from(Measurement.measurement("table2"))
                                    .where(
                                            BinaryExpression.or(
                                                    BinaryExpression.eq(
                                                            VarRef.of("A"),
                                                            IntegerLiteral.of(165)
                                                    ),
                                                    BinaryExpression.lte(
                                                            VarRef.of("time"),
                                                            TimeLiteral.of(Instant.parse("1970-01-01T00:00:00Z"))
                                                    )
                                            )
                                    )
                    )
                    .withExpectedSql("SELECT * FROM table2 WHERE A = 165 OR time <= '1970-01-01T00:00:00Z'")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.of(Parentheses.of(BinaryExpression.add(VarRef.of("A"), IntegerLiteral.of(32)))))
                                    .select(Field.of(Parentheses.of(BinaryExpression.div(VarRef.of("TU"), NumberLiteral.of(3.3)))))
                                    .select(Field.of(Parentheses.of(BinaryExpression.sub(VarRef.of("MINUS"), UnsignedLiteral.of(32)))))
                                    .select(Field.of(Parentheses.of(BinaryExpression.mul(VarRef.of("MINUS"), IntegerLiteral.of(1)))))
                                    .select(Field.of(Parentheses.of(BinaryExpression.mod(VarRef.of("MINUS"), IntegerLiteral.of(1)))))
                                    .from(Measurement.measurement("table2"))
                                    .where(
                                            Parentheses.of(
                                                    BinaryExpression.or(
                                                            BinaryExpression.neq(
                                                                    VarRef.of("A"),
                                                                    IntegerLiteral.of(165)
                                                            ),
                                                            BinaryExpression.lte(
                                                                    VarRef.of("time"),
                                                                    TimeLiteral.of(Instant.parse("1970-01-01T00:00:00Z"))
                                                            )
                                                    )
                                            )
                                    )
                    )
                    .withExpectedSql("SELECT (A + 32), (TU / 3.3), (MINUS - 32), (MINUS * 1), (MINUS % 1) FROM table2 WHERE (A != 165 OR time <= '1970-01-01T00:00:00Z')")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(Field.field("A"))
                                    .from(Measurement.measurement("table2"))
                                    .into(
                                            Target.of(new Measurement.Builder()
                                                    .withDatabase("MyDB")
                                                    .withRetentionPolicy("RP")
                                                    .withName("Measurement")
                                                    .build())
                                    )
                    )
                    .withExpectedSql("SELECT A INTO MyDB.RP.\"Measurement\" FROM table2")
                    .build(),

            new TestBody.Builder<SelectStatement>()
                    .withStatement(
                            new SelectStatement.Builder()
                                    .select(new Field.Builder().withExpr(VarRef.of("tutu")).withAlias("alias").build())
                                    .from(Measurement.measurement("MyMeasurement"))
                    )
                    .withExpectedSql("SELECT tutu AS alias FROM MyMeasurement")
                    .build()
    );

}