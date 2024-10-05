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

package io.github.willena.influxql.ast.statement;

import static org.junit.jupiter.api.Assertions.*;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

public abstract class GenericStatementTest<T extends Statement> {

    @ParameterizedTest
    @FieldSource("TEST_BODIES")
    public void testAllStatements(TestBody<T> testCase) {
        if (testCase.shouldFail()) {
            assertThrows(Exception.class, testCase.getStatement()::build);
        } else {
            T sql = assertDoesNotThrow(testCase.getStatement()::build);
            assertEquals(testCase.getExpectedSql(), sql.toString());
        }
    }

    public static class TestBody<T0 extends Statement> {
        private final String expectedSql;
        private final Buildable<T0> statement;
        private final boolean shouldFail;

        private TestBody(Builder<T0> builder) {
            expectedSql = builder.expectedSql;
            statement = builder.statement;
            shouldFail = builder.shouldFail;
        }

        public String getExpectedSql() {
            return expectedSql;
        }

        public Buildable<T0> getStatement() {
            return statement;
        }

        public boolean shouldFail() {
            return shouldFail;
        }

        @Override
        public String toString() {
            return expectedSql;
        }

        public static final class Builder<T1 extends Statement> {
            private String expectedSql;
            private Buildable<T1> statement;
            private boolean shouldFail;

            public Builder() {}

            public TestBody<T1> build() {
                return new TestBody<>(this);
            }

            public Builder<T1> withShouldFail(boolean shouldFail) {
                this.shouldFail = shouldFail;
                return this;
            }

            public Builder<T1> withExpectedSql(String expectedSql) {
                this.expectedSql = expectedSql;
                return this;
            }

            public Builder<T1> withStatement(Buildable<T1> statement) {
                this.statement = statement;
                return this;
            }
        }
    }
}
