package de.agilecoders.wicket.jquery.function;

import org.apache.wicket.util.io.IClusterable;

/**
 * simple interface to represent a jquery function.
 */
public interface IFunction extends IClusterable {

    /**
     * @return the function as javascript string.
     */
    String build();
}
