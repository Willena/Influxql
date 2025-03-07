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

import static io.github.willena.influxql.ast.utils.Utils.parseDuration;

import io.github.willena.influxql.ast.*;
import io.github.willena.influxql.ast.expr.*;
import io.github.willena.influxql.ast.expr.literal.*;
import io.github.willena.influxql.ast.extra.RetentionPolicy;
import io.github.willena.influxql.ast.field.Field;
import io.github.willena.influxql.ast.field.Fields;
import io.github.willena.influxql.ast.field.SortField;
import io.github.willena.influxql.ast.field.SortFields;
import io.github.willena.influxql.ast.source.Measurement;
import io.github.willena.influxql.ast.source.Sources;
import io.github.willena.influxql.ast.source.SubQuery;
import io.github.willena.influxql.ast.source.Target;
import io.github.willena.influxql.ast.statement.*;
import io.github.willena.influxql.ast.token.DestinationMode;
import io.github.willena.influxql.ast.token.FillOption;
import io.github.willena.influxql.ast.token.Operator;
import io.github.willena.influxql.ast.token.Privilege;
import io.github.willena.influxql.ast.utils.StringJoiningList;
import io.github.willena.influxql.ast.utils.TimezoneNode;
import io.github.willena.influxql.ast.utils.Utils;
import io.github.willena.influxql.parser.antlr.InfluxqlParser;
import io.github.willena.influxql.parser.antlr.InfluxqlParserBaseVisitor;
import java.time.Duration;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

public class InfluxqlAstAdapter extends InfluxqlParserBaseVisitor<Node> {

    protected InfluxqlAstAdapter() {
        // Empty
    }

