package de.agilecoders.wicket.jquery.function;

import org.apache.wicket.util.lang.Objects;

import java.util.Collections;
import java.util.List;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

/**
 * simple class to represent a javascript function.
 */
public class JavaScriptInlineFunction extends AbstractFunction {
    private final String functionBody;

    /**
     * Construct.
     *
     * @param functionBody the function body as string
     */
    public JavaScriptInlineFunction(final String functionBody) {
        this(functionBody, Collections.<CharSequence>emptyList());
    }

    /**
     * Construct.
     *
     * @param functionBody the function body as string
     */
    public JavaScriptInlineFunction(final String functionBody, final List<CharSequence> parameters) {
        super("function", parameters);

        this.functionBody = nullToEmpty(functionBody);
    }

    @Override
    public String build() {
        return super.build() + "{" + functionBody + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JavaScriptInlineFunction) {
            JavaScriptInlineFunction other = (JavaScriptInlineFunction) o;
            final boolean parametersEqual = Objects.equal(getParameters(), other.getParameters());
            final boolean functionBodyEqual = Objects.equal(functionBody, other.functionBody);
            return parametersEqual && functionBodyEqual;
        } else if (o instanceof String) {
            return functionBody.equals(o);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return functionBody.hashCode();
    }

    @Override
    public String toString() {
        return functionBody;
    }

    /**
     * @param value The value to stringify
     * @return string representation of given value
     */
    public static String toString(final JavaScriptInlineFunction value) {
        return value != null ? value.toString() : "null";
    }
}
