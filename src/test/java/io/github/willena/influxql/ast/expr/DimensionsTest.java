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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class DimensionsTest {
    @Test
    void testDimensionsEmpty() {
        assertEquals(0, new Dimensions().size());
    }

    @Test
    void testDimensions() {
        Dimensions dimensions = Dimensions.of(Dimension.of(VarRef.of("jjj")));
        assertEquals(1, dimensions.size());
        assertEquals("jjj", dimensions.toString());
    }

    @Test
    void of() {
        Dimensions dimensions = Dimensions.of(List.of(Dimension.of(VarRef.of("jjj"))));
        assertEquals(1, dimensions.size());
        assertEquals("jjj", dimensions.toString());
    }
}
