package de.agilecoders.wicket.jquery.settings;

import tools.jackson.core.Version;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import de.agilecoders.wicket.jquery.Config;
import de.agilecoders.wicket.jquery.ConfigModel;
import de.agilecoders.wicket.jquery.util.Json;
import de.agilecoders.wicket.jquery.util.serializer.ConfigModelSerializer;
import de.agilecoders.wicket.jquery.util.serializer.ConfigSerializer;
import de.agilecoders.wicket.jquery.util.serializer.RawSerializer;

/**
 * {@link tools.jackson.databind.ObjectMapper} factory
 *
 * @author Michael Haitz
 */
public class DefaultObjectMapperFactory implements ObjectMapperFactory {

    /**
     * lazy holder pattern to prevent instantiation of serializers if not used.
     */
    protected static final class Holder {

        protected static final RawSerializer RAW_VALUE_SERIALIZER = new RawSerializer();

        protected static final ConfigSerializer CONFIG_SERIALIZER = new ConfigSerializer();

        protected static final ConfigModelSerializer CONFIG_MODEL_SERIALIZER = new ConfigModelSerializer();
    }

    /**
     * Construct.
     */
    public DefaultObjectMapperFactory() {
        super();
    }

    /**
     * @return new object mapper instance
     */
    @Override
    public ObjectMapper newObjectMapper() {
        return configure(JsonMapper.builder())
            .addModule(newModule())
            .build();
    }

    /**
     * @return new mapper module
     */
    protected JacksonModule newModule() {
        return addSerializer(new SimpleModule("wicket-jquery-selectors", new Version(1, 0, 0, null, "de.agilecoders.wicket", "wicket-jquery-selectors")));
    }

    /**
     * adds custom serializers to given module
     *
     * @param module the module to extend
     * @return module instance for chaining
     */
    protected JacksonModule addSerializer(SimpleModule module) {
        module.addSerializer(ConfigModel.class, Holder.CONFIG_MODEL_SERIALIZER);
        module.addSerializer(Config.class, Holder.CONFIG_SERIALIZER);
        module.addSerializer(Json.RawValue.class, Holder.RAW_VALUE_SERIALIZER);

        return module;
    }

    /**
     * configures given object mapper instance.
     *
     * @param mapper the object to configure
     * @return mapper instance for chaining
     */
    protected JsonMapper.Builder configure(JsonMapper.Builder builder) {
        return builder
            .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
            .configure(JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES, true);
    }
}
