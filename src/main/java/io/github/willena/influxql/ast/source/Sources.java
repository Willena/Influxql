package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.Arrays;
import java.util.List;

/**
 * List of {@link Source}
 */
public class Sources extends StringJoiningList<Source> {
    /**
     * Source list initialized with base
     *
     * @param sources initialization
     */
    public Sources(List<Source> sources) {
        super(sources);
    }

    /**
     * New uninitialized list
     */
    public Sources() {
        super();
    }

    /**
     * Helper method to create an initialized Source list
     *
     * @param sources sources
     * @return a list of {@link Source}
     */
    public static Sources of(Source... sources) {
        return new Sources(Arrays.asList(sources));
    }
}
