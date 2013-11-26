package de.agilecoders.wicket.jquery;

/**
 * Simple interface to enrich the {@link java.util.Map} key with some
 * functionality like assertion of value type and default value
 * handling.
 */
public interface IKey<T> {

    /**
     * @return the key to use in {@link java.util.Map}
     */
    String key();

    /**
     * checks if given value is equal to default value
     *
     * @param value The value to check.
     * @return true, if given value is equal to default value
     */
    boolean isDefaultValue(T value);

    /**
     * @return the default value
     */
    T getDefaultValue();
}
