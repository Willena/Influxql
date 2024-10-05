package io.github.willena.influxql.ast;

/**
 * Any literal value in the AST
 *
 * @param <T> the value represented by the literal
 */
public interface Literal<T> extends Expression {
    /**
     * Current value of the literal
     *
     * @return the value
     */
    T getValue();
}
