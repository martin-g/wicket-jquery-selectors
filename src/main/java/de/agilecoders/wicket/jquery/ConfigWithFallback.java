package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.util.Json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * special config that holds two configurations. One base configuration and a fallback configuration.
 *
 * @author Michael Haitz
 */
public class ConfigWithFallback extends CombinableConfig {

    private final Config base;
    private final Config fallback;

    /**
     * Construct.
     *
     * @param base     base configuration
     * @param fallback fallback configuration
     */
    public ConfigWithFallback(Config base, Config fallback) {
        this.base = base;
        this.fallback = fallback;
    }

    @Override
    public String toJsonString() {
        return Json.stringify(all());
    }

    @Override
    public Map<String, Object> all() {
        Map<String, Object> all = new HashMap<String, Object>();
        all.putAll(fallback.all());
        all.putAll(base.all());
        return Collections.unmodifiableMap(all);
    }

    @Override
    public boolean isEmpty() {
        return base.isEmpty() && fallback.isEmpty();
    }

    @Override
    public <T> ConfigWithFallback put(IKey<T> key, T value) {
        if (fallback.contains(key)) {
            fallback.put(key, value);
        }

        base.put(key, value);
        return this;
    }

    @Override
    public <T> T remove(IKey<T> key) {
        T value = key.getDefaultValue();

        if (fallback.contains(key)) {
            value = fallback.remove(key);
        }
        if (base.contains(key)) {
            value = base.remove(key);
        }

        return value;
    }

    @Override
    public <T> T get(IKey<T> key) {
        if (base.contains(key)) {
            return base.get(key);
        }
        if (fallback.contains(key)) {
            return fallback.get(key);
        }

        return key.getDefaultValue();
    }

    @Override
    public <T> boolean contains(IKey<T> key) {
        return base.contains(key) || fallback.contains(key);
    }
}
