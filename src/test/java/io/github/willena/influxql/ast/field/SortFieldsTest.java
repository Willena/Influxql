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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SortFieldsTest {

    @Test
    void test() {
        assertEquals(0, new SortFields().size());
        assertEquals(0, SortFields.of().size());
        assertEquals(
                "\"name\" ASC, another DESC",
                SortFields.of(SortField.asc("name"), SortField.desc("another")).toString());
    }

    @Test
    void of() {
        assertEquals(1, SortFields.of(List.of(SortField.asc("abc"))).size());
    }
}
