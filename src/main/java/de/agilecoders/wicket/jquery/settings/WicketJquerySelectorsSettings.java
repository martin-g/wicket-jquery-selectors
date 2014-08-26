package de.agilecoders.wicket.jquery.settings;

/**
 * default settings implementation.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class WicketJquerySelectorsSettings implements IWicketJquerySelectorsSettings {

    private ObjectMapperFactory objectMapperFactory;

    /**
     * Construct.
     */
    public WicketJquerySelectorsSettings() {
        objectMapperFactory = new SingletonObjectMapperFactory();
    }

    /**
     * @return object mapper factory
     */
    @Override
    public ObjectMapperFactory getObjectMapperFactory() {
        return objectMapperFactory;
    }

    /**
     * sets the object mapper factory
     *
     * @param objectMapperFactory the object mapper factory to use for json serialization
     * @return this instance for chaining
     */
    public WicketJquerySelectorsSettings setObjectMapperFactory(ObjectMapperFactory objectMapperFactory) {
        this.objectMapperFactory = objectMapperFactory;
        return this;
    }
}
