package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteString;

public class ShowDiagnosticsStatement implements Statement {
    private final String module;

    public ShowDiagnosticsStatement(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW DIAGNOSTICS");
        if (!module.isEmpty()) {
            buf.append(" FOR ");
            buf.append(QuoteString(module));
        }
        return buf.toString();
    }
}
