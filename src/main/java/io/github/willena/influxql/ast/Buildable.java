package io.github.willena.influxql.ast;

/**
 * A Builder interface
 *
 * @param <T> the built type
 */
public interface Buildable<T> {
    T build();
}
