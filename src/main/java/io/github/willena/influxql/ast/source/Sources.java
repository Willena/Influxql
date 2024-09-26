package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.Arrays;
import java.util.List;

public class Sources extends StringJoiningList<Source> {
    public Sources(List<Source> sources) {
        super(sources);
    }

    public Sources() {
        super();
    }

    public static Sources of(Source... sources) {
        return new Sources(Arrays.asList(sources));
    }
}
