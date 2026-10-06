package de.agilecoders.wicket.jquery.util.serializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import de.agilecoders.wicket.jquery.ConfigModel;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * {@link de.agilecoders.wicket.jquery.ConfigModel} json serializer
 *
 * @author Michael Haitz
 */
public class ConfigModelSerializer extends ValueSerializer<ConfigModel> {

    @Override
    public void serialize(ConfigModel value, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        jsonGenerator.writeString(value.getObject());
    }

}
