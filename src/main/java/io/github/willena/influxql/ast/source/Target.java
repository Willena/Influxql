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

import static io.github.willena.influxql.ast.utils.Utils.ensureDefined;

import io.github.willena.influxql.ast.Node;
import java.util.Objects;

/** Destination Measurement when using SELECT INTO statements */
public class Target implements Node {
    private final Measurement measurement;

    /**
     * Build a new Target
     *
     * @param measurement measurement to use
     */
    public Target(Measurement measurement) {
        this.measurement = measurement;
        ensureDefined("measurement", measurement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Objects.equals(measurement, target.measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(measurement);
    }

    /**
     * Build a {@link Target} from {@link Measurement}
     *
     * @param measurement the measurement
     * @return the {@link Target}
     */
    public static Target of(Measurement measurement) {
        return new Target(measurement);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("INTO ");
        buf.append(measurement.toString());
        if (measurement.getName() == null || measurement.getName().isBlank()) {
            buf.append(":MEASUREMENT");
        }
        return buf.toString();
    }
}
