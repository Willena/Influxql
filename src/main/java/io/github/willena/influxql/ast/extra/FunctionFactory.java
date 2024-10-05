package io.github.willena.influxql.ast.extra;

import io.github.willena.influxql.ast.expr.Call;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.Wildcard;
import io.github.willena.influxql.ast.expr.literal.*;

import java.util.List;

/**
 * Helper methods of common and known InfluxQL functions
 * If a function is not covered by this factory.
 * You can still use the AST {@link Call} object to represent it.
 */
public final class FunctionFactory {
    private FunctionFactory() {
        // nothing here !
    }

    /**
     * The type Aggregations functions.
     */
    public static final class Aggregations {

        /**
         * The constant COUNT_NAME.
         */
        public static final String COUNT_NAME = "COUNT";
        /**
         * The constant DISTINCT_NAME.
         */
        public static final String DISTINCT_NAME = "DISTINCT";
        /**
         * The constant INTEGRAL_NAME.
         */
        public static final String INTEGRAL_NAME = "INTEGRAL";
        /**
         * The constant MEAN_NAME.
         */
        public static final String MEAN_NAME = "MEAN";
        /**
         * The constant MEDIAN_NAME.
         */
        public static final String MEDIAN_NAME = "MEDIAN";
        /**
         * The constant MODE_NAME.
         */
        public static final String MODE_NAME = "MODE";
        /**
         * The constant SPREAD_NAME.
         */
        public static final String SPREAD_NAME = "SPREAD";
        /**
         * The constant STDDEV_NAME.
         */
        public static final String STDDEV_NAME = "STDDEV";
        /**
         * The constant SUM_NAME.
         */
        public static final String SUM_NAME = "SUM";

        private Aggregations() {
            // nothing
        }

        /**
         * Count call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call count(Wildcard wildcard) {
            return new Call.Builder().function(COUNT_NAME).withArguments(wildcard).build();
        }

        /**
         * Count call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call count(RegexLiteral regexLiteral) {
            return new Call.Builder().function(COUNT_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Count call.
         *
         * @param field the field
         * @return the call
         */
        public static Call count(VarRef field) {
            return new Call.Builder().function(COUNT_NAME).withArguments(field).build();
        }

        /**
         * Count call.
         *
         * @param distinct the distinct
         * @return the call
         */
        public static Call count(Call distinct) {
            if (!DISTINCT_NAME.equals(distinct.getName())) {
                throw new IllegalArgumentException("Count only support DISTINCT as nested function");
            }
            return new Call.Builder().function(COUNT_NAME).withArguments(distinct).build();
        }

        /**
         * Distinct call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call distinct(Wildcard wildcard) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(wildcard).build();
        }

        /**
         * Distinct call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call distinct(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Distinct call.
         *
         * @param field the field
         * @return the call
         */
        public static Call distinct(VarRef field) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(field).build();
        }

        /**
         * Integral call.
         *
         * @param wildcard        the wildcard
         * @param durationLiteral the duration literal
         * @return the call
         */
        public static Call integral(Wildcard wildcard, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(wildcard, durationLiteral).build();
        }

        /**
         * Integral call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call integral(Wildcard wildcard) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(wildcard).build();
        }

        /**
         * Integral call.
         *
         * @param regexLiteral    the regex literal
         * @param durationLiteral the duration literal
         * @return the call
         */
        public static Call integral(RegexLiteral regexLiteral, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(regexLiteral, durationLiteral).build();
        }

        /**
         * Integral call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call integral(RegexLiteral regexLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Integral call.
         *
         * @param field           the field
         * @param durationLiteral the duration literal
         * @return the call
         */
        public static Call integral(VarRef field, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(field, durationLiteral).build();
        }

