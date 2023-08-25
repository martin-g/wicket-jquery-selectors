package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.agilecoders.wicket.jquery.Config;

import java.io.IOException;

/**
 * {@link de.agilecoders.wicket.jquery.Config} json serializer
 *
 * @author Michael Haitz michael.haitz@agilecoders.de
 */
public class ConfigSerializer extends JsonSerializer<Config> {
    @Override
    public void serialize(Config value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeRawValue(value.toJsonString());
    }
}
