package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.expr.BinaryExpression;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.literal.IntegerLiteral;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.token.Operator;

import java.util.List;

class ExplainStatementTest extends GenericStatementTest<ExplainStatement> {
    private static final List<TestBody<ExplainStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ExplainStatement>()
                    .withStatement(
                            new ExplainStatement.Builder()
                                    .select(s -> s
                                            .select(new Field.Builder().withExpr(VarRef.of("F1")).build())
                                            .from(new Measurement.Builder().withName("Toto").build())
                                            .where(new BinaryExpression(VarRef.of("totoField"), IntegerLiteral.of(32), Operator.EQ))
                                    )
                    )
                    .withExpectedSql("EXPLAIN SELECT F1 FROM Toto WHERE totoField = 32")
                    .build(),
            new TestBody.Builder<ExplainStatement>()
                    .withStatement(
                            new ExplainStatement.Builder()
                                    .analyse()
                                    .verbose()
                                    .select(s -> s
                                            .select(new Field.Builder().withExpr(VarRef.of("F1")).build())
                                            .from(new Measurement.Builder().withName("Toto").build())
                                            .where(new BinaryExpression(VarRef.of("totoField"), IntegerLiteral.of(32), Operator.EQ))
                                    )
                    )
                    .withExpectedSql("EXPLAIN ANALYZE VERBOSE SELECT F1 FROM Toto WHERE totoField = 32")
                    .build()

    );
}