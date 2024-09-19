package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;

public class CreateUserStatement implements Statement {
    private final String name;
    private final String password;
    private final boolean admin;

    private CreateUserStatement(Builder builder) {
        name = builder.name;
        password = builder.password;
        admin = builder.admin;
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("CREATE USER ");
        buf.append(QuoteIdent(name));
        buf.append(" WITH PASSWORD ");
        buf.append("[REDACTED]");
        if (admin) {
            buf.append(" WITH ALL PRIVILEGES");
        }
        return buf.toString();
    }

    /**
     * {@code CreateUserStatement} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String password;
        private boolean admin;

        public Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder enabling method chaining.
         *
         * @param password the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the {@code admin} and returns a reference to this Builder enabling method chaining.
         *
         * @param admin the {@code admin} to set
         * @return a reference to this Builder
         */
        public Builder withAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        /**
         * Returns a {@code CreateUserStatement} built from the parameters previously set.
         *
         * @return a {@code CreateUserStatement} built with parameters of this {@code CreateUserStatement.Builder}
         */
        public CreateUserStatement build() {
            return new CreateUserStatement(this);
        }
    }
}
