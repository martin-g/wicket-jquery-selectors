package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.function.ClosestJqueryFunction;
import de.agilecoders.wicket.jquery.function.ConfigurableFunction;
import de.agilecoders.wicket.jquery.function.FindJqueryFunction;
import de.agilecoders.wicket.jquery.function.IFunction;
import de.agilecoders.wicket.jquery.function.JavaScriptInlineFunction;
import de.agilecoders.wicket.jquery.function.OnJqueryFunction;
import de.agilecoders.wicket.jquery.function.SimpleFunction;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.string.Strings;

import java.util.List;
import java.util.function.Function;

/**
 * The Jquery class helps to keep all javascript jquery scripts type safe.
 *
 * @author miha
 */
@SuppressWarnings("UnusedDeclaration")
public class JQuery implements IClusterable {

    /**
     * creates a quoted attribute
     *
     * @param value the selector
     * @return quoted attribute
     */
    public static Attr quoted(final CharSequence value) {
        if (!Strings.isEmpty(value)) {
            return new Attr.Quoted(value);
        } else {
            return Attr.nullValue();
        }
    }

    /**
     * creates a plain attribute without quotes
     *
     * @param value the selector
     * @return plain attribute
     */
    public static Attr plain(final CharSequence value) {
        if (!Strings.isEmpty(value)) {
            return new Attr.Plain(value);
        } else {
            return Attr.nullValue();
        }
    }

    /**
     * creates a quoted markup id selector
     *
     * @param component the component to extract markup id
     * @return plain attribute
     */
    public static Attr.MarkupId markupId(final Component component) {
        return new Attr.MarkupId(component);
    }

    /**
     * creates a quoted markup id selector
     *
     * @param markupId the markup id
     * @return plain attribute
     */
    public static Attr.MarkupId markupId(final CharSequence markupId) {
        return new Attr.MarkupId(markupId);
    }

    /**
     * creates a auto detect attribute
     *
     * @param value the selector
     * @return the attribute value
     */
    public static Attr auto(final Object value) {
        if (value instanceof Attr) {
            return (Attr) value;
        } else if (value != null) {
            return new Attr.Auto(value);
        } else {
            return Attr.nullValue();
        }
    }

    /**
     * Function that maps an {@link IFunction} to its string representation.
     */
    private static final Function<IFunction, String> FUNCTION_TRANSFORMER = new Function<IFunction, String>() {
        @Override
        public String apply(final IFunction function) {
            return function != null ? function.build() : null;
        }
    };

    /**
     * helper method to allow a jquery like code style. The selector will be quoted.
     * <p/>
     * <pre>
     *     JQuery.$("selector") // = $('selector')
     * </pre>
     *
     * @param selector The jquery selector
     * @return new Jquery instance
     */
    public static JQuery $(final CharSequence selector) {
        return new JQuery(quoted(selector));
    }

    /**
     * helper method to allow a jquery like code style
     * <p/>
     * <pre>
     *     JQuery.$(plain("document")) // = $(document)
     * </pre>
     *
     * @param selector The jquery selector
     * @return new Jquery instance
     */
    public static JQuery $(final Attr selector) {
        if (selector instanceof Attr.MarkupId) {
            return new JQuery(((Attr.MarkupId) selector).quoted());
        } else {
            return new JQuery(selector);
        }

    }

    /**
     * helper method to allow a jquery like code style
     *
     * @param component The markup id of given component is used as jquery selector
     * @return new Jquery instance
     */
    public static JQuery $(final Component component) {
        return $(markupId(component));
    }

    /**
     * helper method to allow a jquery like code style
     *
     * @param component          The markup id of given component is used as jquery selector
     * @param additionalSelector an additional initial selector
     * @return new Jquery instance
     */
    public static JQuery $(final Component component, final CharSequence... additionalSelector) {
        final List<Attr> selectors = Generics2.newArrayList();
        selectors.add(markupId(component));

        if (additionalSelector != null) {
            for (CharSequence selector : additionalSelector) {
                selectors.add(plain(selector));
            }
        }

        return $(Generics2.join(selectors, ' '));
    }

    /**
     * helper method to allow a jquery like code style
     *
     * @param component          The markup id of given component is used as jquery selector
     * @param additionalSelector an additional initial selector
     * @return new Jquery instance
     */
    public static JQuery $(final Component component, final Attr... additionalSelector) {
        final List<Attr> selector = Generics2.newArrayList();
        selector.add(markupId(component));

        if (additionalSelector != null) {
            selector.addAll(Generics2.newArrayList(additionalSelector));
        }

        return $(Generics2.join(selector, ' '));
    }

    public static JQuery $() {
        return $(Attr.noSelector());
    }

    private final Attr selector;
    private final List<IFunction> functions;

    /**
     * Construct.
     *
     * @param selector the selector to use.
     */
    private JQuery(final Attr selector) {
        this.selector = selector;
        this.functions = Generics2.newArrayList();
    }

