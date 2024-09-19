package io.github.willena.influxql.ast;

public interface Literal<T> extends Expr {
    T getValue();
}
