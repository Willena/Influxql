package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.quoteString;

public class ShowStatsStatement implements Statement {
    private final String module;

    private ShowStatsStatement(Builder builder) {
        module = builder.module;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SHOW STATS");
        if (module != null && !module.isEmpty()) {
            buf.append(" FOR ");
            buf.append(quoteString(module));
        }
        return buf.toString();
    }

    public static final class Builder implements Buildable<ShowStatsStatement> {
        private String module;

        public Builder() {
        }

        public Builder for_(String module) {
            this.module = module;
            return this;
        }

        public ShowStatsStatement build() {
            return new ShowStatsStatement(this);
        }
    }
}
