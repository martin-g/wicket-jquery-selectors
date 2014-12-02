package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.JQuery;

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
        super("function");

        this.functionBody = nullToEmpty(functionBody);
    }

    @Override
    public String build() {
        return super.build() + "{" + functionBody + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JavaScriptInlineFunction) {
            return functionBody.equals(((JavaScriptInlineFunction) o).functionBody);
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
