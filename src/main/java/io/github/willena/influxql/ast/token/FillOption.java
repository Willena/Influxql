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

package io.github.willena.influxql.ast.token;

import io.github.willena.influxql.ast.Literal;
import io.github.willena.influxql.ast.expr.literal.NumericLiteral;

/** Fill options */
public enum FillOption {
    /** Null fil option */
    NULL {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(null)";
        }
    },
    /** None fill option */
    NONE {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(none)";
        }
    },
    /** Number fill option */
    NUMBER {
        @Override
        public String getSqlForValue(Literal<?> value) {
            if (!(value instanceof NumericLiteral)) {
                throw new IllegalArgumentException("Expected number but got " + value.getClass());
            }

            return "fill(" + value + ")";
        }
    },
    /** Previous fill option */
    PREVIOUS {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(previous)";
        }
    },
    /** Linear fill option */
    LINEAR {
        @Override
        public String getSqlForValue(Literal<?> value) {
            return "fill(linear)";
        }
    };

    /**
     * Get influxql for the current fill option given a literal as parameter
     *
     * @param value the parameter
     * @return the InfluxQL string
     */
    public abstract String getSqlForValue(Literal<?> value);
}
