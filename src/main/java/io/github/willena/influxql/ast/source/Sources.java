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

package io.github.willena.influxql.ast.source;

import io.github.willena.influxql.ast.Node;
import io.github.willena.influxql.ast.Source;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import java.util.Arrays;
import java.util.List;

/** List of {@link Source} */
public class Sources extends StringJoiningList<Source> implements Node {
    /**
     * Source list initialized with base
     *
     * @param sources initialization
     */
    public Sources(List<Source> sources) {
        super(sources);
    }

    /** New uninitialized list */
    public Sources() {
        super();
    }

    /**
     * Helper method to create an initialized Source list
     *
     * @param sources sources
     * @return a list of {@link Source}
     */
    public static Sources of(Source... sources) {
        return new Sources(Arrays.asList(sources));
    }

    public static Sources of(List<Source> sources) {
        return new Sources(sources);
    }
}
