# InfluxQL

Java library inspired from the official [influxql](https://github.com/influxdata/influxql),
and [influxqb](https://github.com/Willena/influxqb) go package.

Building strongly typed and secure InfluxQL queries from string is not always an easy task You have to take care of the
sanitization, keep the distinction between function, identifier, numbers and string literals to build a valid query.

The influxQL parser contains all the required types to manually build a query from scratch. More importantly it also
contains String() method on each type and statement. The parser then take care of escaping character, putting quotes or
not, ...

The implementation is based on types from the go parser AST.
Once a statement object is built the properly formated and escaped InfluxQL statement can be obtained by
calling `toString()`

## Download

Relases can be found from Maven and as github artifacts.

```xml

<dependency>
    <groupId>io.github.willena.influx</groupId>
    <artifactId>influxql</artifactId>
    <version>${version}</version>
</dependency>
```

## Notices

For now, no checks are made. The generated query is only guaranteed to be syntax valid. Only the influxdb server will
decide if your request is globally valid and can target your data.

## Example

```java

import io.github.willena.influxql.ast.statement.AlterRetentionPolicyStatement;

public static void main(String[] args) {
    AlterRetentionPolicyStatement stm = new AlterRetentionPolicyStatement.Builder()
            .withDatabase("DB")
            .withPolicyName("Policy")
            .build();

    System.out.println("stm = " + stm);
    // Prints 'ALTER RETENTION POLICY Policy ON "DB"'
}

```

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