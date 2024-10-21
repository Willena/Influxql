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

package io.github.willena.influxql.ast.field;

import io.github.willena.influxql.ast.Node;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import java.util.List;

/** List of fields */
public class Fields extends StringJoiningList<Field> implements Node {
    /**
     * Create initialized list of field
     *
     * @param list list
     */
    public Fields(List<Field> list) {
        super(list);
    }

    /** Create empty list of fields */
    public Fields() {
        super();
    }

    /**
     * Create an initialized list of fields
     *
     * @param fields fields to add
     * @return field list
     */
    public static Fields of(Field... fields) {
        return new Fields(List.of(fields));
    }

    public static Fields of(List<Field> fields) {
        return new Fields(fields);
    }
}
