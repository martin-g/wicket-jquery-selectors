package de.agilecoders.wicket.jquery.function;

/**
 * java abstraction of jquery each function
 */
public final class EachJqueryFunction extends Function {

    /**
     * creates a new {@link EachJqueryFunction} instance that holds a given inline function.
     *
     * @param function The inline function to execute for each element
     * @return new {@link EachJqueryFunction} instance
     */
    public static EachJqueryFunction each(final JavaScriptInlineFunction function) {
        return new EachJqueryFunction(function);
    }

    /**
     * Construct.
     */
    protected EachJqueryFunction(final JavaScriptInlineFunction function) {
        super("each", function);
    }
}
