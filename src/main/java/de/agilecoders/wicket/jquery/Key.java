package de.agilecoders.wicket.jquery;

import org.apache.wicket.util.lang.Objects;

/**
 * Default {@link IKey} implementation
 *
 * @author Michael Haitz michael.haitz@agilecoders.de
 */
public class Key<T> implements IKey<T> {
    private final String key;
    private final T defaultValue;

    /**
     * Construct.
     *
     * @param name         string representation of this key
     * @param defaultValue The default value
     */
    public Key(final String name, final T defaultValue) {
        this.key = name;
        this.defaultValue = defaultValue;
    }

    /**
     * Construct. Uses `null` as default value
     *
     * @param name string representation of this key
     */
    public Key(final String name) {
        this(name, null);
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public boolean isDefaultValue(final T value) {
        return Objects.equal(value, defaultValue);
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

}