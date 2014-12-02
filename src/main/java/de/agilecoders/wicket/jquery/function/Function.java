package de.agilecoders.wicket.jquery.function;

/**
 *
 */
public class Function extends AbstractFunction {

    /**
     * Constructor.
     *
     * @param name The function name of this {@link de.agilecoders.wicket.jquery.function.IFunction} implementation
     */
    public Function(CharSequence name) {
        super(name);
    }

    /**
     * @param name The function name of this {@link de.agilecoders.wicket.jquery.function.IFunction} implementation
     * @param parameters
     */
    public Function(String name, Object... parameters) {
        super(name);

        if (parameters != null) {
            for (Object parameter : parameters) {
                addParameter(toParameterValue(parameter));
            }
        }
    }
}
