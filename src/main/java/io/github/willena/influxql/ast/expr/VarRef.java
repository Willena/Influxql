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

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;
import static io.github.willena.influxql.ast.utils.Utils.quoteIdentifier;

import io.github.willena.influxql.ast.Expression;

/** Reference to variable of name of field / alias */
public class VarRef implements Expression {
    private final String value;
    private final DataType type;

    /**
     * Build a varRef from name and type
     *
     * @param type type
     * @param value value
     */
    public VarRef(String value, DataType type) {
        this.value = value;
        this.type = type;
        ensureDefined("value", value);
    }

    /**
     * Build a varRef from name
     *
     * @param expr name
     */
    public VarRef(String expr) {
        this(expr, null);
    }

    /**
     * Build a reference given its name and datatype
     *
     * @param expr ref name
     * @return a VarRef
     */
    public static VarRef of(String expr) {
        return new VarRef(expr);
    }

    /**
     * Build a reference given its name and datatype
     *
     * @param expr ref name
     * @param type type
     * @return a VarRef
     */
    public static VarRef of(String expr, DataType type) {
        return new VarRef(expr, type);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append(quoteIdentifier(value));
        if (type != null && type != DataType.UNKNOWN) {
            buf.append("::");
            buf.append(type);
        }
        return buf.toString();
    }
}
