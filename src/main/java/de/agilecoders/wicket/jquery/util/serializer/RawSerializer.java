package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.agilecoders.wicket.jquery.util.Json;

import java.io.IOException;

/**
 * {@link de.agilecoders.wicket.jquery.util.Json.RawValue} json serializer
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class RawSerializer extends JsonSerializer<Json.RawValue> {
    @Override
    public void serialize(Json.RawValue value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeObject(value.value());
    }
}
