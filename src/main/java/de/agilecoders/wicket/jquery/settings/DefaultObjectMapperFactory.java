package de.agilecoders.wicket.jquery.settings;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.agilecoders.wicket.jquery.Config;
import de.agilecoders.wicket.jquery.ConfigModel;
import de.agilecoders.wicket.jquery.util.Json;
import de.agilecoders.wicket.jquery.util.serializer.ConfigModelSerializer;
import de.agilecoders.wicket.jquery.util.serializer.ConfigSerializer;
import de.agilecoders.wicket.jquery.util.serializer.RawSerializer;

/**
 * {@link com.fasterxml.jackson.databind.ObjectMapper} factory
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
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
        return configure(new ObjectMapper()).registerModule(newModule());
    }

    /**
     * @return new mapper module
     */
    protected Module newModule() {
        return addSerializer(new SimpleModule("wicket-jquery-selectors", new Version(1, 0, 0, null, "de.agilecoders.wicket", "wicket-jquery-selectors")));
    }

    /**
     * adds custom serializers to given module
     *
     * @param module the module to extend
     * @return module instance for chaining
     */
    protected Module addSerializer(SimpleModule module) {
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
    protected ObjectMapper configure(ObjectMapper mapper) {
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        return mapper;
    }
}
