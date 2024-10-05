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

package io.github.willena.influxql.ast.extra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.willena.influxql.ast.expr.Call;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.Wildcard;
import io.github.willena.influxql.ast.expr.literal.*;
import java.time.Duration;
import org.junit.jupiter.api.Test;

class FunctionFactoryTest {
    @Test
    void aggregationCount() {
        assertEquals(
                "COUNT(*)", FunctionFactory.Aggregations.count(Wildcard.wildcard()).toString());
        assertEquals("COUNT(ooo)", FunctionFactory.Aggregations.count(VarRef.of("ooo")).toString());
        assertEquals(
                "COUNT(/ooo/)",
                FunctionFactory.Aggregations.count(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "COUNT(DISTINCT(ooo))",
                FunctionFactory.Aggregations.count(
                                FunctionFactory.Aggregations.distinct(VarRef.of("ooo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Aggregations.count(
                                        FunctionFactory.Aggregations.mean(VarRef.of("ooo")))
                                .toString());
    }

    @Test
    void aggregationDistinct() {
        assertEquals(
                "DISTINCT(*)",
                FunctionFactory.Aggregations.distinct(Wildcard.wildcard()).toString());
        assertEquals(
                "DISTINCT(ooo)",
                FunctionFactory.Aggregations.distinct(VarRef.of("ooo")).toString());
        assertEquals(
                "DISTINCT(/ooo/)",
                FunctionFactory.Aggregations.distinct(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationIntegral() {
        assertEquals(
                "INTEGRAL(*)",
                FunctionFactory.Aggregations.integral(Wildcard.wildcard()).toString());
        assertEquals(
                "INTEGRAL(ooo)",
                FunctionFactory.Aggregations.integral(VarRef.of("ooo")).toString());
        assertEquals(
                "INTEGRAL(/ooo/)",
                FunctionFactory.Aggregations.integral(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "INTEGRAL(*, 1h)",
                FunctionFactory.Aggregations.integral(
                                Wildcard.wildcard(), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "INTEGRAL(ooo, 1h)",
                FunctionFactory.Aggregations.integral(
                                VarRef.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "INTEGRAL(/ooo/, 1h)",
                FunctionFactory.Aggregations.integral(
                                RegexLiteral.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
    }

    @Test
    void aggregationMean() {
        assertEquals("MEAN(*)", FunctionFactory.Aggregations.mean(Wildcard.wildcard()).toString());
        assertEquals("MEAN(ooo)", FunctionFactory.Aggregations.mean(VarRef.of("ooo")).toString());
        assertEquals(
                "MEAN(/ooo/)",
                FunctionFactory.Aggregations.mean(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationMedian() {
        assertEquals(
                "MEDIAN(*)", FunctionFactory.Aggregations.median(Wildcard.wildcard()).toString());
        assertEquals(
                "MEDIAN(ooo)", FunctionFactory.Aggregations.median(VarRef.of("ooo")).toString());
        assertEquals(
                "MEDIAN(/ooo/)",
                FunctionFactory.Aggregations.median(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationMode() {
        assertEquals("MODE(*)", FunctionFactory.Aggregations.mode(Wildcard.wildcard()).toString());
        assertEquals("MODE(ooo)", FunctionFactory.Aggregations.mode(VarRef.of("ooo")).toString());
        assertEquals(
                "MODE(/ooo/)",
                FunctionFactory.Aggregations.mode(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationSpread() {
        assertEquals(
                "SPREAD(*)", FunctionFactory.Aggregations.spread(Wildcard.wildcard()).toString());
        assertEquals(
                "SPREAD(ooo)", FunctionFactory.Aggregations.spread(VarRef.of("ooo")).toString());
        assertEquals(
                "SPREAD(/ooo/)",
                FunctionFactory.Aggregations.spread(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationStdDev() {
        assertEquals(
                "STDDEV(*)", FunctionFactory.Aggregations.stdDev(Wildcard.wildcard()).toString());
        assertEquals(
                "STDDEV(ooo)", FunctionFactory.Aggregations.stdDev(VarRef.of("ooo")).toString());
        assertEquals(
                "STDDEV(/ooo/)",
                FunctionFactory.Aggregations.stdDev(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void aggregationSum() {
        assertEquals("SUM(*)", FunctionFactory.Aggregations.sum(Wildcard.wildcard()).toString());
        assertEquals("SUM(ooo)", FunctionFactory.Aggregations.sum(VarRef.of("ooo")).toString());
        assertEquals(
                "SUM(/ooo/)", FunctionFactory.Aggregations.sum(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void selectorBottom() {
        assertEquals(
                "BOTTOM(ooo, 1)",
                FunctionFactory.Selectors.bottom(VarRef.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "BOTTOM(ooo, ooo1, 1)",
                FunctionFactory.Selectors.bottom(
                                VarRef.of("ooo"), VarRef.of("ooo1"), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void selectorFirst() {
        assertEquals("FIRST(*)", FunctionFactory.Selectors.first(Wildcard.wildcard()).toString());
        assertEquals("FIRST(ooo)", FunctionFactory.Selectors.first(VarRef.of("ooo")).toString());
        assertEquals(
                "FIRST(/ooo/)", FunctionFactory.Selectors.first(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void selectorLast() {
        assertEquals("LAST(*)", FunctionFactory.Selectors.last(Wildcard.wildcard()).toString());
        assertEquals("LAST(ooo)", FunctionFactory.Selectors.last(VarRef.of("ooo")).toString());
        assertEquals(
                "LAST(/ooo/)", FunctionFactory.Selectors.last(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void selectorMax() {
        assertEquals("MAX(*)", FunctionFactory.Selectors.max(Wildcard.wildcard()).toString());
        assertEquals("MAX(ooo)", FunctionFactory.Selectors.max(VarRef.of("ooo")).toString());
        assertEquals(
                "MAX(/ooo/)", FunctionFactory.Selectors.max(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void selectorMin() {
        assertEquals("MIN(*)", FunctionFactory.Selectors.min(Wildcard.wildcard()).toString());
        assertEquals("MIN(ooo)", FunctionFactory.Selectors.min(VarRef.of("ooo")).toString());
        assertEquals(
                "MIN(/ooo/)", FunctionFactory.Selectors.min(RegexLiteral.of("ooo")).toString());
    }

    @Test
    void selectorPercentile() {
        assertEquals(
                "PERCENTILE(*, 1)",
                FunctionFactory.Selectors.percentile(Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "PERCENTILE(ooo, 1)",
                FunctionFactory.Selectors.percentile(VarRef.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "PERCENTILE(/ooo/, 1)",
                FunctionFactory.Selectors.percentile(RegexLiteral.of("ooo"), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void selectorSample() {
        assertEquals(
                "SAMPLE(*, 1)",
                FunctionFactory.Selectors.sample(Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "SAMPLE(ooo, 1)",
                FunctionFactory.Selectors.sample(VarRef.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "SAMPLE(/ooo/, 1)",
                FunctionFactory.Selectors.sample(RegexLiteral.of("ooo"), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void selectorTop() {
        assertEquals(
                "TOP(ooo, 1)",
                FunctionFactory.Selectors.top(VarRef.of("ooo"), IntegerLiteral.of(1)).toString());
        assertEquals(
                "TOP(ooo, ooo1, 1)",
                FunctionFactory.Selectors.top(
                                VarRef.of("ooo"), VarRef.of("ooo1"), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void transformerAbs() {
        assertEquals("ABS(*)", FunctionFactory.Transformations.abs(Wildcard.wildcard()).toString());
        assertEquals("ABS(ooo)", FunctionFactory.Transformations.abs(VarRef.of("ooo")).toString());
        assertEquals(
                "ABS(MAX(oo))",
                FunctionFactory.Transformations.abs(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.abs(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerAscos() {
        assertEquals(
                "ACOS(*)", FunctionFactory.Transformations.acos(Wildcard.wildcard()).toString());
        assertEquals(
                "ACOS(ooo)", FunctionFactory.Transformations.acos(VarRef.of("ooo")).toString());
        assertEquals(
                "ACOS(MAX(oo))",
                FunctionFactory.Transformations.acos(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.acos(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerAsin() {
        assertEquals(
                "ASIN(*)", FunctionFactory.Transformations.asin(Wildcard.wildcard()).toString());
        assertEquals(
                "ASIN(ooo)", FunctionFactory.Transformations.asin(VarRef.of("ooo")).toString());
        assertEquals(
                "ASIN(MAX(oo))",
                FunctionFactory.Transformations.asin(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.asin(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerAtan() {
        assertEquals(
                "ATAN(*)", FunctionFactory.Transformations.atan(Wildcard.wildcard()).toString());
        assertEquals(
                "ATAN(ooo)", FunctionFactory.Transformations.atan(VarRef.of("ooo")).toString());
        assertEquals(
                "ATAN(MAX(oo))",
                FunctionFactory.Transformations.atan(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.atan(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerAtan2() {
        assertEquals(
                "ATAN2(*, *)",
                FunctionFactory.Transformations.atan2(Wildcard.wildcard(), Wildcard.wildcard())
                        .toString());
        assertEquals(
                "ATAN2(*, ooo)",
                FunctionFactory.Transformations.atan2(Wildcard.wildcard(), VarRef.of("ooo"))
                        .toString());
        assertEquals(
                "ATAN2(ooo, *)",
                FunctionFactory.Transformations.atan2(VarRef.of("ooo"), Wildcard.wildcard())
                        .toString());
        assertEquals(
                "ATAN2(*, 1.0)",
                FunctionFactory.Transformations.atan2(Wildcard.wildcard(), NumberLiteral.of(1))
                        .toString());
        assertEquals(
                "ATAN2(1.0, *)",
                FunctionFactory.Transformations.atan2(NumberLiteral.of(1), Wildcard.wildcard())
                        .toString());
        assertEquals(
                "ATAN2(ooo, 1.0)",
                FunctionFactory.Transformations.atan2(VarRef.of("ooo"), NumberLiteral.of(1))
                        .toString());
        assertEquals(
                "ATAN2(ooo, ooo)",
                FunctionFactory.Transformations.atan2(VarRef.of("ooo"), VarRef.of("ooo"))
                        .toString());
        assertEquals(
                "ATAN2(1.0, 1.0)",
                FunctionFactory.Transformations.atan2(NumberLiteral.of(1), NumberLiteral.of(1))
                        .toString());
        assertEquals(
                "ATAN2(1.0, ooo)",
                FunctionFactory.Transformations.atan2(NumberLiteral.of(1), VarRef.of("ooo"))
                        .toString());
        assertEquals(
                "ATAN2(MAX(oo), MAX(oo))",
                FunctionFactory.Transformations.atan2(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.atan2(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerCeil() {
        assertEquals(
                "CEIL(*)", FunctionFactory.Transformations.ceil(Wildcard.wildcard()).toString());
        assertEquals(
                "CEIL(ooo)", FunctionFactory.Transformations.ceil(VarRef.of("ooo")).toString());
        assertEquals(
                "CEIL(MAX(oo))",
                FunctionFactory.Transformations.ceil(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.ceil(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerCos() {
        assertEquals("COS(*)", FunctionFactory.Transformations.cos(Wildcard.wildcard()).toString());
        assertEquals("COS(ooo)", FunctionFactory.Transformations.cos(VarRef.of("ooo")).toString());
        assertEquals(
                "COS(MAX(oo))",
                FunctionFactory.Transformations.cos(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.cos(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerCumSum() {
        assertEquals(
                "CUMULATIVE_SUM(*)",
                FunctionFactory.Transformations.cumulativeSum(Wildcard.wildcard()).toString());
        assertEquals(
                "CUMULATIVE_SUM(ooo)",
                FunctionFactory.Transformations.cumulativeSum(VarRef.of("ooo")).toString());
        assertEquals(
                "CUMULATIVE_SUM(/ooo/)",
                FunctionFactory.Transformations.cumulativeSum(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "CUMULATIVE_SUM(MAX(oo))",
                FunctionFactory.Transformations.cumulativeSum(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.cumulativeSum(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerDerivative() {
        assertEquals(
                "DERIVATIVE(*)",
                FunctionFactory.Transformations.derivative(Wildcard.wildcard()).toString());
        assertEquals(
                "DERIVATIVE(*, 1h)",
                FunctionFactory.Transformations.derivative(
                                Wildcard.wildcard(), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "DERIVATIVE(ooo)",
                FunctionFactory.Transformations.derivative(VarRef.of("ooo")).toString());
        assertEquals(
                "DERIVATIVE(ooo, 1h)",
                FunctionFactory.Transformations.derivative(
                                VarRef.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "DERIVATIVE(/ooo/)",
                FunctionFactory.Transformations.derivative(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "DERIVATIVE(/ooo/, 1h)",
                FunctionFactory.Transformations.derivative(
                                RegexLiteral.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "DERIVATIVE(MAX(oo))",
                FunctionFactory.Transformations.derivative(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertEquals(
                "DERIVATIVE(MAX(oo), 1h)",
                FunctionFactory.Transformations.derivative(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.derivative(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        DurationLiteral.of(Duration.ofHours(1)))
                                .toString());
    }

    @Test
    void transformerDifference() {
        assertEquals(
                "DIFFERENCE(*)",
                FunctionFactory.Transformations.difference(Wildcard.wildcard()).toString());
        assertEquals(
                "DIFFERENCE(ooo)",
                FunctionFactory.Transformations.difference(VarRef.of("ooo")).toString());
        assertEquals(
                "DIFFERENCE(/ooo/)",
                FunctionFactory.Transformations.difference(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "DIFFERENCE(MAX(oo))",
                FunctionFactory.Transformations.difference(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.difference(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerElapsed() {
        assertEquals(
                "ELAPSED(*)",
                FunctionFactory.Transformations.elapsed(Wildcard.wildcard()).toString());
        assertEquals(
                "ELAPSED(*, 1h)",
                FunctionFactory.Transformations.elapsed(
                                Wildcard.wildcard(), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "ELAPSED(ooo)",
                FunctionFactory.Transformations.elapsed(VarRef.of("ooo")).toString());
        assertEquals(
                "ELAPSED(ooo, 1h)",
                FunctionFactory.Transformations.elapsed(
                                VarRef.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "ELAPSED(/ooo/)",
                FunctionFactory.Transformations.elapsed(RegexLiteral.of("ooo")).toString());
        assertEquals(
                "ELAPSED(/ooo/, 1h)",
                FunctionFactory.Transformations.elapsed(
                                RegexLiteral.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "ELAPSED(MAX(oo))",
                FunctionFactory.Transformations.elapsed(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertEquals(
                "ELAPSED(MAX(oo), 1h)",
                FunctionFactory.Transformations.elapsed(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.elapsed(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        DurationLiteral.of(Duration.ofHours(1)))
                                .toString());
    }

    @Test
    void transformerExp() {
        assertEquals("EXP(*)", FunctionFactory.Transformations.exp(Wildcard.wildcard()).toString());
        assertEquals("EXP(ooo)", FunctionFactory.Transformations.exp(VarRef.of("ooo")).toString());
        assertEquals(
                "EXP(MAX(oo))",
                FunctionFactory.Transformations.exp(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.exp(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerFloor() {
        assertEquals(
                "FLOOR(*)", FunctionFactory.Transformations.floor(Wildcard.wildcard()).toString());
        assertEquals(
                "FLOOR(ooo)", FunctionFactory.Transformations.floor(VarRef.of("ooo")).toString());
        assertEquals(
                "FLOOR(MAX(oo))",
                FunctionFactory.Transformations.floor(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.floor(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerLn() {
        assertEquals("LN(*)", FunctionFactory.Transformations.ln(Wildcard.wildcard()).toString());
        assertEquals("LN(ooo)", FunctionFactory.Transformations.ln(VarRef.of("ooo")).toString());
        assertEquals(
                "LN(MAX(oo))",
                FunctionFactory.Transformations.ln(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.ln(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerLog() {
        assertEquals(
                "LOG(*, 1)",
                FunctionFactory.Transformations.log(Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "LOG(ooo, 1)",
                FunctionFactory.Transformations.log(VarRef.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "LOG(MAX(oo), 1)",
                FunctionFactory.Transformations.log(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                IntegerLiteral.of(1))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.log(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        IntegerLiteral.of(1))
                                .toString());
    }

    @Test
    void transformerLog2() {
        assertEquals(
                "LOG2(*)", FunctionFactory.Transformations.log2(Wildcard.wildcard()).toString());
        assertEquals(
                "LOG2(ooo)", FunctionFactory.Transformations.log2(VarRef.of("ooo")).toString());
        assertEquals(
                "LOG2(MAX(oo))",
                FunctionFactory.Transformations.log2(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.log2(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerLog10() {
        assertEquals(
                "LOG10(*)", FunctionFactory.Transformations.log10(Wildcard.wildcard()).toString());
        assertEquals(
                "LOG10(ooo)", FunctionFactory.Transformations.log10(VarRef.of("ooo")).toString());
        assertEquals(
                "LOG10(MAX(oo))",
                FunctionFactory.Transformations.log10(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.log10(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerMovingAverage() {
        assertEquals(
                "MOVING_AVERAGE(*, 1)",
                FunctionFactory.Transformations.movingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "MOVING_AVERAGE(ooo, 1)",
                FunctionFactory.Transformations.movingAverage(
                                VarRef.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "MOVING_AVERAGE(/ooo/, 1)",
                FunctionFactory.Transformations.movingAverage(
                                RegexLiteral.of("ooo"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "MOVING_AVERAGE(MAX(oo), 1)",
                FunctionFactory.Transformations.movingAverage(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                IntegerLiteral.of(1))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.movingAverage(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        IntegerLiteral.of(1))
                                .toString());
    }

    @Test
    void transformerNonNegativeDerivative() {
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(*)",
                FunctionFactory.Transformations.nonNegativeDerivative(Wildcard.wildcard())
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(*, 1h)",
                FunctionFactory.Transformations.nonNegativeDerivative(
                                Wildcard.wildcard(), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(ooo)",
                FunctionFactory.Transformations.nonNegativeDerivative(VarRef.of("ooo")).toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(ooo, 1h)",
                FunctionFactory.Transformations.nonNegativeDerivative(
                                VarRef.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(/ooo/)",
                FunctionFactory.Transformations.nonNegativeDerivative(RegexLiteral.of("ooo"))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(/ooo/, 1h)",
                FunctionFactory.Transformations.nonNegativeDerivative(
                                RegexLiteral.of("ooo"), DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(MAX(oo))",
                FunctionFactory.Transformations.nonNegativeDerivative(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DERIVATIVE(MAX(oo), 1h)",
                FunctionFactory.Transformations.nonNegativeDerivative(
                                FunctionFactory.Selectors.max(VarRef.of("oo")),
                                DurationLiteral.of(Duration.ofHours(1)))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.nonNegativeDerivative(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        DurationLiteral.of(Duration.ofHours(1)))
                                .toString());
    }

    @Test
    void transformerNonNegativeDifference() {
        assertEquals(
                "NON_NEGATIVE_DIFFERENCE(*)",
                FunctionFactory.Transformations.nonNegativeDifference(Wildcard.wildcard())
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DIFFERENCE(ooo)",
                FunctionFactory.Transformations.nonNegativeDifference(VarRef.of("ooo")).toString());
        assertEquals(
                "NON_NEGATIVE_DIFFERENCE(/ooo/)",
                FunctionFactory.Transformations.nonNegativeDifference(RegexLiteral.of("ooo"))
                        .toString());
        assertEquals(
                "NON_NEGATIVE_DIFFERENCE(MAX(oo))",
                FunctionFactory.Transformations.nonNegativeDifference(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.nonNegativeDifference(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerPow() {
        assertEquals(
                "POW(*, 1.0)",
                FunctionFactory.Transformations.pow(Wildcard.wildcard(), NumberLiteral.of(1))
                        .toString());
        assertEquals(
                "POW(ooo, 1.0)",
                FunctionFactory.Transformations.pow(VarRef.of("ooo"), NumberLiteral.of(1))
                        .toString());
        assertEquals(
                "POW(MAX(oo), 1.0)",
                FunctionFactory.Transformations.pow(
                                FunctionFactory.Selectors.max(VarRef.of("oo")), NumberLiteral.of(1))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.pow(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)),
                                        NumberLiteral.of(1))
                                .toString());
    }

    @Test
    void transformerRound() {
        assertEquals(
                "ROUND(*)", FunctionFactory.Transformations.round(Wildcard.wildcard()).toString());
        assertEquals(
                "ROUND(ooo)", FunctionFactory.Transformations.round(VarRef.of("ooo")).toString());
        assertEquals(
                "ROUND(MAX(oo))",
                FunctionFactory.Transformations.round(
                                FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.round(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerSin() {
        assertEquals("SIN(*)", FunctionFactory.Transformations.sin(Wildcard.wildcard()).toString());
        assertEquals("SIN(ooo)", FunctionFactory.Transformations.sin(VarRef.of("ooo")).toString());
        assertEquals(
                "SIN(MAX(oo))",
                FunctionFactory.Transformations.sin(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.sin(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerSqrt() {
        assertEquals(
                "SQRT(*)", FunctionFactory.Transformations.sqrt(Wildcard.wildcard()).toString());
        assertEquals(
                "SQRT(ooo)", FunctionFactory.Transformations.sqrt(VarRef.of("ooo")).toString());
        assertEquals(
                "SQRT(MAX(oo))",
                FunctionFactory.Transformations.sqrt(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.sqrt(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void transformerTan() {
        assertEquals("TAN(*)", FunctionFactory.Transformations.tan(Wildcard.wildcard()).toString());
        assertEquals("TAN(ooo)", FunctionFactory.Transformations.tan(VarRef.of("ooo")).toString());
        assertEquals(
                "TAN(MAX(oo))",
                FunctionFactory.Transformations.tan(FunctionFactory.Selectors.max(VarRef.of("oo")))
                        .toString());
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Transformations.tan(
                                        FunctionFactory.Selectors.sample(
                                                VarRef.of("oo"), IntegerLiteral.of(1)))
                                .toString());
    }

    @Test
    void predictorsHoltWinters() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Predictors.holtWinters(
                                FunctionFactory.Aggregations.count(Wildcard.wildcard()),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1)));
        assertEquals(
                "HOLT_WINTERS(COUNT(rrr), 1, 1)",
                FunctionFactory.Predictors.holtWinters(
                                FunctionFactory.Aggregations.count(VarRef.of("rrr")),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void predictorsHoltWintersWithFit() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        FunctionFactory.Predictors.holtWintersWithFit(
                                FunctionFactory.Aggregations.count(Wildcard.wildcard()),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1)));
        assertEquals(
                "HOLT_WINTERS_WITH_FIT(COUNT(rrr), 1, 1)",
                FunctionFactory.Predictors.holtWintersWithFit(
                                FunctionFactory.Aggregations.count(VarRef.of("rrr")),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void technicalAnalysisChandeMomentumOscillator() {
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(*, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(v, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "CHANDE_MOMENTUM_OSCILLATOR(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.chandeMomentumOscillator(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }

    @Test
    void technicalAnalysisExponentialMovingAverage() {
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(*, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(v, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.exponentialMovingAverage(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }

    @Test
    void technicalAnalysisDoubleExponentialMovingAverage() {
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(*, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(v, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "DOUBLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.doubleExponentialMovingAverage(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }

    @Test
    void technicalAnalysisKaufmansEffeciencyRatio() {
        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(*, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(v, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_EFFICIENCY_RATIO(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansEffeciencyRatio(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void technicalAnalysisKaufmansAdaptativeMovingAverage() {
        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(*, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(v, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "KAUFMANS_ADAPTIVE_MOVING_AVERAGE(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.kaufmansAdaptativeMovingAverage(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
    }

    @Test
    void technicalAnalysisTripleExponentialMovingAverage() {
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(*, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(v, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_MOVING_AVERAGE(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialMovingAverage(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }

    @Test
    void technicalAnalysisTripleExponentialDerivative() {
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(*, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(v, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "TRIPLE_EXPONENTIAL_DERIVATIVE(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.tripleExponentialDerivative(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }

    @Test
    void technicalAnalysisRelativeStrengthIndex() {
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(*, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                Wildcard.wildcard(), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(*, 1, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                Wildcard.wildcard(), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(*, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                Wildcard.wildcard(),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "RELATIVE_STRENGTH_INDEX(v, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                VarRef.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(v, 1, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                VarRef.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(v, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                VarRef.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        assertEquals(
                "RELATIVE_STRENGTH_INDEX(/v/, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                RegexLiteral.of("v"), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(/v/, 1, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                RegexLiteral.of("v"), IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(/v/, 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                RegexLiteral.of("v"),
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());

        Call c = FunctionFactory.Aggregations.mean(VarRef.of("v"));

        assertEquals(
                "RELATIVE_STRENGTH_INDEX(MEAN(v), 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(c, IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(MEAN(v), 1, 1)",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                c, IntegerLiteral.of(1), IntegerLiteral.of(1))
                        .toString());
        assertEquals(
                "RELATIVE_STRENGTH_INDEX(MEAN(v), 1, 1, 'warmup')",
                FunctionFactory.TechnicalAnalysis.relativeStrengthIndex(
                                c,
                                IntegerLiteral.of(1),
                                IntegerLiteral.of(1),
                                StringLiteral.of("warmup"))
                        .toString());
    }
}
