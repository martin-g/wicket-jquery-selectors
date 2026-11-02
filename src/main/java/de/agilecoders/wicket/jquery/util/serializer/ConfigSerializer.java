package de.agilecoders.wicket.jquery.util.serializer;

import tools.jackson.core.JsonGenerator;
import de.agilecoders.wicket.jquery.Config;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * {@link de.agilecoders.wicket.jquery.Config} json serializer
 *
 * @author Michael Haitz
 */
public class ConfigSerializer extends ValueSerializer<Config> {
    @Override
    public void serialize(Config value, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        jsonGenerator.writeRawValue(value.toJsonString());
    }
}
