package io.github.willena.influxql.ast;

public interface Literal<T> extends Expression {
    T getValue();
}
