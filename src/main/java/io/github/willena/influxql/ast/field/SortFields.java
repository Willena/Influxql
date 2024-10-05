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

import io.github.willena.influxql.ast.utils.StringJoiningList;
import java.util.List;

/** List of sort fields */
public class SortFields extends StringJoiningList<SortField> {
    /**
     * Create initialized list
     *
     * @param list the list
     */
    public SortFields(List<SortField> list) {
        super(list);
    }

    /** Create empty {@link SortField} list */
    public SortFields() {
        super();
    }

    /**
     * Create initialized sortField list
     *
     * @param sortFields elements to add
     * @return the list
     */
    public static SortFields of(SortField... sortFields) {
        return new SortFields(List.of(sortFields));
    }
}
