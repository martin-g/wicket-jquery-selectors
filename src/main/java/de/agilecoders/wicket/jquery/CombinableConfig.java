package de.agilecoders.wicket.jquery;

/**
 * @author Michael Haitz
 */
public abstract class CombinableConfig implements Config {

    /**
     * Construct.
     */
    protected CombinableConfig() {
        super();
    }

    /**
     * uses given config as fallback if a key isn't set in this config.
     *
     * @param fallback the fallback configuration
     * @return this instance for chaining
     */
    public CombinableConfig withFallback(Config fallback) {
        return new ConfigWithFallback(this, fallback);
    }

    /**
     * combines this configuration with given ones. Given configuration doesn't override existing one.
     *
     * @param fallbackConfigs the configurations to combine
     * @return this instance for chaining
     */
    public CombinableConfig combine(Config... fallbackConfigs) {
        CombinableConfig newConfig = this;
        for (Config fallback : fallbackConfigs) {
            newConfig = new ConfigWithFallback(newConfig, fallback);
        }
        return newConfig;
    }

}
