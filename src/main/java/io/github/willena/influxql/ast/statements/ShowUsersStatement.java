package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

public class ShowUsersStatement implements Statement {
    public ShowUsersStatement() {
    }

    @Override
    public String toString() {
        return "SHOW USERS";
    }
}
