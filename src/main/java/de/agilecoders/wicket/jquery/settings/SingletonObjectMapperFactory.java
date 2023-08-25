package de.agilecoders.wicket.jquery.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * special {@link DefaultObjectMapperFactory} that holds mapper as singleton instance.
 *
 * @author Michael Haitz michael.haitz@agilecoders.de
 */
public class SingletonObjectMapperFactory extends DefaultObjectMapperFactory {

    private final ObjectMapper mapper;

    /**
     * Construct.
     */
    public SingletonObjectMapperFactory() {
        super();

        mapper = super.newObjectMapper();
    }

    /**
     * Construct.
     *
     * @param mapper the object mapper to use
     */
    public SingletonObjectMapperFactory(ObjectMapper mapper) {
        super();

        this.mapper = mapper;
    }

    @Override
    public final ObjectMapper newObjectMapper() {
        return mapper;
    }
}
