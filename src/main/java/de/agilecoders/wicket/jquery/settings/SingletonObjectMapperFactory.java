package de.agilecoders.wicket.jquery.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * special {@link DefaultObjectMapperFactory} that holds mapper as singleton instance.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
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

    @Override
    public ObjectMapper newObjectMapper() {
        return mapper;
    }
}
