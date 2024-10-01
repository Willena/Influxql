package io.github.willena.influxql.ast.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VarRefTest {

    @Test
    void of() {
        assertEquals("tutu", VarRef.of("tutu").toString());
    }

    @Test
    void ofType() {
        assertEquals("tutu::string", VarRef.of("tutu", DataType.STRING).toString());
    }
}