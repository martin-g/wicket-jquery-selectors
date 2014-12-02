package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.Attr;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * java abstraction of JQuery <em>on</em> function. Attach an event handler function for one or more events to the
 * selected elements.
 */
public final class OnJqueryFunction extends AbstractFunction {

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param events The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final Attr events, JavaScriptInlineFunction handler) {
        return new OnJqueryFunction(events, Attr.nullValue(), null, handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param events The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final CharSequence events, JavaScriptInlineFunction handler) {
        return new OnJqueryFunction(JQuery.quoted(events), Attr.nullValue(), Attr.nullValue(), handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param selector The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final Attr events, final Attr selector, JavaScriptInlineFunction handler) {
        return on(events, selector, Attr.nullValue(), handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param selector The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final Attr events, final Attr selector, final Attr data, JavaScriptInlineFunction handler) {
        return new OnJqueryFunction(events, selector, data, handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param selector The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final Attr events, final Attr selector, final Object data, JavaScriptInlineFunction handler) {
        return on(events, selector, JQuery.auto(data), handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param selector The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final CharSequence events, final CharSequence selector, JavaScriptInlineFunction handler) {
        return on(JQuery.quoted(events), JQuery.quoted(selector), Attr.nullValue(), handler);
    }

    /**
     * creates a new {@link OnJqueryFunction} instance
     *
     * @param selector The CSS selector for event delegation
     * @return new {@link OnJqueryFunction} instance
     */
    public static OnJqueryFunction on(final CharSequence events, final CharSequence selector, final Object data, JavaScriptInlineFunction handler) {
        return new OnJqueryFunction(JQuery.quoted(events), JQuery.quoted(selector), JQuery.auto(data), handler);
    }

    /**
     * Construct.
     *
     * @param events   One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
     * @param selector A selector string to filter the descendants of the selected elements that trigger the event.
     *                 If the selector is null or omitted, the event is always triggered when it reaches the selected element.
     * @param data     Data to be passed to the handler in event.data when an event is triggered.
     * @param handler  A function to execute when the event is triggered. The value false is also allowed as a shorthand for
     *                 a function that simply does return false.
     */
    protected OnJqueryFunction(final Attr events, final Attr selector, final Attr data, final JavaScriptInlineFunction handler) {
        super("on");

        addParameter(events);

        if (!Attr.isNullOrEmpty(selector)) {
            addParameter(selector);
        }

        if (!Attr.isNullOrEmpty(data)) {
            // if selector isn't present, add null value.
            if (Attr.isNullOrEmpty(selector)) {
                addParameter(Attr.nullValue());
            }

            addParameter(data);
        }

        handler.addParameter("evt");
        addParameter(toParameterValue(handler));
    }
}
