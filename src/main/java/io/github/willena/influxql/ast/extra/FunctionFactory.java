package io.github.willena.influxql.ast.extra;

import io.github.willena.influxql.ast.expr.Call;
import io.github.willena.influxql.ast.expr.VarRef;
import io.github.willena.influxql.ast.expr.Wildcard;
import io.github.willena.influxql.ast.expr.literal.*;

import java.util.List;

public final class FunctionFactory {
    private FunctionFactory() {
        // nothing here !
    }

    public static final class Aggregations {

        public static final String COUNT_NAME = "COUNT";
        public static final String DISTINCT_NAME = "DISTINCT";
        public static final String INTEGRAL_NAME = "INTEGRAL";
        public static final String MEAN_NAME = "MEAN";
        public static final String MEDIAN_NAME = "MEDIAN";
        public static final String MODE_NAME = "MODE";
        public static final String SPREAD_NAME = "SPREAD";
        public static final String STDDEV_NAME = "STDDEV";
        public static final String SUM_NAME = "SUM";

        private Aggregations() {
            // nothing
        }

        public static Call count(Wildcard wildcard) {
            return new Call.Builder().function(COUNT_NAME).withArguments(wildcard).build();
        }

        public static Call count(RegexLiteral regexLiteral) {
            return new Call.Builder().function(COUNT_NAME).withArguments(regexLiteral).build();
        }

        public static Call count(VarRef field) {
            return new Call.Builder().function(COUNT_NAME).withArguments(field).build();
        }

        public static Call count(Call distinct) {
            if (!DISTINCT_NAME.equals(distinct.getName())) {
                throw new IllegalArgumentException("Count only support DISTINCT as nested function");
            }
            return new Call.Builder().function(COUNT_NAME).withArguments(distinct).build();
        }

        public static Call distinct(Wildcard wildcard) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(wildcard).build();
        }

        public static Call distinct(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(regexLiteral).build();
        }

        public static Call distinct(VarRef field) {
            return new Call.Builder().function(DISTINCT_NAME).withArguments(field).build();
        }

        public static Call integral(Wildcard wildcard, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(wildcard, durationLiteral).build();
        }

