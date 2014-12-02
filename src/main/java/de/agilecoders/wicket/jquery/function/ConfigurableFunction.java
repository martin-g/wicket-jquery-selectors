package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.Config;

/**
 * a configurable function with one parameter (an {@link de.agilecoders.wicket.jquery.AbstractConfig}) and without body
 */
public class ConfigurableFunction extends Function {

    /**
     * Construct.
     *
     * @param functionName The function name of this {@link de.agilecoders.wicket.jquery.function.IFunction} implementation
     * @param config       the function configuration
     */
    public ConfigurableFunction(final CharSequence functionName, final Config config) {
        this(functionName, config, (Config[]) null);
    }

    /**
     * Construct.
     *
     * @param functionName The function name of this {@link de.agilecoders.wicket.jquery.function.IFunction} implementation
     * @param config       the function configuration
     */
    public ConfigurableFunction(final CharSequence functionName, final Config config, Config... extraConfigs) {
        super(functionName);

        // if multiple configs are provided, render all parameters, even if empty
        if (extraConfigs != null && extraConfigs.length > 0) {
            addParameter(config.toJsonString());

            for (Config extraConfig : extraConfigs) {
                addParameter(extraConfig.toJsonString());
            }
        } else if(!config.isEmpty()) {
          // don't render any parameter if only one is provided and it is empty
          addParameter(config.toJsonString());
        }
    }
}
