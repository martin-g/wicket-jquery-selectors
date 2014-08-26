package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.util.Json;
import org.apache.wicket.model.IModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Base configuration class.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class AbstractConfig extends CombinableConfig {

    private final Map<String, Object> config;

    /**
     * Construct.
     */
    protected AbstractConfig() {
        super();

        config = new HashMap<String, Object>();
    }

    /**
     * @return current configuration as json string
     */
    @Override
    public final String toJsonString() {
        return Json.stringify(config);
    }

    /**
     * @return an immutable view of all configurations
     */
    @Override
    public final Map<String, Object> all() {
        return Collections.unmodifiableMap(config);
    }

    /**
     * @return true, if no special configuration is set.
     */
    @Override
    public final boolean isEmpty() {
        return config.isEmpty();
    }

    /**
     * puts a new config to the configuration map. If given value is default
     * value of key then it will be removed. This is necessary to keep the ui
     * code small and clean. Also the given value type will be asserted by
     * {@link IKey} implementation.
     *
     * @param key   mandatory parameter
     * @param value mandatory parameter
     */
    @Override
    public final <T> AbstractConfig put(final IKey<T> key, final T value) {
        if (!key.isDefaultValue(value)) {
            config.put(key.key(), value);
        } else {
            remove(key);
        }
        return this;
    }

    @Override
    public <T> boolean contains(IKey<T> key) {
        return config.containsKey(key.key());
    }

    /**
     * removes the given key (and its value) from configuration map.
     *
     * @param key the key to remove
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <T> T remove(final IKey<T> key) {
        return (T) config.remove(key.key());
    }

    /**
     * returns the value as string according to given key. If no value is set, the
     * default value will be returned.
     *
     * @param key The key to read.
     * @return the value as string.
     */
    protected final <T> String getString(final IKey<T> key) {
        final Object value = config.get(key.key());

        return String.valueOf(value != null ? value : key.getDefaultValue());
    }

    /**
     * returns the value for the given key. If no value is set, the
     * default value will be returned.
     *
     * @param key The key to read.
     * @return the value.
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <T> T get(final IKey<T> key) {
        T value = (T) config.get(key.key());

        return value != null ? value : key.getDefaultValue();
    }

    /**
     * Wraps IModel&lt;String&gt; in ConfigModel to serialize it as
     * simple String in the produced JSON.
     *
     * @param model The model to wrap.
     * @return A model that uses special Json serializer
     */
    protected ConfigModel wrap(IModel<String> model) {
        return new ConfigModel(model);
    }

    /**
     * creates a new key.
     *
     * @param key          string representation of this key
     * @param defaultValue The default value
     */
    protected static <T> IKey<T> newKey(final String key, final T defaultValue) {
        return new Key<T>(key, defaultValue);
    }

}
