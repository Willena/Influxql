package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class ShowDatabasesStatement implements Statement {
    public ShowDatabasesStatement() {
    }

    @Override
    public String toString() {
        return "SHOW DATABASES";
    }
}
