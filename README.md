# InfluxQL



![CI](https://img.shields.io/github/actions/workflow/status/willena/influxql/ci.yml?branch=master)
[![MVN](https://img.shields.io/maven-central/v/io.github.willena/influxql)](https://maven-badges.herokuapp.com/maven-central/io.github.willena/influxql/)
[![JDOC](https://javadoc.io/badge2/io.github.willena/influxql/javadoc.svg)](https://javadoc.io/doc/io.github.willena/influxql)
[![SNAP](https://img.shields.io/nexus/s/io.github.willena/influxql?color=blue&label=maven%20snapshot&server=https%3A%2F%2Foss.sonatype.org%2F)](https://oss.sonatype.org/content/repositories/snapshots/io/github/willena/influxql/)

Java library inspired from the official [influxql](https://github.com/influxdata/influxql) and [influxqb](https://github.com/Willena/influxqb) go package.

Building strongly typed and secure InfluxQL queries from string is not always an easy task You have to take care of the
sanitization, keep the distinction between function, identifier, numbers and string literals to build a valid query.

The influxQL parser contains all the required types to manually build a query from scratch. More importantly it also
contains String() method on each type and statement. The parse  r then take care of escaping character, putting quotes or
not, ...

The implementation is based on types from the go parser AST.
Once a statement object is built the properly formated and escaped InfluxQL statement can be obtained by
calling `toString()`

## Download

Relases can be found from Maven and as github artifacts.

```xml

<dependency>
    <groupId>io.github.willena</groupId>
    <artifactId>influxql</artifactId>
    <version>${version}</version>
</dependency>
```

## Notices

For now, no checks are made. The generated query is only guaranteed to be syntax valid. Only the influxdb server will
decide if your request is globally valid and can target your data.

## Example

```java

import io.github.willena.influxql.ast.expr.Dimension;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.statement.AlterRetentionPolicyStatement;
import io.github.willena.influxql.ast.statement.SelectStatement;
import io.github.willena.influxql.ast.extra.FunctionFactory.Aggregations.mean;
import io.github.willena.influxql.ast.source.Measurement.measurement;
import io.github.willena.influxql.ast.expr.Dimension.sampledBy;

import java.time.Duration;

public static void main(String[] args) {
    AlterRetentionPolicyStatement stm = new AlterRetentionPolicyStatement.Builder()
            .on("DB")
            .policyName("Policy")
            .build();

    System.out.println(stm);
    // Prints 'ALTER RETENTION POLICY Policy ON "DB"'

    SelectStatement select = new SelectStatement.Builder()
            .select(Field.of(mean(VarRef.of("field"))))
            .from(measurement("meas"))
            .groupBy(sampledBy(Duration.ofDays(1)))
            .build();
    System.out.println(select);
    // Prints 'SELECT MEAN(field) FROM meas GROUP BY time(1d)'
}

```

## Note:

The InfluxQL ANTLR-based gramar included in the repo is not fully working.
A parser for influxql syntax is not yet included.

## Licence

```text
   InfluxQL Java package
   Copyright 2024 Guillaume VILLENA aka "Willena"
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
   
      http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```