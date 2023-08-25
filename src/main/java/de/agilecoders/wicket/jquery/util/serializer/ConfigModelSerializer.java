package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.agilecoders.wicket.jquery.ConfigModel;

import java.io.IOException;

/**
 * {@link de.agilecoders.wicket.jquery.ConfigModel} json serializer
 *
 * @author Michael Haitz michael.haitz@agilecoders.de
 */
public class ConfigModelSerializer extends JsonSerializer<ConfigModel> {

    @Override
    public void serialize(ConfigModel value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(value.getObject());
    }

}
