package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.RawValue;
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
        jsonGenerator.writeObject(new RV(value.value()));
    }

    /**
     * An extension of com.fasterxml.jackson.databind.util.RawValue that delegates
     * toString() to the rawValue
     */
    private static class RV extends RawValue {

        public RV(String v) {
            super(v);
        }

        @Override
        public String toString() {
            return rawValue().toString();
        }
    }
}
