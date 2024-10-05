/*
 * InfluxQL Java package
 * Copyright 2024 Guillaume VILLENA also known as "Willena" on GitHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.willena.influxql.ast.expr;

import io.github.willena.influxql.ast.Expression;
import io.github.willena.influxql.ast.utils.StringJoiningList;

import java.util.List;

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

/**
 * Function / Call expression
 */
public class Call implements Expression {
    private final String name;
    private final StringJoiningList<Expression> args;

    private Call(Builder builder) {
        name = builder.name;
        args = builder.args;
        ensureDefined("name", name);
        ensureDefined("args", args);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets args.
     *
     * @return the args
     */
    public StringJoiningList<Expression> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return name + "(" + args + ")";
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private String name;
        private StringJoiningList<Expression> args;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Function name builder.
         *
         * @param name the name
         * @return the builder
         */
        public Builder function(String name) {
            this.name = name;
            return this;
        }

        /**
         * With arguments builder.
         *
         * @param args the args list
         * @return the builder
         */
        public Builder withArguments(StringJoiningList<Expression> args) {
            this.args = args;
            return this;
        }

        /**
         * With arguments builder.
         *
         * @param args the args
         * @return the builder
         */
        public Builder withArguments(Expression... args) {
            if (this.args == null) {
                this.args = new StringJoiningList<>();
            }
            this.args.addAll(List.of(args));
            return this;
        }

        /**
         * Build call.
         *
         * @return the call
         */
        public Call build() {
            return new Call(this);
        }
    }
}