    /**
     * adds a chained function to this jquery instance
     *
     * @param function the function to add
     * @return this instance for chaining
     */
    public JQuery chain(final IFunction function) {
        functions.add(function);
        return this;
    }

    /**
     * Attach an event handler function for one or more events to the selected elements. The {@code events} parameter
     * will be quoted.
     *
     * @param events  One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
     * @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for
     *                a function that simply does return false.
     * @return this instance for chaining.
     */
    public JQuery on(CharSequence events, JavaScriptInlineFunction handler) {
        return chain(OnJqueryFunction.on(events, handler));
    }

    /**
     * Attach an event handler function for one or more events to the selected elements. The {@code events} and
     * {@code selector} parameter will be quoted.
     *
     * @param events   One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
     * @param selector A selector string to filter the descendants of the selected elements that trigger the event.
     *                 If the selector is null or omitted, the event is always triggered when it reaches the selected element.
     * @param handler  A function to execute when the event is triggered. The value false is also allowed as a shorthand for
     *                 a function that simply does return false.
     * @return this instance for chaining.
     */
    public JQuery on(CharSequence events, CharSequence selector, JavaScriptInlineFunction handler) {
        return on(quoted(events), quoted(selector), handler);
    }

    /**
     * Attach an event handler function for one or more events to the selected elements.
     *
     * @param events   One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
     * @param selector A selector string to filter the descendants of the selected elements that trigger the event.
     *                 If the selector is null or omitted, the event is always triggered when it reaches the selected element.
     * @param handler  A function to execute when the event is triggered. The value false is also allowed as a shorthand for
     *                 a function that simply does return false.
     * @return this instance for chaining.
     */
    public JQuery on(Attr events, Attr selector, JavaScriptInlineFunction handler) {
        return chain(OnJqueryFunction.on(events, selector, handler));
    }

    /**
     * Attach an event handler function for one or more events to the selected elements.
     *
     * @param events  One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
     * @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for
     *                a function that simply does return false.
     * @return this instance for chaining.
     */
    public JQuery on(Attr events, JavaScriptInlineFunction handler) {
        return on(events, Attr.nullValue(), handler);
    }

    /**
     * For each element in the set, get the first element that matches the selector by testing the element itself and
     * traversing up through its ancestors in the DOM tree.
     *
     * @param selector the selector to use (will be quoted).
     * @return this instance for chaining
     */
    public JQuery closest(CharSequence selector) {
        return closest(quoted(selector));
    }

    /**
     * For each element in the set, get the first element that matches the selector by testing the element itself and
     * traversing up through its ancestors in the DOM tree.
     *
     * @param selector the selector to use.
     * @return this instance for chaining
     */
    public JQuery closest(Attr selector) {
        return chain(ClosestJqueryFunction.closest(selector));
    }

    /**
     * Get the descendants of each element in the current set of matched elements, filtered by a selector.
     *
     * @param selector the selector to use (will be quoted).
     * @return this instance for chaining
     */
    public JQuery find(CharSequence selector) {
        return find(quoted(selector));
    }

    /**
     * Get the descendants of each element in the current set of matched elements, filtered by a selector.
     *
     * @param selector the selector to use.
     * @return this instance for chaining
     */
    public JQuery find(Attr selector) {
        return chain(new FindJqueryFunction(selector));
    }

    /**
     * adds a chained function to this jquery instance
     *
     * @param functionName the function to add
     * @return this instance for chaining
     */
    public JQuery chain(final CharSequence functionName) {
        functions.add(new SimpleFunction(functionName));
        return this;
    }

    /**
     * @return this jquery chain as string and a semicolon as last char
     */
    public String get() {
        return build() + ";";
    }

    /**
     * @return this jquery chain as string but doesn't close chain with semicolon.
     */
    public String build() {
        StringBuilder builder = new StringBuilder();
        if (selector == Attr.noSelector()) {
            builder.append("$");
        } else {
            builder.append("$(").append(selector).append(")");
        }
        builder.append(createFunctionString());
        return builder.toString();
    }

    /**
     * @return this jquery script as {@link OnDomReadyHeaderItem} instance
     */
    public OnDomReadyHeaderItem asDomReadyScript() {
        return OnDomReadyHeaderItem.forScript(get());
    }

    /**
     * @return all functions as joined string.
     */
    private String createFunctionString() {
        return functions.isEmpty() ? "" : "." + Generics2.join(Generics2.transform(functions, FUNCTION_TRANSFORMER), '.');
    }

    /**
     * adds a chained function to this jquery instance
     *
     * @param functionName the function to add
     * @param config       the function configuration
     * @return this instance for chaining
     */
    public JQuery chain(final CharSequence functionName, final Config config, Config... extraConfigs) {
        functions.add(new ConfigurableFunction(functionName, config, extraConfigs));
        return this;
    }


}
