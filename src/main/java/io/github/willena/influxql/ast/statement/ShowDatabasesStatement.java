package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Statement;

public class ShowDatabasesStatement implements Statement {
    public ShowDatabasesStatement() {
    }

    public static ShowDatabasesStatement showDatabases() {
        return new ShowDatabasesStatement();
    }

    @Override
    public String toString() {
        return "SHOW DATABASES";
    }
}
