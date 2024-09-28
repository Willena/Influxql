package io.github.willena.influxql.ast.statement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowUsersStatementTest {
    @Test
    void test() {
        assertEquals("SHOW USERS", new ShowUsersStatement().toString());
    }
}