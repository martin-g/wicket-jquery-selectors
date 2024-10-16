package de.agilecoders.wicket.jquery.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link com.fasterxml.jackson.databind.ObjectMapper} factory
 *
 * @author Michael Haitz
 */
public interface ObjectMapperFactory {

    /**
     * @return new object mapper instance
     */
    ObjectMapper newObjectMapper();
}
