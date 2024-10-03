package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

public class Call implements Expression {
    private final String name;
    private final StringJoiningList<Expression> args;

    private Call(Builder builder) {
        name = builder.name;
        args = builder.args;
        ensureDefined("name", name);
        ensureDefined("args", args);
    }

    public String getName() {
        return name;
    }

    public StringJoiningList<Expression> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return name + "(" + args + ")";
    }

    public static final class Builder {
        private String name;
        private StringJoiningList<Expression> args;

        public Builder() {
        }

        public Builder function(String name) {
            this.name = name;
            return this;
        }

        public Builder withArguments(StringJoiningList<Expression> args) {
            this.args = args;
            return this;
        }

        public Builder withArguments(Expression... args) {
            if (this.args == null) {
                this.args = new StringJoiningList<>();
            }
            this.args.addAll(List.of(args));
            return this;
        }

        public Call build() {
            return new Call(this);
        }
    }
}
