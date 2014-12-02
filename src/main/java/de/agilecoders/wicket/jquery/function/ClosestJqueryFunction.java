package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.Attr;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * java abstraction of jquery closest function
 */
public final class ClosestJqueryFunction extends Function {

    /**
     * creates a new {@link ClosestJqueryFunction} instance
     *
     * @param selector The CSS selector to use the closest parent
     * @return new {@link ClosestJqueryFunction} instance
     */
    public static ClosestJqueryFunction closest(final Attr selector) {
        return new ClosestJqueryFunction(selector);
    }

    /**
     * creates a new {@link ClosestJqueryFunction} instance
     *
     * @param selector The CSS selector to use the closest parent
     * @return new {@link ClosestJqueryFunction} instance
     */
    public static ClosestJqueryFunction closest(final CharSequence selector) {
        return closest(JQuery.quoted(selector));
    }

    /**
     * Construct.
     */
    protected ClosestJqueryFunction(final Attr selector) {
        super("closest", selector);
    }
}
