package de.agilecoders.wicket.jquery.settings;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import de.agilecoders.wicket.jquery.Config;
import de.agilecoders.wicket.jquery.ConfigModel;
import de.agilecoders.wicket.jquery.util.Json;

import java.io.IOException;

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
        private static final RawSerializer<Json.RawValue> rawSerializer = new RawSerializer<Json.RawValue>(Json.RawValue.class) {
            @Override
            public void serialize(Json.RawValue value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                jsonGenerator.writeObject(value.value());
            }
        };
        public static JsonSerializer<Config> configSerializer = new JsonSerializer<Config>() {
            @Override
            public void serialize(Config value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                jsonGenerator.writeObject(value.toJsonString());
            }
        };
        public static JsonSerializer<ConfigModel> configModelSerializer = new JsonSerializer<ConfigModel>() {
            @Override
            public void serialize(ConfigModel value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                jsonGenerator.writeString(value.getObject());
            }
        };
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
        module.addSerializer(ConfigModel.class, Holder.configModelSerializer);
        module.addSerializer(Config.class, Holder.configSerializer);
        module.addSerializer(Json.RawValue.class, Holder.rawSerializer);

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
