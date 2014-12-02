package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.util.CharSequenceWrapper;
import de.agilecoders.wicket.jquery.util.Generics2;

import java.util.List;

/**
 * A simple implementation of {@link IFunction} that allows you to chain
 * function parameters in a javascript safe way.
 */
public abstract class AbstractFunction implements IFunction {
    private final CharSequence functionName;
    private final List<CharSequence> parameters;

    /**
     * Construct.
     *
     * @param functionName The function name of this {@link IFunction} implementation
     */
    protected AbstractFunction(final CharSequence functionName) {
        this.functionName = functionName;
        this.parameters = Generics2.newArrayList();
    }

    @Override
    public String build() {
        return functionName + "(" + buildParameters() + ")";
    }

    /**
     * @return the separator
     */
    protected final char getSeparator() {
        return ',';
    }

    /**
     * @return a joined list of parameters as string
     */
    protected String buildParameters() {
        return Generics2.join(parameters, getSeparator());
    }

    /**
     * adds a new parameter to parameter list
     *
     * @param parameter The parameter to add
     */
    protected final void addParameter(final CharSequence parameter) {
        parameters.add(parameter);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final Object value) {
        return CharSequenceWrapper.toParameterValue(value);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final JavaScriptInlineFunction value) {
        return CharSequenceWrapper.toParameterValue(value);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final Long value) {
        return CharSequenceWrapper.toParameterValue(value);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final Integer value) {
        return CharSequenceWrapper.toParameterValue(value);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final Float value) {
        return CharSequenceWrapper.toParameterValue(value);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    protected final CharSequence toParameterValue(final Boolean value) {
        return CharSequenceWrapper.toParameterValue(value);
    }
}