    private static String trimStringQuotes(String value) {
        if (value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1).replaceAll("\\\\'", "'");
        }
        return value;
    }

    private static String trimIdentifierQuotes(String identifier) {
        if (identifier.startsWith("\"") && identifier.endsWith("\"")) {
            return identifier.substring(1, identifier.length() - 1);
        }
        return identifier;
    }

    private static String trimRegexChars(String regex) {
        if (regex.startsWith("/") && regex.endsWith("/")) {
            return regex.substring(1, regex.length() - 1);
        }
        return regex;
    }

    @Override
    public Query visitQuery(InfluxqlParser.QueryContext ctx) {
        return new Query(
                ctx.statement().stream()
                        .map(s -> (Statement) visitStatement(s))
                        .collect(Collectors.toList()));
    }

    @Override
    public Node visitStatement(InfluxqlParser.StatementContext ctx) {
        if (ctx.alter_retention_policy_stmt() != null) {
            return visitAlter_retention_policy_stmt(ctx.alter_retention_policy_stmt());
        } else if (ctx.create_continuous_query_stmt() != null) {
            return visitCreate_continuous_query_stmt(ctx.create_continuous_query_stmt());
        } else if (ctx.create_database_stmt() != null) {
            return visitCreate_database_stmt(ctx.create_database_stmt());
        } else if (ctx.create_retention_policy_stmt() != null) {
            return visitCreate_retention_policy_stmt(ctx.create_retention_policy_stmt());
        } else if (ctx.create_subscription_stmt() != null) {
            return visitCreate_subscription_stmt(ctx.create_subscription_stmt());
        } else if (ctx.create_user_stmt() != null) {
            return visitCreate_user_stmt(ctx.create_user_stmt());
        } else if (ctx.delete_stmt() != null) {
            return visitDelete_stmt(ctx.delete_stmt());
        } else if (ctx.drop_continuous_query_stmt() != null) {
            return visitDrop_continuous_query_stmt(ctx.drop_continuous_query_stmt());
        } else if (ctx.drop_database_stmt() != null) {
            return visitDrop_database_stmt(ctx.drop_database_stmt());
        } else if (ctx.drop_measurement_stmt() != null) {
            return visitDrop_measurement_stmt(ctx.drop_measurement_stmt());
        } else if (ctx.drop_retention_policy_stmt() != null) {
            return visitDrop_retention_policy_stmt(ctx.drop_retention_policy_stmt());
        } else if (ctx.drop_series_stmt() != null) {
            return visitDrop_series_stmt(ctx.drop_series_stmt());
        } else if (ctx.drop_shard_stmt() != null) {
            return visitDrop_shard_stmt(ctx.drop_shard_stmt());
        } else if (ctx.drop_subscription_stmt() != null) {
            return visitDrop_subscription_stmt(ctx.drop_subscription_stmt());
        } else if (ctx.drop_user_stmt() != null) {
            return visitDrop_user_stmt(ctx.drop_user_stmt());
        } else if (ctx.explain_stmt() != null) {
            return visitExplain_stmt(ctx.explain_stmt());
        } else if (ctx.grant_stmt() != null) {
            return visitGrant_stmt(ctx.grant_stmt());
        } else if (ctx.kill_query_statement() != null) {
            return visitKill_query_statement(ctx.kill_query_statement());
        } else if (ctx.show_continuous_queries_stmt() != null) {
            return visitShow_continuous_queries_stmt(ctx.show_continuous_queries_stmt());
        } else if (ctx.show_databases_stmt() != null) {
            return visitShow_databases_stmt(ctx.show_databases_stmt());
        } else if (ctx.show_field_keys_stmt() != null) {
            return visitShow_field_keys_stmt(ctx.show_field_keys_stmt());
        } else if (ctx.show_grants_stmt() != null) {
            return visitShow_grants_stmt(ctx.show_grants_stmt());
        } else if (ctx.show_measurements_stmt() != null) {
            return visitShow_measurements_stmt(ctx.show_measurements_stmt());
        } else if (ctx.show_measurement_cardinality_stmt() != null) {
            return visitShow_measurement_cardinality_stmt(ctx.show_measurement_cardinality_stmt());
        } else if (ctx.show_queries_stmt() != null) {
            return visitShow_queries_stmt(ctx.show_queries_stmt());
        } else if (ctx.show_retention_policies_stmt() != null) {
            return visitShow_retention_policies_stmt(ctx.show_retention_policies_stmt());
        } else if (ctx.show_series_stmt() != null) {
            return visitShow_series_stmt(ctx.show_series_stmt());
        } else if (ctx.show_series_cardinality_stmt() != null) {
            return visitShow_series_cardinality_stmt(ctx.show_series_cardinality_stmt());
        } else if (ctx.show_shard_groups_stmt() != null) {
            return visitShow_shard_groups_stmt(ctx.show_shard_groups_stmt());
        } else if (ctx.show_shards_stmt() != null) {
            return visitShow_shards_stmt(ctx.show_shards_stmt());
        } else if (ctx.show_subscriptions_stmt() != null) {
            return visitShow_subscriptions_stmt(ctx.show_subscriptions_stmt());
        } else if (ctx.show_tag_keys_stmt() != null) {
            return visitShow_tag_keys_stmt(ctx.show_tag_keys_stmt());
        } else if (ctx.show_tag_key_cardinality_stmt() != null) {
            return visitShow_tag_key_cardinality_stmt(ctx.show_tag_key_cardinality_stmt());
        } else if (ctx.show_tag_values_stmt() != null) {
            return visitShow_tag_values_stmt(ctx.show_tag_values_stmt());
        } else if (ctx.show_tag_values_cardinality_stmt() != null) {
            return visitShow_tag_values_cardinality_stmt(ctx.show_tag_values_cardinality_stmt());
        } else if (ctx.show_field_key_cardinality_stmt() != null) {
            return visitShow_field_key_cardinality_stmt(ctx.show_field_key_cardinality_stmt());
        } else if (ctx.show_users_stmt() != null) {
            return visitShow_users_stmt(ctx.show_users_stmt());
        } else if (ctx.show_diagnostics_stmt() != null) {
            return visitShow_diagnostics_stmt(ctx.show_diagnostics_stmt());
        } else if (ctx.show_stats_stmt() != null) {
            return visitShow_stats_stmt(ctx.show_stats_stmt());
        } else if (ctx.revoke_stmt() != null) {
            return visitRevoke_stmt(ctx.revoke_stmt());
        } else if (ctx.select_stmt() != null) {
            return visitSelect_stmt(ctx.select_stmt());
        } else {
            throw new UnsupportedOperationException(
                    "Unknown statement; Code might not reflect grammar. Please update code / grammar");
        }
    }

    @Override
    public ShowStatsStatement visitShow_stats_stmt(InfluxqlParser.Show_stats_stmtContext ctx) {
        return new ShowStatsStatement.Builder()
                .for_(
                        Optional.ofNullable(ctx.STRING_LITERAL())
                                .map(ParseTree::getText)
                                .map(InfluxqlAstAdapter::trimStringQuotes)
                                .orElse(null))
                .build();
    }

    @Override
    public ShowSeriesCardinalityStatement visitShow_series_cardinality_stmt(
            InfluxqlParser.Show_series_cardinality_stmtContext ctx) {
        return new ShowSeriesCardinalityStatement.Builder()
                .exact(Optional.ofNullable(ctx.EXACT()).isPresent())
                .on(Optional.ofNullable(ctx.on_clause()).map(c -> c.db_name.getText()).orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .groupBy(
                        Optional.ofNullable(ctx.group_by_clause())
                                .map(this::visitGroup_by_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .build();
    }

    @Override
    public ShowMeasurementCardinalityStatement visitShow_measurement_cardinality_stmt(
            InfluxqlParser.Show_measurement_cardinality_stmtContext ctx) {
        return new ShowMeasurementCardinalityStatement.Builder()
                .exact(Optional.ofNullable(ctx.EXACT()).isPresent())
                .on(Optional.ofNullable(ctx.on_clause()).map(c -> c.db_name.getText()).orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .groupBy(
                        Optional.ofNullable(ctx.group_by_clause())
                                .map(this::visitGroup_by_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .build();
    }

    @Override
    public ShowFieldKeyCardinalityStatement visitShow_field_key_cardinality_stmt(
            InfluxqlParser.Show_field_key_cardinality_stmtContext ctx) {
        return new ShowFieldKeyCardinalityStatement.Builder()
                .exact(Optional.ofNullable(ctx.EXACT()).isPresent())
                .on(Optional.ofNullable(ctx.on_clause()).map(c -> c.db_name.getText()).orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .groupBy(
                        Optional.ofNullable(ctx.group_by_clause())
                                .map(this::visitGroup_by_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(l -> l.INTEGER_LITERAL().getText())
                                .map(Integer::parseInt)
                                .orElse(0))
                .build();
    }

    @Override
    public ShowDiagnosticsStatement visitShow_diagnostics_stmt(
            InfluxqlParser.Show_diagnostics_stmtContext ctx) {
        return new ShowDiagnosticsStatement.Builder()
                .for_(
                        Optional.ofNullable(ctx.STRING_LITERAL())
                                .map(ParseTree::getText)
                                .map(InfluxqlAstAdapter::trimStringQuotes)
                                .orElse(null))
                .build();
    }

    @Override
    public AlterRetentionPolicyStatement visitAlter_retention_policy_stmt(
            InfluxqlParser.Alter_retention_policy_stmtContext ctx) {
        AlterRetentionPolicyStatement.Builder builder = new AlterRetentionPolicyStatement.Builder();

        for (InfluxqlParser.Retention_policy_optionContext retentionPolicyOptionContext :
                ctx.retention_policy_option()) {
            if (retentionPolicyOptionContext.retention_policy_replication() != null) {
                builder.replicationFactor(
                        Integer.parseInt(
                                retentionPolicyOptionContext
                                        .retention_policy_replication()
                                        .INTEGER_LITERAL()
                                        .getText()));
            } else if (retentionPolicyOptionContext.retention_policy_duration() != null) {
                builder.duration(
                        parseDuration(
                                retentionPolicyOptionContext
                                        .retention_policy_duration()
                                        .DURATION_LITERAL()
                                        .getText()));
            } else if (retentionPolicyOptionContext.retention_policy_shard_group_duration()
                    != null) {
                builder.shardDuration(
                        parseDuration(
                                retentionPolicyOptionContext
                                        .retention_policy_shard_group_duration()
                                        .DURATION_LITERAL()
                                        .getText()));
            } else if (retentionPolicyOptionContext.DEFAULT() != null) {
                builder.default_();
            } else if (retentionPolicyOptionContext.retention_policy_future_limit() != null) {
                builder.futureLimit(
                        parseDuration(
                                retentionPolicyOptionContext
                                        .retention_policy_future_limit()
                                        .DURATION_LITERAL()
                                        .getText()));
            } else if (retentionPolicyOptionContext.retention_policy_past_limit() != null) {
                builder.pastLimit(
                        parseDuration(
                                retentionPolicyOptionContext
                                        .retention_policy_past_limit()
                                        .DURATION_LITERAL()
                                        .getText()));
            }
        }

        return builder.on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .policyName(trimIdentifierQuotes(ctx.policy_name.getText()))
                .build();
    }

    @Override
    public CreateContinuousQueryStatement visitCreate_continuous_query_stmt(
            InfluxqlParser.Create_continuous_query_stmtContext ctx) {
        CreateContinuousQueryStatement.Builder builder =
                new CreateContinuousQueryStatement.Builder();

        if (ctx.resample_opts() != null) {
            if (ctx.resample_opts().for_stmt() != null) {
                builder.for_(
                        parseDuration(ctx.resample_opts().for_stmt().DURATION_LITERAL().getText()));
            }
            if (ctx.resample_opts().every_stmt() != null) {
                builder.resampleEvery(
                        parseDuration(
                                ctx.resample_opts().every_stmt().DURATION_LITERAL().getText()));
            }
        }

        return builder.name(trimIdentifierQuotes(ctx.query_name.getText()))
                .on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .select(visitSelect_stmt(ctx.select_stmt()))
                .build();
    }

    @Override
    public CreateDatabaseStatement visitCreate_database_stmt(
            InfluxqlParser.Create_database_stmtContext ctx) {

        RetentionPolicy rp =
                new RetentionPolicy.Builder()
                        .retentionPolicyName(
                                Optional.ofNullable(ctx.retention_policy_name())
                                        .map(
                                                InfluxqlParser.Retention_policy_nameContext
                                                        ::IDENTIFIER)
                                        .map(ParseTree::getText)
                                        .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                        .orElse(null))
                        .duration(
                                Optional.ofNullable(ctx.retention_policy_duration())
                                        .map(
                                                InfluxqlParser.Retention_policy_durationContext
                                                        ::DURATION_LITERAL)
                                        .map(ParseTree::getText)
                                        .map(Utils::parseDuration)
                                        .orElse(null))
                        .shardDuration(
                                Optional.ofNullable(ctx.retention_policy_shard_group_duration())
                                        .map(
                                                InfluxqlParser
                                                                .Retention_policy_shard_group_durationContext
                                                        ::DURATION_LITERAL)
                                        .map(ParseTree::getText)
                                        .map(Utils::parseDuration)
                                        .orElse(null))
                        .replication(
                                Optional.ofNullable(ctx.retention_policy_replication())
                                        .map(
                                                InfluxqlParser.Retention_policy_replicationContext
                                                        ::INTEGER_LITERAL)
                                        .map(ParseTree::getText)
                                        .map(Integer::parseInt)
                                        .orElse(null))
                        .futureLimit(
                                Optional.ofNullable(ctx.retention_policy_future_limit())
                                        .map(
                                                InfluxqlParser.Retention_policy_future_limitContext
                                                        ::DURATION_LITERAL)
                                        .map(ParseTree::getText)
                                        .map(Utils::parseDuration)
                                        .orElse(null))
                        .pastLimit(
                                Optional.ofNullable(ctx.retention_policy_past_limit())
                                        .map(
                                                InfluxqlParser.Retention_policy_past_limitContext
                                                        ::DURATION_LITERAL)
                                        .map(ParseTree::getText)
                                        .map(Utils::parseDuration)
                                        .orElse(null))
                        .build();

        return new CreateDatabaseStatement.Builder()
                .name(trimIdentifierQuotes(ctx.db_name.getText()))
                .withRetentionPolicy(rp)
                .build();
    }

    @Override
    public CreateRetentionPolicyStatement visitCreate_retention_policy_stmt(
            InfluxqlParser.Create_retention_policy_stmtContext ctx) {

        Duration shardDuration =
                Optional.ofNullable(ctx.retention_policy_shard_group_duration())
                        .map(
                                InfluxqlParser.Retention_policy_shard_group_durationContext
                                        ::DURATION_LITERAL)
                        .map(ParseTree::getText)
                        .map(Utils::parseDuration)
                        .orElse(null);

        Duration futureLimit =
                Optional.ofNullable(ctx.retention_policy_future_limit())
                        .map(InfluxqlParser.Retention_policy_future_limitContext::DURATION_LITERAL)
                        .map(ParseTree::getText)
                        .map(Utils::parseDuration)
                        .orElse(null);
        Duration pastLimit =
                Optional.ofNullable(ctx.retention_policy_past_limit())
                        .map(InfluxqlParser.Retention_policy_past_limitContext::DURATION_LITERAL)
                        .map(ParseTree::getText)
                        .map(Utils::parseDuration)
                        .orElse(null);

        return new CreateRetentionPolicyStatement.Builder()
                .name(trimIdentifierQuotes(ctx.policy_name.getText()))
                .on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .duration(
                        parseDuration(ctx.retention_policy_duration().DURATION_LITERAL().getText()))
                .replication(
                        Integer.parseInt(
                                ctx.retention_policy_replication().INTEGER_LITERAL().getText()))
                .withIsDefault(ctx.DEFAULT() != null)
                .shardDuration(shardDuration)
                .futureLimit(futureLimit)
                .pastLimit(pastLimit)
                .build();
    }

    @Override
    public CreateSubscriptionStatement visitCreate_subscription_stmt(
            InfluxqlParser.Create_subscription_stmtContext ctx) {
        CreateSubscriptionStatement.Builder builder = new CreateSubscriptionStatement.Builder();
        boolean isAny = ctx.ANY() != null;
        boolean isAll = ctx.ALL() != null;

        if (isAny) {
            builder.destinationMode(DestinationMode.ANY);
        }
        if (isAll) {
            builder.destinationMode(DestinationMode.ALL);
        }

        return builder.name(trimIdentifierQuotes(ctx.subscription_name.getText()))
                .on(trimIdentifierQuotes(ctx.db_name.getText()))
                .retentionPolicy(trimIdentifierQuotes(ctx.retention_policy.getText()))
                .destinations(
                        ctx.STRING_LITERAL().stream()
                                .map(ParseTree::getText)
                                .map(InfluxqlAstAdapter::trimStringQuotes)
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CreateUserStatement visitCreate_user_stmt(InfluxqlParser.Create_user_stmtContext ctx) {
        return new CreateUserStatement.Builder()
                .username(trimIdentifierQuotes(ctx.user_name.getText()))
                .password(trimStringQuotes(ctx.password.getText()))
                .admin(ctx.PRIVILEGES() != null)
                .build();
    }

    @Override
    public DeleteSeriesStatement visitDelete_stmt(InfluxqlParser.Delete_stmtContext ctx) {
        return new DeleteSeriesStatement.Builder()
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .build();
    }

    @Override
    public DropContinuousQueryStatement visitDrop_continuous_query_stmt(
            InfluxqlParser.Drop_continuous_query_stmtContext ctx) {
        return new DropContinuousQueryStatement.Builder()
                .query(trimIdentifierQuotes(ctx.query_name.getText()))
                .on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .build();
    }

    @Override
    public DropDatabaseStatement visitDrop_database_stmt(
            InfluxqlParser.Drop_database_stmtContext ctx) {
        return new DropDatabaseStatement.Builder()
                .database(trimIdentifierQuotes(ctx.db_name.getText()))
                .build();
    }

    @Override
    public DropMeasurementStatement visitDrop_measurement_stmt(
            InfluxqlParser.Drop_measurement_stmtContext ctx) {

        return new DropMeasurementStatement.Builder()
                .measurement(trimIdentifierQuotes(ctx.measurement_value.getText()))
                .build();
    }

    @Override
    public DropRetentionPolicyStatement visitDrop_retention_policy_stmt(
            InfluxqlParser.Drop_retention_policy_stmtContext ctx) {
        return new DropRetentionPolicyStatement.Builder()
                .on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .retentionPolicy(trimIdentifierQuotes(ctx.policy_name.getText()))
                .build();
    }

    @Override
    public DropSeriesStatement visitDrop_series_stmt(InfluxqlParser.Drop_series_stmtContext ctx) {
        return new DropSeriesStatement.Builder()
                .where(visitWhere_clause(ctx.where_clause()))
                .from(visitFrom_clause(ctx.from_clause()))
                .build();
    }

    @Override
    public DropShardStatement visitDrop_shard_stmt(InfluxqlParser.Drop_shard_stmtContext ctx) {
        return new DropShardStatement.Builder()
                .shardId(Long.parseLong(ctx.shard_id.getText()))
                .build();
    }

    @Override
    public DropSubscriptionStatement visitDrop_subscription_stmt(
            InfluxqlParser.Drop_subscription_stmtContext ctx) {
        return new DropSubscriptionStatement.Builder()
                .subscription(trimIdentifierQuotes(ctx.subscription_name.getText()))
                .on(trimIdentifierQuotes(ctx.db_name.getText()))
                .retentionPolicy(trimIdentifierQuotes(ctx.retention_policy.getText()))
                .build();
    }

    @Override
    public DropUserStatement visitDrop_user_stmt(InfluxqlParser.Drop_user_stmtContext ctx) {
        return new DropUserStatement.Builder()
                .username(trimIdentifierQuotes(ctx.user_name.getText()))
                .build();
    }

    @Override
    public ExplainStatement visitExplain_stmt(InfluxqlParser.Explain_stmtContext ctx) {
        return new ExplainStatement.Builder()
                .select(visitSelect_stmt(ctx.select_stmt()))
                .analyse(ctx.ANALYZE() != null)
                .build();
    }

    @Override
    public GrantStatement visitGrant_stmt(InfluxqlParser.Grant_stmtContext ctx) {
        GrantStatement.Builder builder = new GrantStatement.Builder();

        if (ctx.on_clause() != null) {
            builder.on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()));
        }

        return builder.to(trimIdentifierQuotes(ctx.to_clause().user_name.getText()))
                .privilege(visitPrivilege(ctx.privilege()))
                .build();
    }

    @Override
    public KillQueryStatement visitKill_query_statement(
            InfluxqlParser.Kill_query_statementContext ctx) {
        return new KillQueryStatement.Builder()
                .queryId(Long.parseLong(ctx.INTEGER_LITERAL().getText()))
                .on(
                        Optional.ofNullable(ctx.host)
                                .map(Token::getText)
                                .map(InfluxqlAstAdapter::trimStringQuotes)
                                .orElse(null))
                .build();
    }

    @Override
    public ShowContinuousQueriesStatement visitShow_continuous_queries_stmt(
            InfluxqlParser.Show_continuous_queries_stmtContext ctx) {
        return new ShowContinuousQueriesStatement();
    }

    @Override
    public ShowDatabasesStatement visitShow_databases_stmt(
            InfluxqlParser.Show_databases_stmtContext ctx) {
        return new ShowDatabasesStatement();
    }

    @Override
    public ShowFieldKeysStatement visitShow_field_keys_stmt(
            InfluxqlParser.Show_field_keys_stmtContext ctx) {
        return new ShowFieldKeysStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .orderBy(
                        Optional.ofNullable(ctx.order_by_clause())
                                .map(this::visitOrder_by_clause)
                                .orElse(null))
                .build();
    }

    @Override
    public ShowGrantsForUserStatement visitShow_grants_stmt(
            InfluxqlParser.Show_grants_stmtContext ctx) {
        return new ShowGrantsForUserStatement.Builder()
                .for_(trimIdentifierQuotes(ctx.user_name.getText()))
                .build();
    }

    @Override
    public ShowMeasurementsStatement visitShow_measurements_stmt(
            InfluxqlParser.Show_measurements_stmtContext ctx) {
        return new ShowMeasurementsStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(o -> o.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.with_measurement_clause())
                                .map(this::visitWith_measurement_clause)
                                .orElse(null))
                .build();
    }

    @Override
    public ShowQueriesStatement visitShow_queries_stmt(
            InfluxqlParser.Show_queries_stmtContext ctx) {
        return new ShowQueriesStatement();
    }

    @Override
    public ShowRetentionPoliciesStatement visitShow_retention_policies_stmt(
            InfluxqlParser.Show_retention_policies_stmtContext ctx) {
        return new ShowRetentionPoliciesStatement.Builder()
                .on(trimIdentifierQuotes(ctx.on_clause().db_name.getText()))
                .build();
    }

    @Override
    public ShowSeriesStatement visitShow_series_stmt(InfluxqlParser.Show_series_stmtContext ctx) {
        return new ShowSeriesStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .build();
    }

    @Override
    public ShowShardGroupsStatement visitShow_shard_groups_stmt(
            InfluxqlParser.Show_shard_groups_stmtContext ctx) {
        return new ShowShardGroupsStatement();
    }

    @Override
    public ShowShardsStatement visitShow_shards_stmt(InfluxqlParser.Show_shards_stmtContext ctx) {
        return new ShowShardsStatement();
    }

    @Override
    public ShowSubscriptionsStatement visitShow_subscriptions_stmt(
            InfluxqlParser.Show_subscriptions_stmtContext ctx) {
        return new ShowSubscriptionsStatement();
    }

    @Override
    public ShowTagKeysStatement visitShow_tag_keys_stmt(
            InfluxqlParser.Show_tag_keys_stmtContext ctx) {
        ShowTagKeysStatement.Builder builder = new ShowTagKeysStatement.Builder();
        if (ctx.with_tag_clause() != null) {
            Operator op = Operator.fromValue(ctx.with_tag_clause().op.getText());
            Literal<?> literal;
            if (op == Operator.IN) {
                literal =
                        ListLiteral.of(
                                ctx.with_tag_clause().tag_keys().IDENTIFIER().stream()
                                        .map(ParseTree::getText)
                                        .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                        .map(IdentifierlLiteral::of)
                                        .collect(Collectors.toList()));
            } else if (op == Operator.EQREGEX || op == Operator.NEQREGEX) {
                literal = RegexLiteral.of(trimRegexChars(ctx.with_tag_clause().tag_key.getText()));
            } else {
                literal =
                        IdentifierlLiteral.of(
                                trimIdentifierQuotes(ctx.with_tag_clause().tag_key.getText()));
            }
            builder.withKey(op, literal);
        }

        return builder.on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .orderBy(
                        Optional.ofNullable(ctx.order_by_clause())
                                .map(this::visitOrder_by_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .build();
    }

    @Override
    public ShowTagKeyCardinalityStatement visitShow_tag_key_cardinality_stmt(
            InfluxqlParser.Show_tag_key_cardinality_stmtContext ctx) {
        return new ShowTagKeyCardinalityStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .groupBy(
                        Optional.ofNullable(ctx.group_by_clause())
                                .map(this::visitGroup_by_clause)
                                .orElse(null))
                .exact(Optional.ofNullable(ctx.EXACT()).isPresent())
                .build();
    }

    @Override
    public ShowTagValuesStatement visitShow_tag_values_stmt(
            InfluxqlParser.Show_tag_values_stmtContext ctx) {
        Operator op = Operator.fromValue(ctx.with_tag_clause().op.getText());
        Literal<?> literal;
        if (op == Operator.IN) {
            literal =
                    ListLiteral.of(
                            ctx.with_tag_clause().tag_keys().IDENTIFIER().stream()
                                    .map(ParseTree::getText)
                                    .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                    .map(IdentifierlLiteral::of)
                                    .collect(Collectors.toList()));
        } else if (op == Operator.EQREGEX || op == Operator.NEQREGEX) {
            literal = RegexLiteral.of(trimRegexChars(ctx.with_tag_clause().tag_key.getText()));
        } else {
            literal =
                    IdentifierlLiteral.of(
                            trimIdentifierQuotes(ctx.with_tag_clause().tag_key.getText()));
        }

        return new ShowTagValuesStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .withKey(op, literal)
                .build();
    }

    @Override
    public ShowTagValuesCardinalityStatement visitShow_tag_values_cardinality_stmt(
            InfluxqlParser.Show_tag_values_cardinality_stmtContext ctx) {
        Operator op = Operator.fromValue(ctx.with_tag_clause().op.getText());
        Literal<?> literal;
        if (op == Operator.IN) {
            literal =
                    ListLiteral.of(
                            ctx.with_tag_clause().tag_keys().IDENTIFIER().stream()
                                    .map(ParseTree::getText)
                                    .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                    .map(IdentifierlLiteral::of)
                                    .collect(Collectors.toList()));
        } else if (op == Operator.EQREGEX || op == Operator.NEQREGEX) {
            literal = RegexLiteral.of(trimRegexChars(ctx.with_tag_clause().tag_key.getText()));
        } else {
            literal =
                    IdentifierlLiteral.of(
                            trimIdentifierQuotes(ctx.with_tag_clause().tag_key.getText()));
        }
        return new ShowTagValuesCardinalityStatement.Builder()
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(
                        Optional.ofNullable(ctx.from_clause())
                                .map(this::visitFrom_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .exact(Optional.ofNullable(ctx.EXACT()).isPresent())
                .groupBy(
                        Optional.ofNullable(ctx.group_by_clause())
                                .map(this::visitGroup_by_clause)
                                .orElse(null))
                .withKey(op, literal)
                .build();
    }

    @Override
    public ShowUsersStatement visitShow_users_stmt(InfluxqlParser.Show_users_stmtContext ctx) {
        return new ShowUsersStatement();
    }

    @Override
    public RevokeStatement visitRevoke_stmt(InfluxqlParser.Revoke_stmtContext ctx) {
        return new RevokeStatement.Builder()
                .privilege(visitPrivilege(ctx.privilege()))
                .on(
                        Optional.ofNullable(ctx.on_clause())
                                .map(c -> c.db_name.getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .from(trimIdentifierQuotes(ctx.user_name.getText()))
                .build();
    }

    @Override
    public NumericLiteral visitNumber_literal(InfluxqlParser.Number_literalContext ctx) {
        if (ctx.INTEGER_LITERAL() != null) {
            return IntegerLiteral.of(Long.parseLong(ctx.INTEGER_LITERAL().getText()));
        } else {
            return NumberLiteral.of(Double.parseDouble(ctx.NUMERIC_LITERAL().getText()));
        }
    }

    @Override
    public SelectStatement visitSelect_stmt(InfluxqlParser.Select_stmtContext ctx) {

        Optional<InfluxqlParser.Group_by_clauseContext> groupByClause =
                Optional.ofNullable(ctx.group_by_clause());
        Optional<InfluxqlParser.Fill_clauseContext> fillClause =
                groupByClause.map(InfluxqlParser.Group_by_clauseContext::fill_clause);
        Optional<FillOption> fillOption = fillClause.map(m -> visitFill_option(m.fill_option()));

        Optional<NumericLiteral> fillValue =
                fillClause
                        .map(InfluxqlParser.Fill_clauseContext::fill_option)
                        .map(InfluxqlParser.Fill_optionContext::number_literal)
                        .map(this::visitNumber_literal);

        return new SelectStatement.Builder()
                .select(visitFields(ctx.fields()))
                .from(visitFrom_clause(ctx.from_clause()))
                .into(
                        Optional.ofNullable(ctx.into_clause())
                                .map(this::visitInto_clause)
                                .orElse(null))
                .where(
                        Optional.ofNullable(ctx.where_clause())
                                .map(this::visitWhere_clause)
                                .orElse(null))
                .groupBy(groupByClause.map(this::visitGroup_by_clause).orElse(null))
                .fill(fillOption.orElse(null))
                .fillValue(fillValue.orElse(null))
                .orderBy(
                        Optional.ofNullable(ctx.order_by_clause())
                                .map(this::visitOrder_by_clause)
                                .orElse(null))
                .limit(
                        Optional.ofNullable(ctx.limit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .offset(
                        Optional.ofNullable(ctx.offset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .sLimit(
                        Optional.ofNullable(ctx.slimit_clause())
                                .map(l -> Integer.parseInt(l.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .sOffset(
                        Optional.ofNullable(ctx.soffset_clause())
                                .map(o -> Integer.parseInt(o.INTEGER_LITERAL().getText()))
                                .orElse(0))
                .timezone(
                        Optional.ofNullable(ctx.timezone_clause())
                                .map(this::visitTimezone_clause)
                                .map(TimezoneNode::getTimeZone)
                                .orElse(null))
                .build();
    }

    @Override
    public Dimension visitDimension(InfluxqlParser.DimensionContext ctx) {
        return Dimension.of(visitExpression(ctx.expression()));
    }

    @Override
    public Dimensions visitDimensions(InfluxqlParser.DimensionsContext ctx) {
        return Dimensions.of(
                ctx.dimension().stream().map(this::visitDimension).collect(Collectors.toList()));
    }

    @Override
    public Field visitField(InfluxqlParser.FieldContext ctx) {
        return new Field.Builder()
                .withAlias(
                        Optional.ofNullable(ctx.alias())
                                .map(m -> m.IDENTIFIER().getText())
                                .map(InfluxqlAstAdapter::trimIdentifierQuotes)
                                .orElse(null))
                .withExpr(visitExpression(ctx.expression()))
                .build();
    }

    @Override
    public Fields visitFields(InfluxqlParser.FieldsContext ctx) {
        return Fields.of(ctx.field().stream().map(this::visitField).collect(Collectors.toList()));
    }

    @Override
    public FillOption visitFill_option(InfluxqlParser.Fill_optionContext ctx) {
        if (ctx.LINEAR() != null) {
            return FillOption.LINEAR;
        } else if (ctx.NULL() != null) {
            return FillOption.NULL;
        } else if (ctx.NONE() != null) {
            return FillOption.NONE;
        } else if (ctx.PREVIOUS() != null) {
            return FillOption.PREVIOUS;
        } else if (ctx.number_literal() != null) {
            return FillOption.NUMBER;
        }
        throw new UnsupportedOperationException(
                "Unsupported Fill Option. Is grammar and code synchronized ?");
    }

    @Override
    public Measurement visitMeasurement_with_rp_and_database(
            InfluxqlParser.Measurement_with_rp_and_databaseContext ctx) {
        Measurement.Builder builder = new Measurement.Builder();
        builder.withRetentionPolicy(trimIdentifierQuotes(ctx.policy_name.getText()));
        builder.withDatabase(trimIdentifierQuotes(ctx.db_name.getText()));
        setSimpleMeasurement(builder, ctx.simple_measurement_name());
        return builder.build();
    }

    @Override
    public Measurement visitMeasurment_with_rp(InfluxqlParser.Measurment_with_rpContext ctx) {
        Measurement.Builder builder = new Measurement.Builder();
        builder.withRetentionPolicy(trimIdentifierQuotes(ctx.policy_name.getText()));
        setSimpleMeasurement(builder, ctx.simple_measurement_name());
        return builder.build();
    }

    private void setSimpleMeasurement(
            Measurement.Builder builder, InfluxqlParser.Simple_measurement_nameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            builder.withName(trimIdentifierQuotes(ctx.IDENTIFIER().getText()));
        }

        if (ctx.REGULAR_EXPRESSION_LITERAL() != null) {
            builder.withRegex(
                    Pattern.compile(trimRegexChars(ctx.REGULAR_EXPRESSION_LITERAL().getText())));
        }
    }

    @Override
    public Measurement visitSimple_measurement_name(
            InfluxqlParser.Simple_measurement_nameContext ctx) {
        Measurement.Builder builder = new Measurement.Builder();
        setSimpleMeasurement(builder, ctx);
        return builder.build();
    }

    @Override
    public Measurement visitMeasurement(InfluxqlParser.MeasurementContext ctx) {

        if (ctx.measurement_with_rp_and_database() != null) {
            return visitMeasurement_with_rp_and_database(ctx.measurement_with_rp_and_database());
        }
        if (ctx.measurment_with_rp() != null) {
            return visitMeasurment_with_rp(ctx.measurment_with_rp());
        }
        if (ctx.simple_measurement_name() != null) {
            return visitSimple_measurement_name(ctx.simple_measurement_name());
        }

        throw new UnsupportedOperationException(
                "Could not read Measurement. Ensure grammar is in sync with code");
    }

    @Override
    public SubQuery visitSub_query(InfluxqlParser.Sub_queryContext ctx) {
        return new SubQuery(visitSelect_stmt(ctx.select_stmt()));
    }

    @Override
    public Source visitSource(InfluxqlParser.SourceContext ctx) {
        if (ctx.sub_query() != null) {
            return visitSub_query(ctx.sub_query());
        }
        if (ctx.measurement() != null) {
            return visitMeasurement(ctx.measurement());
        }
        throw new UnsupportedOperationException(
                "Unknown source type. Make sure Grammar is in sync with code");
    }

    @Override
    public Sources visitSources(InfluxqlParser.SourcesContext ctx) {
        return Sources.of(
                ctx.source().stream().map(this::visitSource).collect(Collectors.toList()));
    }

    @Override
    public Privilege visitPrivilege(InfluxqlParser.PrivilegeContext ctx) {
        if (ctx.ALL() != null) {
            return Privilege.ALL_PRIVILEGES;
        }
        if (ctx.READ() != null) {
            return Privilege.READ_PRIVILEGE;
        }
        if (ctx.WRITE() != null) {
            return Privilege.WRITE_PRIVILEGE;
        }
        throw new IllegalArgumentException("Unsupported privilege");
    }

    @Override
    public SortField visitSort_field(InfluxqlParser.Sort_fieldContext ctx) {
        SortField.Builder builder = new SortField.Builder();

        if (ctx.ASC() != null) {
            builder.ascending(true);
        }

        if (ctx.DESC() != null) {
            builder.ascending(false);
        }

        return builder.field(trimIdentifierQuotes(ctx.field_key.getText())).build();
    }

    @Override
    public SortFields visitSort_fields(InfluxqlParser.Sort_fieldsContext ctx) {
        return SortFields.of(
                ctx.sort_field().stream().map(this::visitSort_field).collect(Collectors.toList()));
    }

    @Override
    public VarRef visitVar_ref(InfluxqlParser.Var_refContext ctx) {
        // I'm not a huge fan of this.
        // VarRef only defines
        return VarRef.of(visitMeasurement(ctx.measurement()).toString());
    }

    @Override
    public Parentheses visitGroup_expr(InfluxqlParser.Group_exprContext ctx) {
        return Parentheses.of(visitExpression(ctx.expression()));
    }

    @Override
    public Call visitCall(InfluxqlParser.CallContext ctx) {
        return new Call.Builder()
                .function(trimIdentifierQuotes(ctx.IDENTIFIER().getText()))
                .withArguments(
                        ctx.expression().stream()
                                .map(this::visitExpression)
                                .collect(Collectors.toCollection(StringJoiningList::new)))
                .build();
    }

    @Override
    public Operator visitUnary_operator(InfluxqlParser.Unary_operatorContext ctx) {
        return Operator.fromValue(ctx.getText());
    }

    @Override
    public Operator visitBinary_operator(InfluxqlParser.Binary_operatorContext ctx) {
        return Operator.fromValue(ctx.getText());
    }

    @Override
    public Expression visitExpression(InfluxqlParser.ExpressionContext ctx) {

        if (ctx.literal_value() != null) {
            return visitLiteral_value(ctx.literal_value());
        } else if (ctx.unary_operator() != null) {
            return new UnaryExpression.Builder()
                    .withOperator(visitUnary_operator(ctx.unary_operator()))
                    .withExpression(visitExpression(ctx.expression(0)))
                    .build();
        } else if (ctx.call() != null) {
            return visitCall(ctx.call());
        } else if (ctx.group_expr() != null) {
            return visitGroup_expr(ctx.group_expr());
        } else if (ctx.var_ref() != null) {
            return visitVar_ref(ctx.var_ref());
        } else if (ctx.STAR() != null) {
            return Wildcard.wildcard();
        } else if (ctx.STAR_FIELD() != null) {
            return Wildcard.wildcardFields();
        } else if (ctx.STAR_TAGS() != null) {
            return Wildcard.wildcardTags();
        } else if (ctx.expression() != null && ctx.expression().size() == 2) {
            return BinaryExpression.of(
                    visitExpression(ctx.expression(0)),
                    visitExpression(ctx.expression(1)),
                    visitBinary_operator(ctx.binary_operator()));
        }

        throw new IllegalStateException("Unsupported case. Grammar is not in sync with code");
    }

    @Override
    public Literal<?> visitLiteral_value(InfluxqlParser.Literal_valueContext ctx) {
        if (ctx.INTEGER_LITERAL() != null) {
            return IntegerLiteral.of(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
        } else if (ctx.NUMERIC_LITERAL() != null) {
            return NumberLiteral.of(Double.parseDouble(ctx.NUMERIC_LITERAL().getText()));
        } else if (ctx.STRING_LITERAL() != null) {
            return StringLiteral.of(trimStringQuotes(ctx.STRING_LITERAL().getText()));
        } else if (ctx.DURATION_LITERAL() != null) {
            return DurationLiteral.of(parseDuration(ctx.DURATION_LITERAL().getText()));
        } else if (ctx.TRUE() != null) {
            return BooleanLiteral.of(true);
        } else if (ctx.FALSE() != null) {
            return BooleanLiteral.of(false);
        } else if (ctx.NULL() != null) {
            return NullLiteral.of();
        }
        throw new UnsupportedOperationException("Unsupported literal; Check grammar and code sync");
    }

    @Override
    public Sources visitFrom_clause(InfluxqlParser.From_clauseContext ctx) {
        return visitSources(ctx.sources());
    }

    @Override
    public Dimensions visitGroup_by_clause(InfluxqlParser.Group_by_clauseContext ctx) {
        return visitDimensions(ctx.dimensions());
    }

    @Override
    public Target visitInto_clause(InfluxqlParser.Into_clauseContext ctx) {
        return Target.of(visitMeasurement(ctx.measurement()));
    }

    @Override
    public TimezoneNode visitTimezone_clause(InfluxqlParser.Timezone_clauseContext ctx) {
        return TimezoneNode.of(
                TimeZone.getTimeZone(trimStringQuotes(ctx.STRING_LITERAL().getText())));
    }

    @Override
    public SortFields visitOrder_by_clause(InfluxqlParser.Order_by_clauseContext ctx) {
        return visitSort_fields(ctx.sort_fields());
    }

    @Override
    public Expression visitWhere_clause(InfluxqlParser.Where_clauseContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public Measurement visitWith_measurement_clause(
            InfluxqlParser.With_measurement_clauseContext ctx) {

        if (ctx.measurement() != null) {
            return visitMeasurement(ctx.measurement());
        } else if (ctx.REGULAR_EXPRESSION_LITERAL() != null) {
            return Measurement.measurements(
                    trimRegexChars(ctx.REGULAR_EXPRESSION_LITERAL().getText()));
        }

        throw new IllegalStateException("Unsupported case. Grammar is not in sync with code");
    }
}
