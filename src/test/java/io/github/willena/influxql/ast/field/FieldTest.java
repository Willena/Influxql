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

import io.github.willena.influxql.ast.expr.DataType;
import io.github.willena.influxql.ast.expr.VarRef;
import org.junit.jupiter.api.Test;

class FieldTest {
    @Test
    void testField() {
        assertEquals("fieldName", Field.field("fieldName").toString());
        assertEquals("fieldName", Field.field("fieldName").toString());
        assertEquals(
                "fieldName",
                new Field.Builder()
                        .withExpr(VarRef.of("fieldName"))
                        .withAlias("")
                        .build()
                        .toString());
        assertEquals("fieldName::integer", Field.field("fieldName", DataType.INTEGER).toString());
    }

    @Test
    void fieldWithAlias() {
        assertEquals(
                "fieldAlias AS toto",
                new Field.Builder()
                        .withAlias("toto")
                        .withExpr(VarRef.of("fieldAlias"))
                        .build()
                        .toString());
    }

    @Test
    void wildcard() {
        assertEquals("*", Field.wildcard().toString());
        assertEquals("*::field", Field.wildcardFields().toString());
        assertEquals("*::tag", Field.wildcardTags().toString());
    }
}
