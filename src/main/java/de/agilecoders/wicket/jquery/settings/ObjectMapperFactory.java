package de.agilecoders.wicket.jquery.settings;

import tools.jackson.databind.ObjectMapper;

/**
 * {@link tools.jackson.databind.ObjectMapper} factory
 *
 * @author Michael Haitz
 */
public interface ObjectMapperFactory {

    /**
     * @return new object mapper instance
     */
    ObjectMapper newObjectMapper();
}
