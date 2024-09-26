package io.github.willena.influxql.ast;

public interface Buildable<T> {
    T build();
}
