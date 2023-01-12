wicket-jquery-selectors
=======================

Utility library for working with JQuery and Apache Wicket.

Current release version:

* For Wicket 10.x use 4.x
* For Wicket 9.x  use 3.x
* For Wicket 8.x  use 2.x
* For Wicket 7.x  use 0.2.x
* For Wicket 6.x  use 0.1.x


## Configuration

configure wicket-jquery-selectors in application init method (optional)

```java
WicketJquerySelectors.install(new WicketJquerySelectorsSettings().setObjectMapperFactory(new MyCustomObjectMapperFactory()));
```

## Usage

The following example uses the [bootstrap typeahead](http://getbootstrap.com/2.3.2/javascript.html#typeahead) jquery plugin:

### Config classes

Create a subclass of `AbstractConfig` (or use one of the existing configs) to build the options object for a JQuery plugin. You can omit
this step if the JQuery plugin doesn't need any options:

```java

    import de.agilecoders.wicket.jquery.AbstractConfig;
    import de.agilecoders.wicket.jquery.IDataSource;

    public class TypeaheadConfig extends AbstractConfig {

        private static final IKey<IDataSource> Source = newKey("source", null);
        private static final IKey<Integer> MinLength = newKey("minLength", 1);

        public TypeaheadConfig withDataSource(final IDataSource<?> value) {
            put(Source, value);
            return this;
        }

        public TypeaheadConfig withMinLength(final int value) {
            put(MinLength, value);
            return this;
        }
    }

```

### JQuery method chaining

```java

    import static de.agilecoders.wicket.jquery.JQuery.$;

    public class Typeahead<T> extends TextField<T> {

        @Override
        public void renderHead(final IHeaderResponse response) {
            super.renderHead(response);

            // setup the options
            TypeaheadConfig config = new TypeaheadConfig();
            config.withDataSource(dataSource);
            config.withMinLength(3);

            // render the header contribution
            response.render($(this).chain("typeahead", config).asDomReadyScript());
        }
    }

```

The above code will generate the following javascript:


```javascript

    $('#wicketComponentId').typeahead({"dataSource" : "<wicket-ajax-callback>", "minLength": 3});

```