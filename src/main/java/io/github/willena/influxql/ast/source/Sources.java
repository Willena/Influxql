package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.StringJoiningList;

import java.util.List;

public class Sources extends StringJoiningList<Source> {
    public Sources(List<Source> sources) {
        super(sources);
    }
}
