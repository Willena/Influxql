package io.github.willena.influxql.ast.statement;

import io.github.willena.influxql.ast.Buildable;
import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.utils.Utils.*;

public class SetPasswordUserStatement implements Statement {
    private final String password;
    private final String name;

    // Go source code always redact in String() method.
    // Here it is a builder; The password should not be redacted.
    // If required it can be redacted.
    private final boolean redacted;

    private SetPasswordUserStatement(Builder builder) {
        password = builder.password;
        name = builder.name;
        redacted = builder.redacted;
        ensureDefined("name", name);
        ensureDefined("password", password);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SET PASSWORD FOR ");
        buf.append(quoteIdentifier(name));
        buf.append(" = ");
        if (redacted) {
            buf.append("[REDACTED]");
        } else {
            buf.append(quoteString(password));
        }
        return buf.toString();
    }

    public static final class Builder implements Buildable<SetPasswordUserStatement> {
        private String password;
        private String name;
        private boolean redacted;

        public Builder() {
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder for_(String name) {
            this.name = name;
            return this;
        }

        public Builder redacted(boolean redacted) {
            this.redacted = redacted;
            return this;
        }

        public Builder redacted() {
            return redacted(true);
        }

        public SetPasswordUserStatement build() {
            return new SetPasswordUserStatement(this);
        }
    }
}
