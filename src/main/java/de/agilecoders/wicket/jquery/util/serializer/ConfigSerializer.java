package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.RawValue;
import de.agilecoders.wicket.jquery.Config;

import java.io.IOException;

/**
 * {@link de.agilecoders.wicket.jquery.Config} json serializer
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class ConfigSerializer extends JsonSerializer<Config> {
    @Override
    public void serialize(Config value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeObject(new RV(value.toJsonString()));
    }

    /**
     * An extension of com.fasterxml.jackson.databind.util.RawValue that delegates
     * toString() to the rawValue
     */
    private static class RV extends RawValue
    {
        public RV(String v) {
            super(v);
        }

        @Override
        public String toString() {
            return rawValue().toString();
        }
    }
}
