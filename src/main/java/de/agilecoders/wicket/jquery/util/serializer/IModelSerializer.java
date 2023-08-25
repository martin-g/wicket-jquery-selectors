package de.agilecoders.wicket.jquery.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.wicket.model.IModel;

import java.io.IOException;

/**
 * {@link IModel} json serializer
 *
 * @author Michael Haitz michael.haitz@agilecoders.de
 */
public class IModelSerializer extends JsonSerializer<IModel> {

    @Override
    public void serialize(IModel value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        Object obj = value.getObject();

        if (obj instanceof CharSequence) {
            jsonGenerator.writeString(obj.toString());
        } else {
            jsonGenerator.writeObject(obj);
        }
    }

}
