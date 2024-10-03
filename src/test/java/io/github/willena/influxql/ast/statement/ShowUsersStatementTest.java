package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static io.github.willena.influxql.ast.statement.ShowUsersStatement.showUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowUsersStatementTest {
    @Test
    void test() {
        assertEquals("SHOW USERS", new ShowUsersStatement().toString());
        assertEquals("SHOW USERS", showUsers().toString());
    }
}