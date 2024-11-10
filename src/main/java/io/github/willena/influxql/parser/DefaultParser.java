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

package io.github.willena.influxql.parser;

import io.github.willena.influxql.ast.Node;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.antlr.v4.runtime.*;

public class DefaultParser {

    private static class ErrorListener extends BaseErrorListener {

        public ErrorListener() {}

        @Override
        public void syntaxError(
                Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line,
                int charPositionInLine,
                String msg,
                RecognitionException e) {
            throw new IllegalArgumentException("InfluxQL syntax error: " + msg, e);
        }
    }

    public static <TREE, T extends Node> T parseFrom(
            Function<InfluxqlParser, TREE> entryPoint,
            BiFunction<TREE, InfluxqlAstAdapter, T> adapterFunction,
            String influxql) {
        ErrorListener errorListener = new ErrorListener();
        InfluxqlLexer lexer = new InfluxqlLexer(CharStreams.fromString(influxql));
        lexer.addErrorListener(errorListener);

        CommonTokenStream token = new CommonTokenStream(lexer);
        InfluxqlParser parser = new InfluxqlParser(token);
        parser.addErrorListener(errorListener);

        InfluxqlAstAdapter adapter = new InfluxqlAstAdapter();
        TREE ctx = entryPoint.apply(parser);

        return adapterFunction.apply(ctx, adapter);
    }
}
