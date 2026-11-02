package de.agilecoders.wicket.jquery.util.serializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import de.agilecoders.wicket.jquery.util.Json;

/**
 * {@link de.agilecoders.wicket.jquery.util.Json.RawValue} json serializer
 *
 * @author Michael Haitz
 */
public class RawSerializer extends ValueSerializer<Json.RawValue> {
    @Override
    public void serialize(Json.RawValue value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        gen.writeRawValue(value.value());
    }
}
