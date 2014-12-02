package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.Attr;

/**
 * java abstraction of jQuery find function
 */
public class FindJqueryFunction extends Function {

    /**
     * Construct.
     *
     * @param selector The selector for the child element
     * @return new {@link FindJqueryFunction} instance
     */
    public FindJqueryFunction(Attr selector) {
        super("find", selector);
    }
}
