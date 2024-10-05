package io.github.willena.influxql.ast;

/**
 * A basic node in the AST
 */
public interface Node {
    /**
     * Convert the current Node to an influxql String that the parser must be able to read.
     *
     * @return an InfluxQL string
     */
    String toString();
}