        /**
         * Integral call.
         *
         * @param field the field
         * @return the call
         */
        public static Call integral(VarRef field) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(field).build();
        }

        /**
         * Mean call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call mean(Wildcard wildcard) {
            return new Call.Builder().function(MEAN_NAME).withArguments(wildcard).build();
        }

        /**
         * Mean call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call mean(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MEAN_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Mean call.
         *
         * @param field the field
         * @return the call
         */
        public static Call mean(VarRef field) {
            return new Call.Builder().function(MEAN_NAME).withArguments(field).build();
        }

        /**
         * Median call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call median(Wildcard wildcard) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(wildcard).build();
        }

        /**
         * Median call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call median(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Median call.
         *
         * @param field the field
         * @return the call
         */
        public static Call median(VarRef field) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(field).build();
        }

        /**
         * Mode call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call mode(Wildcard wildcard) {
            return new Call.Builder().function(MODE_NAME).withArguments(wildcard).build();
        }

        /**
         * Mode call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call mode(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MODE_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Mode call.
         *
         * @param field the field
         * @return the call
         */
        public static Call mode(VarRef field) {
            return new Call.Builder().function(MODE_NAME).withArguments(field).build();
        }

        /**
         * Spread call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call spread(Wildcard wildcard) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(wildcard).build();
        }

        /**
         * Spread call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call spread(RegexLiteral regexLiteral) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Spread call.
         *
         * @param field the field
         * @return the call
         */
        public static Call spread(VarRef field) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(field).build();
        }

        /**
         * Std dev call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call stdDev(Wildcard wildcard) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(wildcard).build();
        }

        /**
         * Std dev call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call stdDev(RegexLiteral regexLiteral) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Std dev call.
         *
         * @param field the field
         * @return the call
         */
        public static Call stdDev(VarRef field) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(field).build();
        }

        /**
         * Sum call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call sum(Wildcard wildcard) {
            return new Call.Builder().function(SUM_NAME).withArguments(wildcard).build();
        }

        /**
         * Sum call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call sum(RegexLiteral regexLiteral) {
            return new Call.Builder().function(SUM_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Sum call.
         *
         * @param field the field
         * @return the call
         */
        public static Call sum(VarRef field) {
            return new Call.Builder().function(SUM_NAME).withArguments(field).build();
        }
    }

    /**
     * The type Selectors functions.
     */
    public static final class Selectors {
        /**
         * The constant BOTTOM_NAME.
         */
        public static final String BOTTOM_NAME = "BOTTOM";
        /**
         * The constant FIRST_NAME.
         */
        public static final String FIRST_NAME = "FIRST";
        /**
         * The constant LAST_NAME.
         */
        public static final String LAST_NAME = "LAST";
        /**
         * The constant MAX_NAME.
         */
        public static final String MAX_NAME = "MAX";
        /**
         * The constant MIN_NAME.
         */
        public static final String MIN_NAME = "MIN";
        /**
         * The constant PERCENTILE_NAME.
         */
        public static final String PERCENTILE_NAME = "PERCENTILE";
        /**
         * The constant SAMPLE_NAME.
         */
        public static final String SAMPLE_NAME = "SAMPLE";
        /**
         * The constant TOP_NAME.
         */
        public static final String TOP_NAME = "TOP";

        private Selectors() {
            // nothing
        }

        /**
         * Bottom call.
         *
         * @param field the field
         * @param n     the n
         * @return the call
         */
        public static Call bottom(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(BOTTOM_NAME).withArguments(field, n).build();
        }

        /**
         * Bottom call.
         *
         * @param field   the field
         * @param tagKeys the tag keys
         * @param n       the n
         * @return the call
         */
        public static Call bottom(VarRef field, VarRef tagKeys, IntegerLiteral n) {
            return new Call.Builder().function(BOTTOM_NAME).withArguments(field, tagKeys, n).build();
        }

        /**
         * First call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call first(Wildcard wildcard) {
            return new Call.Builder().function(FIRST_NAME).withArguments(wildcard).build();
        }

        /**
         * First call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call first(RegexLiteral regexLiteral) {
            return new Call.Builder().function(FIRST_NAME).withArguments(regexLiteral).build();
        }

        /**
         * First call.
         *
         * @param field the field
         * @return the call
         */
        public static Call first(VarRef field) {
            return new Call.Builder().function(FIRST_NAME).withArguments(field).build();
        }

        /**
         * Last call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call last(Wildcard wildcard) {
            return new Call.Builder().function(LAST_NAME).withArguments(wildcard).build();
        }

        /**
         * Last call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call last(RegexLiteral regexLiteral) {
            return new Call.Builder().function(LAST_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Last call.
         *
         * @param field the field
         * @return the call
         */
        public static Call last(VarRef field) {
            return new Call.Builder().function(LAST_NAME).withArguments(field).build();
        }

        /**
         * Max call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call max(Wildcard wildcard) {
            return new Call.Builder().function(MAX_NAME).withArguments(wildcard).build();
        }

        /**
         * Max call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call max(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MAX_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Max call.
         *
         * @param field the field
         * @return the call
         */
        public static Call max(VarRef field) {
            return new Call.Builder().function(MAX_NAME).withArguments(field).build();
        }

        /**
         * Min call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call min(Wildcard wildcard) {
            return new Call.Builder().function(MIN_NAME).withArguments(wildcard).build();
        }

        /**
         * Min call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call min(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MIN_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Min call.
         *
         * @param field the field
         * @return the call
         */
        public static Call min(VarRef field) {
            return new Call.Builder().function(MIN_NAME).withArguments(field).build();
        }


        /**
         * Percentile call.
         *
         * @param wildcard the wildcard
         * @param n        the n
         * @return the call
         */
        public static Call percentile(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(wildcard, n).build();
        }

        /**
         * Percentile call.
         *
         * @param regexLiteral the regex literal
         * @param n            the n
         * @return the call
         */
        public static Call percentile(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(regexLiteral, n).build();
        }

        /**
         * Percentile call.
         *
         * @param field the field
         * @param n     the n
         * @return the call
         */
        public static Call percentile(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(field, n).build();
        }

        /**
         * Sample call.
         *
         * @param wildcard the wildcard
         * @param n        the n
         * @return the call
         */
        public static Call sample(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(wildcard, n).build();
        }

        /**
         * Sample call.
         *
         * @param regexLiteral the regex literal
         * @param n            the n
         * @return the call
         */
        public static Call sample(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(regexLiteral, n).build();
        }

        /**
         * Sample call.
         *
         * @param field the field
         * @param n     the n
         * @return the call
         */
        public static Call sample(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(field, n).build();
        }

        /**
         * Top call.
         *
         * @param field the field
         * @param tag   the tag
         * @param n     the n
         * @return the call
         */
        public static Call top(VarRef field, VarRef tag, IntegerLiteral n) {
            return new Call.Builder().function(TOP_NAME).withArguments(field, tag, n).build();
        }

        /**
         * Top call.
         *
         * @param field the field
         * @param n     the n
         * @return the call
         */
        public static Call top(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(TOP_NAME).withArguments(field, n).build();
        }
    }

    /**
     * The type Transformations functions.
     */
    public static final class Transformations {
        /**
         * The constant ABS_NAME.
         */
        public static String ABS_NAME = "ABS";
        /**
         * The constant ACOS_NAME.
         */
        public static String ACOS_NAME = "ACOS";
        /**
         * The constant ASIN_NAME.
         */
        public static String ASIN_NAME = "ASIN";
        /**
         * The constant ATAN_NAME.
         */
        public static String ATAN_NAME = "ATAN";
        /**
         * The constant ATAN2_NAME.
         */
        public static String ATAN2_NAME = "ATAN2";
        /**
         * The constant CEIL_NAME.
         */
        public static String CEIL_NAME = "CEIL";
        /**
         * The constant COS_NAME.
         */
        public static String COS_NAME = "COS";
        /**
         * The constant CUMULATIVE_SUM_NAME.
         */
        public static String CUMULATIVE_SUM_NAME = "CUMULATIVE_SUM";
        /**
         * The constant DERIVATIVE_NAME.
         */
        public static String DERIVATIVE_NAME = "DERIVATIVE";
        /**
         * The constant DIFFERENCE_NAME.
         */
        public static String DIFFERENCE_NAME = "DIFFERENCE";
        /**
         * The constant ELAPSED_NAME.
         */
        public static String ELAPSED_NAME = "ELAPSED";
        /**
         * The constant EXP_NAME.
         */
        public static String EXP_NAME = "EXP";
        /**
         * The constant FLOOR_NAME.
         */
        public static String FLOOR_NAME = "FLOOR";
        /**
         * The constant LN_NAME.
         */
        public static String LN_NAME = "LN";
        /**
         * The constant LOG_NAME.
         */
        public static String LOG_NAME = "LOG";
        /**
         * The constant LOG2_NAME.
         */
        public static String LOG2_NAME = "LOG2";
        /**
         * The constant LOG10_NAME.
         */
        public static String LOG10_NAME = "LOG10";
        /**
         * The constant MOVING_AVERAGE_NAME.
         */
        public static String MOVING_AVERAGE_NAME = "MOVING_AVERAGE";
        /**
         * The constant NON_NEGATIVE_DERIVATIVE_NAME.
         */
        public static String NON_NEGATIVE_DERIVATIVE_NAME = "NON_NEGATIVE_DERIVATIVE";
        /**
         * The constant NON_NEGATIVE_DIFFERENCE_NAME.
         */
        public static String NON_NEGATIVE_DIFFERENCE_NAME = "NON_NEGATIVE_DIFFERENCE";
        /**
         * The constant POW_NAME.
         */
        public static String POW_NAME = "POW";
        /**
         * The constant ROUND_NAME.
         */
        public static String ROUND_NAME = "ROUND";
        /**
         * The constant SIN_NAME.
         */
        public static String SIN_NAME = "SIN";
        /**
         * The constant SQRT_NAME.
         */
        public static String SQRT_NAME = "SQRT";
        /**
         * The constant TAN_NAME.
         */
        public static String TAN_NAME = "TAN";

        private static final List<String> ALLOWED_NESTED = List.of(
                Aggregations.COUNT_NAME,
                Aggregations.MEAN_NAME,
                Aggregations.MEDIAN_NAME,
                Aggregations.MODE_NAME,
                Aggregations.SUM_NAME,
                Selectors.FIRST_NAME,
                Selectors.LAST_NAME,
                Selectors.MIN_NAME,
                Selectors.MAX_NAME,
                Selectors.PERCENTILE_NAME);

        private Transformations() {
            // nothing
        }

        /**
         * Abs call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call abs(Wildcard wildcard) {
            return new Call.Builder().function(ABS_NAME).withArguments(wildcard).build();
        }

        /**
         * Abs call.
         *
         * @param field the field
         * @return the call
         */
        public static Call abs(VarRef field) {
            return new Call.Builder().function(ABS_NAME).withArguments(field).build();
        }

        /**
         * Abs call.
         *
         * @param call the call
         * @return the call
         */
        public static Call abs(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ABS_NAME).withArguments(call).build();
        }

        /**
         * Acos call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call acos(Wildcard wildcard) {
            return new Call.Builder().function(ACOS_NAME).withArguments(wildcard).build();
        }

        /**
         * Acos call.
         *
         * @param field the field
         * @return the call
         */
        public static Call acos(VarRef field) {
            return new Call.Builder().function(ACOS_NAME).withArguments(field).build();
        }

        /**
         * Acos call.
         *
         * @param call the call
         * @return the call
         */
        public static Call acos(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ACOS_NAME).withArguments(call).build();
        }

        /**
         * Asin call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call asin(Wildcard wildcard) {
            return new Call.Builder().function(ASIN_NAME).withArguments(wildcard).build();
        }

        /**
         * Asin call.
         *
         * @param field the field
         * @return the call
         */
        public static Call asin(VarRef field) {
            return new Call.Builder().function(ASIN_NAME).withArguments(field).build();
        }

        /**
         * Asin call.
         *
         * @param call the call
         * @return the call
         */
        public static Call asin(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ASIN_NAME).withArguments(call).build();
        }


        /**
         * Atan call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call atan(Wildcard wildcard) {
            return new Call.Builder().function(ATAN_NAME).withArguments(wildcard).build();
        }

        /**
         * Atan call.
         *
         * @param field the field
         * @return the call
         */
        public static Call atan(VarRef field) {
            return new Call.Builder().function(ATAN_NAME).withArguments(field).build();
        }

        /**
         * Atan call.
         *
         * @param call the call
         * @return the call
         */
        public static Call atan(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ATAN_NAME).withArguments(call).build();
        }

        /**
         * Atan 2 call.
         *
         * @param wildcard  the wildcard
         * @param wildcard2 the wildcard 2
         * @return the call
         */
        public static Call atan2(Wildcard wildcard, Wildcard wildcard2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, wildcard2).build();
        }

        /**
         * Atan 2 call.
         *
         * @param wildcard the wildcard
         * @param varRef   the var ref
         * @return the call
         */
        public static Call atan2(Wildcard wildcard, VarRef varRef) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, varRef).build();
        }

        /**
         * Atan 2 call.
         *
         * @param wildcard      the wildcard
         * @param numberLiteral the number literal
         * @return the call
         */
        public static Call atan2(Wildcard wildcard, NumberLiteral numberLiteral) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, numberLiteral).build();
        }

        /**
         * Atan 2 call.
         *
         * @param field    the field
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call atan2(VarRef field, Wildcard wildcard) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, wildcard).build();
        }

        /**
         * Atan 2 call.
         *
         * @param field         the field
         * @param numberLiteral the number literal
         * @return the call
         */
        public static Call atan2(VarRef field, NumberLiteral numberLiteral) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, numberLiteral).build();
        }

        /**
         * Atan 2 call.
         *
         * @param field  the field
         * @param field2 the field 2
         * @return the call
         */
        public static Call atan2(VarRef field, VarRef field2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, field2).build();
        }

        /**
         * Atan 2 call.
         *
         * @param numberLiteral  the number literal
         * @param numberLiteral2 the number literal 2
         * @return the call
         */
        public static Call atan2(NumberLiteral numberLiteral, NumberLiteral numberLiteral2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, numberLiteral2).build();
        }

        /**
         * Atan 2 call.
         *
         * @param numberLiteral the number literal
         * @param field2        the field 2
         * @return the call
         */
        public static Call atan2(NumberLiteral numberLiteral, VarRef field2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, field2).build();
        }

        /**
         * Atan 2 call.
         *
         * @param numberLiteral the number literal
         * @param wildcard      the wildcard
         * @return the call
         */
        public static Call atan2(NumberLiteral numberLiteral, Wildcard wildcard) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, wildcard).build();
        }

        /**
         * Atan 2 call.
         *
         * @param call  the call
         * @param call2 the call 2
         * @return the call
         */
        public static Call atan2(Call call, Call call2) {
            if (!ALLOWED_NESTED.contains(call.getName()) || !ALLOWED_NESTED.contains(call2.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ATAN2_NAME).withArguments(call, call2).build();
        }

        /**
         * Ceil call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call ceil(Wildcard wildcard) {
            return new Call.Builder().function(CEIL_NAME).withArguments(wildcard).build();
        }

        /**
         * Ceil call.
         *
         * @param field the field
         * @return the call
         */
        public static Call ceil(VarRef field) {
            return new Call.Builder().function(CEIL_NAME).withArguments(field).build();
        }

        /**
         * Ceil call.
         *
         * @param call the call
         * @return the call
         */
        public static Call ceil(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(CEIL_NAME).withArguments(call).build();
        }


        /**
         * Cos call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call cos(Wildcard wildcard) {
            return new Call.Builder().function(COS_NAME).withArguments(wildcard).build();
        }

        /**
         * Cos call.
         *
         * @param field the field
         * @return the call
         */
        public static Call cos(VarRef field) {
            return new Call.Builder().function(COS_NAME).withArguments(field).build();
        }

        /**
         * Cos call.
         *
         * @param call the call
         * @return the call
         */
        public static Call cos(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(COS_NAME).withArguments(call).build();
        }

        /**
         * Cumulative sum call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call cumulativeSum(Wildcard wildcard) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(wildcard).build();
        }

        /**
         * Cumulative sum call.
         *
         * @param field the field
         * @return the call
         */
        public static Call cumulativeSum(VarRef field) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(field).build();
        }

        /**
         * Cumulative sum call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call cumulativeSum(RegexLiteral regexLiteral) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Cumulative sum call.
         *
         * @param call the call
         * @return the call
         */
        public static Call cumulativeSum(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(call).build();
        }

        /**
         * Derivative call.
         *
         * @param wildcard the wildcard
         * @param unit     the unit
         * @return the call
         */
        public static Call derivative(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(wildcard, unit).build();
        }

        /**
         * Derivative call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call derivative(Wildcard wildcard) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(wildcard).build();
        }

        /**
         * Derivative call.
         *
         * @param field the field
         * @param unit  the unit
         * @return the call
         */
        public static Call derivative(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(field, unit).build();
        }

        /**
         * Derivative call.
         *
         * @param field the field
         * @return the call
         */
        public static Call derivative(VarRef field) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(field).build();
        }

        /**
         * Derivative call.
         *
         * @param regexLiteral the regex literal
         * @param unit         the unit
         * @return the call
         */
        public static Call derivative(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(regexLiteral, unit).build();
        }

        /**
         * Derivative call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call derivative(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Derivative call.
         *
         * @param call the call
         * @param unit the unit
         * @return the call
         */
        public static Call derivative(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(call, unit).build();
        }

        /**
         * Derivative call.
         *
         * @param call the call
         * @return the call
         */
        public static Call derivative(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(call).build();
        }

        /**
         * Difference call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call difference(Wildcard wildcard) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(wildcard).build();
        }

        /**
         * Difference call.
         *
         * @param field the field
         * @return the call
         */
        public static Call difference(VarRef field) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(field).build();
        }

        /**
         * Difference call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call difference(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Difference call.
         *
         * @param call the call
         * @return the call
         */
        public static Call difference(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(call).build();
        }

        /**
         * Elapsed call.
         *
         * @param wildcard the wildcard
         * @param unit     the unit
         * @return the call
         */
        public static Call elapsed(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(wildcard, unit).build();
        }

        /**
         * Elapsed call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call elapsed(Wildcard wildcard) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(wildcard).build();
        }

        /**
         * Elapsed call.
         *
         * @param field the field
         * @param unit  the unit
         * @return the call
         */
        public static Call elapsed(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(field, unit).build();
        }

        /**
         * Elapsed call.
         *
         * @param field the field
         * @return the call
         */
        public static Call elapsed(VarRef field) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(field).build();
        }

        /**
         * Elapsed call.
         *
         * @param regexLiteral the regex literal
         * @param unit         the unit
         * @return the call
         */
        public static Call elapsed(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(regexLiteral, unit).build();
        }

        /**
         * Elapsed call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call elapsed(RegexLiteral regexLiteral) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Elapsed call.
         *
         * @param call the call
         * @param unit the unit
         * @return the call
         */
        public static Call elapsed(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ELAPSED_NAME).withArguments(call, unit).build();
        }

        /**
         * Elapsed call.
         *
         * @param call the call
         * @return the call
         */
        public static Call elapsed(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ELAPSED_NAME).withArguments(call).build();
        }

        /**
         * Exp call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call exp(Wildcard wildcard) {
            return new Call.Builder().function(EXP_NAME).withArguments(wildcard).build();
        }

        /**
         * Exp call.
         *
         * @param field the field
         * @return the call
         */
        public static Call exp(VarRef field) {
            return new Call.Builder().function(EXP_NAME).withArguments(field).build();
        }

        /**
         * Exp call.
         *
         * @param call the call
         * @return the call
         */
        public static Call exp(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(EXP_NAME).withArguments(call).build();
        }

        /**
         * Floor call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call floor(Wildcard wildcard) {
            return new Call.Builder().function(FLOOR_NAME).withArguments(wildcard).build();
        }

        /**
         * Floor call.
         *
         * @param field the field
         * @return the call
         */
        public static Call floor(VarRef field) {
            return new Call.Builder().function(FLOOR_NAME).withArguments(field).build();
        }

        /**
         * Floor call.
         *
         * @param call the call
         * @return the call
         */
        public static Call floor(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(FLOOR_NAME).withArguments(call).build();
        }

        /**
         * Ln call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call ln(Wildcard wildcard) {
            return new Call.Builder().function(LN_NAME).withArguments(wildcard).build();
        }

        /**
         * Ln call.
         *
         * @param field the field
         * @return the call
         */
        public static Call ln(VarRef field) {
            return new Call.Builder().function(LN_NAME).withArguments(field).build();
        }

        /**
         * Ln call.
         *
         * @param call the call
         * @return the call
         */
        public static Call ln(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LN_NAME).withArguments(call).build();
        }

        /**
         * Log call.
         *
         * @param wildcard the wildcard
         * @param b        the b
         * @return the call
         */
        public static Call log(Wildcard wildcard, IntegerLiteral b) {
            return new Call.Builder().function(LOG_NAME).withArguments(wildcard, b).build();
        }

        /**
         * Log call.
         *
         * @param field the field
         * @param b     the b
         * @return the call
         */
        public static Call log(VarRef field, IntegerLiteral b) {
            return new Call.Builder().function(LOG_NAME).withArguments(field, b).build();
        }

        /**
         * Log call.
         *
         * @param call the call
         * @param b    the b
         * @return the call
         */
        public static Call log(Call call, IntegerLiteral b) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG_NAME).withArguments(call, b).build();
        }

        /**
         * Log 2 call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call log2(Wildcard wildcard) {
            return new Call.Builder().function(LOG2_NAME).withArguments(wildcard).build();
        }

        /**
         * Log 2 call.
         *
         * @param field the field
         * @return the call
         */
        public static Call log2(VarRef field) {
            return new Call.Builder().function(LOG2_NAME).withArguments(field).build();
        }

        /**
         * Log 2 call.
         *
         * @param call the call
         * @return the call
         */
        public static Call log2(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG2_NAME).withArguments(call).build();
        }

        /**
         * Log 10 call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call log10(Wildcard wildcard) {
            return new Call.Builder().function(LOG10_NAME).withArguments(wildcard).build();
        }

        /**
         * Log 10 call.
         *
         * @param field the field
         * @return the call
         */
        public static Call log10(VarRef field) {
            return new Call.Builder().function(LOG10_NAME).withArguments(field).build();
        }

        /**
         * Log 10 call.
         *
         * @param call the call
         * @return the call
         */
        public static Call log10(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG10_NAME).withArguments(call).build();
        }

        /**
         * Moving average call.
         *
         * @param wildcard the wildcard
         * @param n        the n
         * @return the call
         */
        public static Call movingAverage(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(wildcard, n).build();
        }

        /**
         * Moving average call.
         *
         * @param field the field
         * @param n     the n
         * @return the call
         */
        public static Call movingAverage(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(field, n).build();
        }

        /**
         * Moving average call.
         *
         * @param regexLiteral the regex literal
         * @param n            the n
         * @return the call
         */
        public static Call movingAverage(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(regexLiteral, n).build();
        }

        /**
         * Moving average call.
         *
         * @param call the call
         * @param n    the n
         * @return the call
         */
        public static Call movingAverage(Call call, IntegerLiteral n) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(call, n).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param wildcard the wildcard
         * @param unit     the unit
         * @return the call
         */
        public static Call nonNegativeDerivative(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(wildcard, unit).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call nonNegativeDerivative(Wildcard wildcard) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(wildcard).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param field the field
         * @param unit  the unit
         * @return the call
         */
        public static Call nonNegativeDerivative(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(field, unit).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param field the field
         * @return the call
         */
        public static Call nonNegativeDerivative(VarRef field) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(field).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param regexLiteral the regex literal
         * @param unit         the unit
         * @return the call
         */
        public static Call nonNegativeDerivative(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(regexLiteral, unit).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call nonNegativeDerivative(RegexLiteral regexLiteral) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param call the call
         * @param unit the unit
         * @return the call
         */
        public static Call nonNegativeDerivative(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(call, unit).build();
        }

        /**
         * Non negative derivative call.
         *
         * @param call the call
         * @return the call
         */
        public static Call nonNegativeDerivative(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(call).build();
        }

        /**
         * Non negative difference call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call nonNegativeDifference(Wildcard wildcard) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(wildcard).build();
        }

        /**
         * Non negative difference call.
         *
         * @param field the field
         * @return the call
         */
        public static Call nonNegativeDifference(VarRef field) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(field).build();
        }

        /**
         * Non negative difference call.
         *
         * @param regexLiteral the regex literal
         * @return the call
         */
        public static Call nonNegativeDifference(RegexLiteral regexLiteral) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(regexLiteral).build();
        }

        /**
         * Non negative difference call.
         *
         * @param call the call
         * @return the call
         */
        public static Call nonNegativeDifference(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(call).build();
        }

        /**
         * Pow call.
         *
         * @param wildcard the wildcard
         * @param b        the b
         * @return the call
         */
        public static Call pow(Wildcard wildcard, NumberLiteral b) {
            return new Call.Builder().function(POW_NAME).withArguments(wildcard, b).build();
        }

        /**
         * Pow call.
         *
         * @param field the field
         * @param b     the b
         * @return the call
         */
        public static Call pow(VarRef field, NumberLiteral b) {
            return new Call.Builder().function(POW_NAME).withArguments(field, b).build();
        }

        /**
         * Pow call.
         *
         * @param call the call
         * @param b    the b
         * @return the call
         */
        public static Call pow(Call call, NumberLiteral b) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(POW_NAME).withArguments(call, b).build();
        }

        /**
         * Round call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call round(Wildcard wildcard) {
            return new Call.Builder().function(ROUND_NAME).withArguments(wildcard).build();
        }

        /**
         * Round call.
         *
         * @param field the field
         * @return the call
         */
        public static Call round(VarRef field) {
            return new Call.Builder().function(ROUND_NAME).withArguments(field).build();
        }

        /**
         * Round call.
         *
         * @param call the call
         * @return the call
         */
        public static Call round(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ROUND_NAME).withArguments(call).build();
        }

        /**
         * Sin call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call sin(Wildcard wildcard) {
            return new Call.Builder().function(SIN_NAME).withArguments(wildcard).build();
        }

        /**
         * Sin call.
         *
         * @param field the field
         * @return the call
         */
        public static Call sin(VarRef field) {
            return new Call.Builder().function(SIN_NAME).withArguments(field).build();
        }

        /**
         * Sin call.
         *
         * @param call the call
         * @return the call
         */
        public static Call sin(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(SIN_NAME).withArguments(call).build();
        }

        /**
         * Sqrt call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call sqrt(Wildcard wildcard) {
            return new Call.Builder().function(SQRT_NAME).withArguments(wildcard).build();
        }

        /**
         * Sqrt call.
         *
         * @param field the field
         * @return the call
         */
        public static Call sqrt(VarRef field) {
            return new Call.Builder().function(SQRT_NAME).withArguments(field).build();
        }

        /**
         * Sqrt call.
         *
         * @param call the call
         * @return the call
         */
        public static Call sqrt(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(SQRT_NAME).withArguments(call).build();
        }

        /**
         * Tan call.
         *
         * @param wildcard the wildcard
         * @return the call
         */
        public static Call tan(Wildcard wildcard) {
            return new Call.Builder().function(TAN_NAME).withArguments(wildcard).build();
        }

        /**
         * Tan call.
         *
         * @param field the field
         * @return the call
         */
        public static Call tan(VarRef field) {
            return new Call.Builder().function(TAN_NAME).withArguments(field).build();
        }

        /**
         * Tan call.
         *
         * @param call the call
         * @return the call
         */
        public static Call tan(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(TAN_NAME).withArguments(call).build();
        }
    }

    /**
     * The type Predictors functions.
     */
    public static final class Predictors {
        /**
         * The constant HOLT_WINTERS_NAME.
         */
        public static final String HOLT_WINTERS_NAME = "HOLT_WINTERS";
        /**
         * The constant HOLT_WINTERS_WITH_FIT_NAME.
         */
        public static final String HOLT_WINTERS_WITH_FIT_NAME = "HOLT_WINTERS_WITH_FIT";

        private Predictors() {
            // nothing
        }

        /**
         * Holt winters call.
         *
         * @param call the call
         * @param n    the n
         * @param s    the s
         * @return the call
         */
        public static Call holtWinters(Call call, IntegerLiteral n, IntegerLiteral s) {
            if (call.getArgs() != null && (call.getArgs().size() != 1 || !(call.getArgs().get(0) instanceof VarRef))) {
                throw new IllegalArgumentException("First argument of function must be a field");
            }
            return new Call.Builder().function(HOLT_WINTERS_NAME).withArguments(call, n, s).build();
        }

        /**
         * Holt winters with fit call.
         *
         * @param call the call
         * @param n    the n
         * @param s    the s
         * @return the call
         */
        public static Call holtWintersWithFit(Call call, IntegerLiteral n, IntegerLiteral s) {
            if (call.getArgs() != null && (call.getArgs().size() != 1 || !(call.getArgs().get(0) instanceof VarRef))) {
                throw new IllegalArgumentException("First argument of function must be a field");
            }
            return new Call.Builder().function(HOLT_WINTERS_WITH_FIT_NAME).withArguments(call, n, s).build();
        }

    }

    /**
     * The type Technical analysis functions .
     */
    public static final class TechnicalAnalysis {
        /**
         * The constant CHANDE_MOMENTUM_OSCILLATOR_NAME.
         */
        public static final String CHANDE_MOMENTUM_OSCILLATOR_NAME = "CHANDE_MOMENTUM_OSCILLATOR";
        /**
         * The constant EXPONENTIAL_MOVING_AVERAGE_NAME.
         */
        public static final String EXPONENTIAL_MOVING_AVERAGE_NAME = "EXPONENTIAL_MOVING_AVERAGE";
        /**
         * The constant DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME.
         */
        public static final String DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME = "DOUBLE_EXPONENTIAL_MOVING_AVERAGE";
        /**
         * The constant KAUFMANS_EFFICIENCY_RATIO_NAME.
         */
        public static final String KAUFMANS_EFFICIENCY_RATIO_NAME = "KAUFMANS_EFFICIENCY_RATIO";
        /**
         * The constant KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME.
         */
        public static final String KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME = "KAUFMANS_ADAPTIVE_MOVING_AVERAGE";
        /**
         * The constant TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME.
         */
        public static final String TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME = "TRIPLE_EXPONENTIAL_MOVING_AVERAGE";
        /**
         * The constant TRIPLE_EXPONENTIAL_DERIVATIVE_NAME.
         */
        public static final String TRIPLE_EXPONENTIAL_DERIVATIVE_NAME = "TRIPLE_EXPONENTIAL_DERIVATIVE";
        /**
         * The constant RELATIVE_STRENGTH_INDEX_NAME.
         */
        public static final String RELATIVE_STRENGTH_INDEX_NAME = "RELATIVE_STRENGTH_INDEX";

        private TechnicalAnalysis() {
            // nothing
        }

        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Chande momentum oscillator call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Double exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Double exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Double exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Double exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans effeciency ratio call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansEffeciencyRatio(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }

        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Kaufmans adaptative moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call kaufmansAdaptativeMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }

        /**
         * Triple exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Triple exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Triple exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential moving average call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Triple exponential derivative call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Triple exponential derivative call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Triple exponential derivative call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Triple exponential derivative call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Relative strength index call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Relative strength index call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        /**
         * Relative strength index call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard the wildcard
         * @param period   the period
         * @return the call
         */
        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @return the call
         */
        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        /**
         * Relative strength index call.
         *
         * @param wildcard   the wildcard
         * @param period     the period
         * @param holdPeriod the hold period
         * @param warmupType the warmup type
         * @return the call
         */
        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }
    }
}
