package de.agilecoders.wicket.jquery;

import org.apache.wicket.util.io.IClusterable;

import java.util.Map;

/**
 * configuration interface
 *
 * @author Michael Haitz
 */
public interface Config extends IClusterable {

    /**
     * @return json string representation of this config
     */
    String toJsonString();

    /**
     * @return an immutable map of all key/values
     */
    Map<String, Object> all();

    /**
     * @return true, if config map is empty or all keys uses their default value
     */
    boolean isEmpty();

    /**
     * puts a new config to the configuration map. If given value is default
     * value of key then it will be removed. This is necessary to keep the ui
     * code small and clean. Also the given value type will be asserted by
     * {@link IKey} implementation.
     *
     * @param key   mandatory parameter
     * @param value mandatory parameter
     * @return same instance for chaining
     */
    <T> Config put(final IKey<T> key, final T value);

    /**
     * removes the given key (and its value) from configuration map.
     *
     * @param key the key to remove
     */
    <T> T remove(final IKey<T> key);

    /**
     * returns the value for the given key. If no value is set, the
     * default value will be returned.
     *
     * @param key The key to read.
     * @return the value.
     */
    <T> T get(final IKey<T> key);

    /**
     * returns true if given key is set to this config
     *
     * @param key The key to check.
     * @return true if key exists.
     */
    <T> boolean contains(final IKey<T> key);
}
