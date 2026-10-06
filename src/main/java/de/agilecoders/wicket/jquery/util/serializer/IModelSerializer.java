package de.agilecoders.wicket.jquery.util.serializer;

import tools.jackson.core.JsonGenerator;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import org.apache.wicket.model.IModel;

/**
 * {@link IModel} json serializer
 *
 * @author Michael Haitz
 */
public class IModelSerializer extends ValueSerializer<IModel> {

    @Override
    public void serialize(IModel value, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        Object obj = value.getObject();

        if (obj instanceof CharSequence) {
            jsonGenerator.writeString(obj.toString());
        } else {
            jsonGenerator.writePOJO(obj);
        }
    }

}