        public static Call integral(Wildcard wildcard) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(wildcard).build();
        }

        public static Call integral(RegexLiteral regexLiteral, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(regexLiteral, durationLiteral).build();
        }

        public static Call integral(RegexLiteral regexLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(regexLiteral).build();
        }

        public static Call integral(VarRef field, DurationLiteral durationLiteral) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(field, durationLiteral).build();
        }

        public static Call integral(VarRef field) {
            return new Call.Builder().function(INTEGRAL_NAME).withArguments(field).build();
        }

        public static Call mean(Wildcard wildcard) {
            return new Call.Builder().function(MEAN_NAME).withArguments(wildcard).build();
        }

        public static Call mean(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MEAN_NAME).withArguments(regexLiteral).build();
        }

        public static Call mean(VarRef field) {
            return new Call.Builder().function(MEAN_NAME).withArguments(field).build();
        }

        public static Call median(Wildcard wildcard) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(wildcard).build();
        }

        public static Call median(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(regexLiteral).build();
        }

        public static Call median(VarRef field) {
            return new Call.Builder().function(MEDIAN_NAME).withArguments(field).build();
        }

        public static Call mode(Wildcard wildcard) {
            return new Call.Builder().function(MODE_NAME).withArguments(wildcard).build();
        }

        public static Call mode(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MODE_NAME).withArguments(regexLiteral).build();
        }

        public static Call mode(VarRef field) {
            return new Call.Builder().function(MODE_NAME).withArguments(field).build();
        }

        public static Call spread(Wildcard wildcard) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(wildcard).build();
        }

        public static Call spread(RegexLiteral regexLiteral) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(regexLiteral).build();
        }

        public static Call spread(VarRef field) {
            return new Call.Builder().function(SPREAD_NAME).withArguments(field).build();
        }

        public static Call stdDev(Wildcard wildcard) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(wildcard).build();
        }

        public static Call stdDev(RegexLiteral regexLiteral) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(regexLiteral).build();
        }

        public static Call stdDev(VarRef field) {
            return new Call.Builder().function(STDDEV_NAME).withArguments(field).build();
        }

        public static Call sum(Wildcard wildcard) {
            return new Call.Builder().function(SUM_NAME).withArguments(wildcard).build();
        }

        public static Call sum(RegexLiteral regexLiteral) {
            return new Call.Builder().function(SUM_NAME).withArguments(regexLiteral).build();
        }

        public static Call sum(VarRef field) {
            return new Call.Builder().function(SUM_NAME).withArguments(field).build();
        }
    }

    public static final class Selectors {
        public static final String BOTTOM_NAME = "BOTTOM";
        public static final String FIRST_NAME = "FIRST";
        public static final String LAST_NAME = "LAST";
        public static final String MAX_NAME = "MAX";
        public static final String MIN_NAME = "MIN";
        public static final String PERCENTILE_NAME = "PERCENTILE";
        public static final String SAMPLE_NAME = "SAMPLE";
        public static final String TOP_NAME = "TOP";

        private Selectors() {
            // nothing
        }

        public static Call bottom(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(BOTTOM_NAME).withArguments(field, n).build();
        }

        public static Call bottom(VarRef field, VarRef tagKeys, IntegerLiteral n) {
            return new Call.Builder().function(BOTTOM_NAME).withArguments(field, tagKeys, n).build();
        }

        public static Call first(Wildcard wildcard) {
            return new Call.Builder().function(FIRST_NAME).withArguments(wildcard).build();
        }

        public static Call first(RegexLiteral regexLiteral) {
            return new Call.Builder().function(FIRST_NAME).withArguments(regexLiteral).build();
        }

        public static Call first(VarRef field) {
            return new Call.Builder().function(FIRST_NAME).withArguments(field).build();
        }

        public static Call last(Wildcard wildcard) {
            return new Call.Builder().function(LAST_NAME).withArguments(wildcard).build();
        }

        public static Call last(RegexLiteral regexLiteral) {
            return new Call.Builder().function(LAST_NAME).withArguments(regexLiteral).build();
        }

        public static Call last(VarRef field) {
            return new Call.Builder().function(LAST_NAME).withArguments(field).build();
        }

        public static Call max(Wildcard wildcard) {
            return new Call.Builder().function(MAX_NAME).withArguments(wildcard).build();
        }

        public static Call max(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MAX_NAME).withArguments(regexLiteral).build();
        }

        public static Call max(VarRef field) {
            return new Call.Builder().function(MAX_NAME).withArguments(field).build();
        }

        public static Call min(Wildcard wildcard) {
            return new Call.Builder().function(MIN_NAME).withArguments(wildcard).build();
        }

        public static Call min(RegexLiteral regexLiteral) {
            return new Call.Builder().function(MIN_NAME).withArguments(regexLiteral).build();
        }

        public static Call min(VarRef field) {
            return new Call.Builder().function(MIN_NAME).withArguments(field).build();
        }


        public static Call percentile(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(wildcard, n).build();
        }

        public static Call percentile(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(regexLiteral, n).build();
        }

        public static Call percentile(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(PERCENTILE_NAME).withArguments(field, n).build();
        }

        public static Call sample(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(wildcard, n).build();
        }

        public static Call sample(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(regexLiteral, n).build();
        }

        public static Call sample(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(SAMPLE_NAME).withArguments(field, n).build();
        }

        public static Call top(VarRef field, VarRef tag, IntegerLiteral n) {
            return new Call.Builder().function(TOP_NAME).withArguments(field, tag, n).build();
        }

        public static Call top(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(TOP_NAME).withArguments(field, n).build();
        }
    }

    public static final class Transformations {
        public static String ABS_NAME = "ABS";
        public static String ACOS_NAME = "ACOS";
        public static String ASIN_NAME = "ASIN";
        public static String ATAN_NAME = "ATAN";
        public static String ATAN2_NAME = "ATAN2";
        public static String CEIL_NAME = "CEIL";
        public static String COS_NAME = "COS";
        public static String CUMULATIVE_SUM_NAME = "CUMULATIVE_SUM";
        public static String DERIVATIVE_NAME = "DERIVATIVE";
        public static String DIFFERENCE_NAME = "DIFFERENCE";
        public static String ELAPSED_NAME = "ELAPSED";
        public static String EXP_NAME = "EXP";
        public static String FLOOR_NAME = "FLOOR";
        public static String LN_NAME = "LN";
        public static String LOG_NAME = "LOG";
        public static String LOG2_NAME = "LOG2";
        public static String LOG10_NAME = "LOG10";
        public static String MOVING_AVERAGE_NAME = "MOVING_AVERAGE";
        public static String NON_NEGATIVE_DERIVATIVE_NAME = "NON_NEGATIVE_DERIVATIVE";
        public static String NON_NEGATIVE_DIFFERENCE_NAME = "NON_NEGATIVE_DIFFERENCE";
        public static String POW_NAME = "POW";
        public static String ROUND_NAME = "ROUND";
        public static String SIN_NAME = "SIN";
        public static String SQRT_NAME = "SQRT";
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

        public static Call abs(Wildcard wildcard) {
            return new Call.Builder().function(ABS_NAME).withArguments(wildcard).build();
        }

        public static Call abs(VarRef field) {
            return new Call.Builder().function(ABS_NAME).withArguments(field).build();
        }

        public static Call abs(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ABS_NAME).withArguments(call).build();
        }

        public static Call acos(Wildcard wildcard) {
            return new Call.Builder().function(ACOS_NAME).withArguments(wildcard).build();
        }

        public static Call acos(VarRef field) {
            return new Call.Builder().function(ACOS_NAME).withArguments(field).build();
        }

        public static Call acos(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ACOS_NAME).withArguments(call).build();
        }

        public static Call asin(Wildcard wildcard) {
            return new Call.Builder().function(ASIN_NAME).withArguments(wildcard).build();
        }

        public static Call asin(VarRef field) {
            return new Call.Builder().function(ASIN_NAME).withArguments(field).build();
        }

        public static Call asin(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ASIN_NAME).withArguments(call).build();
        }


        public static Call atan(Wildcard wildcard) {
            return new Call.Builder().function(ATAN_NAME).withArguments(wildcard).build();
        }

        public static Call atan(VarRef field) {
            return new Call.Builder().function(ATAN_NAME).withArguments(field).build();
        }

        public static Call atan(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ATAN_NAME).withArguments(call).build();
        }

        public static Call atan2(Wildcard wildcard, Wildcard wildcard2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, wildcard2).build();
        }

        public static Call atan2(Wildcard wildcard, VarRef varRef) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, varRef).build();
        }

        public static Call atan2(Wildcard wildcard, NumberLiteral numberLiteral) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(wildcard, numberLiteral).build();
        }

        public static Call atan2(VarRef field, Wildcard wildcard) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, wildcard).build();
        }

        public static Call atan2(VarRef field, NumberLiteral numberLiteral) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, numberLiteral).build();
        }

        public static Call atan2(VarRef field, VarRef field2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(field, field2).build();
        }

        public static Call atan2(NumberLiteral numberLiteral, NumberLiteral numberLiteral2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, numberLiteral2).build();
        }

        public static Call atan2(NumberLiteral numberLiteral, VarRef field2) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, field2).build();
        }

        public static Call atan2(NumberLiteral numberLiteral, Wildcard wildcard) {
            return new Call.Builder().function(ATAN2_NAME).withArguments(numberLiteral, wildcard).build();
        }

        public static Call atan2(Call call, Call call2) {
            if (!ALLOWED_NESTED.contains(call.getName()) || !ALLOWED_NESTED.contains(call2.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ATAN2_NAME).withArguments(call, call2).build();
        }

        public static Call ceil(Wildcard wildcard) {
            return new Call.Builder().function(CEIL_NAME).withArguments(wildcard).build();
        }

        public static Call ceil(VarRef field) {
            return new Call.Builder().function(CEIL_NAME).withArguments(field).build();
        }

        public static Call ceil(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(CEIL_NAME).withArguments(call).build();
        }


        public static Call cos(Wildcard wildcard) {
            return new Call.Builder().function(COS_NAME).withArguments(wildcard).build();
        }

        public static Call cos(VarRef field) {
            return new Call.Builder().function(COS_NAME).withArguments(field).build();
        }

        public static Call cos(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(COS_NAME).withArguments(call).build();
        }

        public static Call cumulativeSum(Wildcard wildcard) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(wildcard).build();
        }

        public static Call cumulativeSum(VarRef field) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(field).build();
        }

        public static Call cumulativeSum(RegexLiteral regexLiteral) {
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(regexLiteral).build();
        }

        public static Call cumulativeSum(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(CUMULATIVE_SUM_NAME).withArguments(call).build();
        }

        public static Call derivative(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(wildcard, unit).build();
        }

        public static Call derivative(Wildcard wildcard) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(wildcard).build();
        }

        public static Call derivative(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(field, unit).build();
        }

        public static Call derivative(VarRef field) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(field).build();
        }

        public static Call derivative(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(regexLiteral, unit).build();
        }

        public static Call derivative(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(regexLiteral).build();
        }

        public static Call derivative(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(call, unit).build();
        }

        public static Call derivative(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DERIVATIVE_NAME).withArguments(call).build();
        }

        public static Call difference(Wildcard wildcard) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(wildcard).build();
        }

        public static Call difference(VarRef field) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(field).build();
        }

        public static Call difference(RegexLiteral regexLiteral) {
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(regexLiteral).build();
        }

        public static Call difference(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(DIFFERENCE_NAME).withArguments(call).build();
        }

        public static Call elapsed(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(wildcard, unit).build();
        }

        public static Call elapsed(Wildcard wildcard) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(wildcard).build();
        }

        public static Call elapsed(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(field, unit).build();
        }

        public static Call elapsed(VarRef field) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(field).build();
        }

        public static Call elapsed(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(regexLiteral, unit).build();
        }

        public static Call elapsed(RegexLiteral regexLiteral) {
            return new Call.Builder().function(ELAPSED_NAME).withArguments(regexLiteral).build();
        }

        public static Call elapsed(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ELAPSED_NAME).withArguments(call, unit).build();
        }

        public static Call elapsed(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ELAPSED_NAME).withArguments(call).build();
        }

        public static Call exp(Wildcard wildcard) {
            return new Call.Builder().function(EXP_NAME).withArguments(wildcard).build();
        }

        public static Call exp(VarRef field) {
            return new Call.Builder().function(EXP_NAME).withArguments(field).build();
        }

        public static Call exp(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(EXP_NAME).withArguments(call).build();
        }

        public static Call floor(Wildcard wildcard) {
            return new Call.Builder().function(FLOOR_NAME).withArguments(wildcard).build();
        }

        public static Call floor(VarRef field) {
            return new Call.Builder().function(FLOOR_NAME).withArguments(field).build();
        }

        public static Call floor(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(FLOOR_NAME).withArguments(call).build();
        }

        public static Call ln(Wildcard wildcard) {
            return new Call.Builder().function(LN_NAME).withArguments(wildcard).build();
        }

        public static Call ln(VarRef field) {
            return new Call.Builder().function(LN_NAME).withArguments(field).build();
        }

        public static Call ln(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LN_NAME).withArguments(call).build();
        }

        public static Call log(Wildcard wildcard, IntegerLiteral b) {
            return new Call.Builder().function(LOG_NAME).withArguments(wildcard, b).build();
        }

        public static Call log(VarRef field, IntegerLiteral b) {
            return new Call.Builder().function(LOG_NAME).withArguments(field, b).build();
        }

        public static Call log(Call call, IntegerLiteral b) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG_NAME).withArguments(call, b).build();
        }

        public static Call log2(Wildcard wildcard) {
            return new Call.Builder().function(LOG2_NAME).withArguments(wildcard).build();
        }

        public static Call log2(VarRef field) {
            return new Call.Builder().function(LOG2_NAME).withArguments(field).build();
        }

        public static Call log2(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG2_NAME).withArguments(call).build();
        }

        public static Call log10(Wildcard wildcard) {
            return new Call.Builder().function(LOG10_NAME).withArguments(wildcard).build();
        }

        public static Call log10(VarRef field) {
            return new Call.Builder().function(LOG10_NAME).withArguments(field).build();
        }

        public static Call log10(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(LOG10_NAME).withArguments(call).build();
        }

        public static Call movingAverage(Wildcard wildcard, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(wildcard, n).build();
        }

        public static Call movingAverage(VarRef field, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(field, n).build();
        }

        public static Call movingAverage(RegexLiteral regexLiteral, IntegerLiteral n) {
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(regexLiteral, n).build();
        }

        public static Call movingAverage(Call call, IntegerLiteral n) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(MOVING_AVERAGE_NAME).withArguments(call, n).build();
        }

        public static Call nonNegativeDerivative(Wildcard wildcard, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(wildcard, unit).build();
        }

        public static Call nonNegativeDerivative(Wildcard wildcard) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(wildcard).build();
        }

        public static Call nonNegativeDerivative(VarRef field, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(field, unit).build();
        }

        public static Call nonNegativeDerivative(VarRef field) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(field).build();
        }

        public static Call nonNegativeDerivative(RegexLiteral regexLiteral, DurationLiteral unit) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(regexLiteral, unit).build();
        }

        public static Call nonNegativeDerivative(RegexLiteral regexLiteral) {
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(regexLiteral).build();
        }

        public static Call nonNegativeDerivative(Call call, DurationLiteral unit) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(call, unit).build();
        }

        public static Call nonNegativeDerivative(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DERIVATIVE_NAME).withArguments(call).build();
        }

        public static Call nonNegativeDifference(Wildcard wildcard) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(wildcard).build();
        }

        public static Call nonNegativeDifference(VarRef field) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(field).build();
        }

        public static Call nonNegativeDifference(RegexLiteral regexLiteral) {
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(regexLiteral).build();
        }

        public static Call nonNegativeDifference(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(NON_NEGATIVE_DIFFERENCE_NAME).withArguments(call).build();
        }

        public static Call pow(Wildcard wildcard, NumberLiteral b) {
            return new Call.Builder().function(POW_NAME).withArguments(wildcard, b).build();
        }

        public static Call pow(VarRef field, NumberLiteral b) {
            return new Call.Builder().function(POW_NAME).withArguments(field, b).build();
        }

        public static Call pow(Call call, NumberLiteral b) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(POW_NAME).withArguments(call, b).build();
        }

        public static Call round(Wildcard wildcard) {
            return new Call.Builder().function(ROUND_NAME).withArguments(wildcard).build();
        }

        public static Call round(VarRef field) {
            return new Call.Builder().function(ROUND_NAME).withArguments(field).build();
        }

        public static Call round(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(ROUND_NAME).withArguments(call).build();
        }

        public static Call sin(Wildcard wildcard) {
            return new Call.Builder().function(SIN_NAME).withArguments(wildcard).build();
        }

        public static Call sin(VarRef field) {
            return new Call.Builder().function(SIN_NAME).withArguments(field).build();
        }

        public static Call sin(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(SIN_NAME).withArguments(call).build();
        }

        public static Call sqrt(Wildcard wildcard) {
            return new Call.Builder().function(SQRT_NAME).withArguments(wildcard).build();
        }

        public static Call sqrt(VarRef field) {
            return new Call.Builder().function(SQRT_NAME).withArguments(field).build();
        }

        public static Call sqrt(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(SQRT_NAME).withArguments(call).build();
        }

        public static Call tan(Wildcard wildcard) {
            return new Call.Builder().function(TAN_NAME).withArguments(wildcard).build();
        }

        public static Call tan(VarRef field) {
            return new Call.Builder().function(TAN_NAME).withArguments(field).build();
        }

        public static Call tan(Call call) {
            if (!ALLOWED_NESTED.contains(call.getName())) {
                throw new IllegalArgumentException(call.getName() + " not supported as nested function");
            }
            return new Call.Builder().function(TAN_NAME).withArguments(call).build();
        }
    }

    public static final class Predictors {
        public static final String HOLT_WINTERS_NAME = "HOLT_WINTERS";
        public static final String HOLT_WINTERS_WITH_FIT_NAME = "HOLT_WINTERS_WITH_FIT";

        private Predictors() {
            // nothing
        }

        public static Call holtWinters(Call call, IntegerLiteral n, IntegerLiteral s) {
            if (call.getArgs() != null && call.getArgs().size() != 1 && !(call.getArgs().get(0) instanceof VarRef)) {
                throw new IllegalArgumentException("First argument of function must be a field");
            }
            return new Call.Builder().function(HOLT_WINTERS_NAME).withArguments(call, n, s).build();
        }

        public static Call holtWintersWithFit(Call call, IntegerLiteral n, IntegerLiteral s) {
            if (call.getArgs() != null && call.getArgs().size() != 1 && !(call.getArgs().get(0) instanceof VarRef)) {
                throw new IllegalArgumentException("First argument of function must be a field");
            }
            return new Call.Builder().function(HOLT_WINTERS_WITH_FIT_NAME).withArguments(call, n, s).build();
        }

    }

    public static final class TechnicalAnalysis {
        public static final String CHANDE_MOMENTUM_OSCILLATOR_NAME = "CHANDE_MOMENTUM_OSCILLATOR";
        public static final String EXPONENTIAL_MOVING_AVERAGE_NAME = "EXPONENTIAL_MOVING_AVERAGE";
        public static final String DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME = "DOUBLE_EXPONENTIAL_MOVING_AVERAGE";
        public static final String KAUFMANS_EFFICIENCY_RATIO_NAME = "KAUFMANS_EFFICIENCY_RATIO";
        public static final String KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME = "KAUFMANS_ADAPTIVE_MOVING_AVERAGE";
        public static final String TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME = "TRIPLE_EXPONENTIAL_MOVING_AVERAGE";
        public static final String TRIPLE_EXPONENTIAL_DERIVATIVE_NAME = "TRIPLE_EXPONENTIAL_DERIVATIVE";
        public static final String RELATIVE_STRENGTH_INDEX_NAME = "RELATIVE_STRENGTH_INDEX";

        private TechnicalAnalysis() {
            // nothing
        }

        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call chandeMomentumOscillator(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call chandeMomentumOscillator(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call chandeMomentumOscillator(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period).build();
        }


        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call chandeMomentumOscillator(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(CHANDE_MOMENTUM_OSCILLATOR_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call exponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call exponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call exponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call exponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call doubleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call doubleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call doubleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call doubleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(DOUBLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call kaufmansEffeciencyRatio(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansEffeciencyRatio(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansEffeciencyRatio(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansEffeciencyRatio(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansEffeciencyRatio(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansEffeciencyRatio(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansEffeciencyRatio(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansEffeciencyRatio(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_EFFICIENCY_RATIO_NAME).withArguments(wildcard, period, holdPeriod).build();
        }

        public static Call kaufmansAdaptativeMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call kaufmansAdaptativeMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(KAUFMANS_ADAPTIVE_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }

        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialMovingAverage(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialMovingAverage(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialMovingAverage(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialMovingAverage(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_MOVING_AVERAGE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialDerivative(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialDerivative(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialDerivative(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period).build();
        }


        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call tripleExponentialDerivative(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(TRIPLE_EXPONENTIAL_DERIVATIVE_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call relativeStrengthIndex(Wildcard wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call relativeStrengthIndex(VarRef wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }

        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call relativeStrengthIndex(RegexLiteral wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }


        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period).build();
        }


        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod).build();
        }


        public static Call relativeStrengthIndex(Call wildcard, IntegerLiteral period, IntegerLiteral holdPeriod, StringLiteral warmupType) {
            return new Call.Builder().function(RELATIVE_STRENGTH_INDEX_NAME).withArguments(wildcard, period, holdPeriod, warmupType).build();
        }
    }
}
