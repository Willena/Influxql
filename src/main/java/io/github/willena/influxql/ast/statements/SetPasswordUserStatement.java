package io.github.willena.influxql.ast.statements;

import io.github.willena.influxql.ast.Statement;

import static io.github.willena.influxql.ast.Utils.QuoteIdent;
import static io.github.willena.influxql.ast.Utils.QuoteString;

public class SetPasswordUserStatement implements Statement {
    private final String password;
    private final String name;

    // Go source code always redact in String() method.
    // Here it is a builder; The password should not be redacted.
    // If required it can be redacted.
    private final boolean redacted;

    public SetPasswordUserStatement(String password, String name, boolean redacted) {
        this.password = password;
        this.name = name;
        this.redacted = redacted;
    }

    public SetPasswordUserStatement(String password, String name) {
        this(password, name, false);
    }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buf.append("SET PASSWORD FOR ");
        buf.append(QuoteIdent(name));
        buf.append(" = ");
        if (redacted) {
            buf.append("[REDACTED]");
        } else {
            buf.append(QuoteString(password));
        }
        return buf.toString();
    }
}
