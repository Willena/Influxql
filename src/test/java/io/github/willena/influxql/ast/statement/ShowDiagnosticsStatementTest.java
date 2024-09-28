package io.github.willena.influxql.ast.statement;

import java.util.List;

class ShowDiagnosticsStatementTest extends GenericStatementTest<ShowDiagnosticsStatement> {
    private static final List<TestBody<ShowDiagnosticsStatement>> TEST_BODIES = List.of(
            new TestBody.Builder<ShowDiagnosticsStatement>()
                    .withStatement(
                            new ShowDiagnosticsStatement.Builder()
                    )
                    .withExpectedSql("SHOW DIAGNOSTICS")
                    .build(),
            new TestBody.Builder<ShowDiagnosticsStatement>()
                    .withStatement(
                            new ShowDiagnosticsStatement.Builder()
                                    .withModule("mod")
                    )
                    .withExpectedSql("SHOW DIAGNOSTICS FOR 'mod'")
                    .build()
    );
}