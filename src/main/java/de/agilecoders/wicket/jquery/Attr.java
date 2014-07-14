package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.util.CharSequenceWrapper;
import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.util.lang.Args;

/**
 * Attr represents a jquery attribute.
 */
public abstract class Attr implements CharSequence {
    private static final Attr NULL_VALUE = new NullValue();

    private CharSequence selector;

    /**
     * Construct.
     *
     * @param selector the jquery selector
     */
    protected Attr(final CharSequence selector) {
        this.selector = Args.notEmpty(selector, "selector");
    }

    @Override
    public int length() {
        return selector.length();
    }

    @Override
    public char charAt(int index) {
        return selector.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return selector.subSequence(start, end);
    }

    @Override
    public String toString() {
        return selector.toString();
    }

    @Override
    public int hashCode() {
        return selector.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return selector.equals(obj);
    }

    /**
     * @return null value instance
     */
    public static Attr nullValue() {
        return NULL_VALUE;
    }

    /**
     * NullValue represents a jquery attribute that is "null".
     * <p/>
     * <pre>
     *     JQuery.$("selector").closest(new NullValue()); // = $('selector').closest(null);
     * </pre>
     */
    public static class NullValue extends Plain {
        /**
         * Construct.
         */
        protected NullValue() {
            super("null");
        }
    }

    /**
     * markup id jquery selector.
     */
    public static class MarkupId extends Attr {
        /**
         * Construct.
         *
         * @param component the component to extract markup id from
         */
        protected MarkupId(Component component) {
            super("'#" + Strings2.getMarkupId(Args.notNull(component, "component")) + "'");
        }

        /**
         * Construct.
         *
         * @param markupId the markup id
         */
        protected MarkupId(CharSequence markupId) {
            super("'#" + Strings2.escapeMarkupId(Args.notEmpty(markupId, "markupId")) + "'");
        }
    }

    /**
     * Plain jquery attribute, this attribute won't be quoted. Please make sure that the selector value won't be escaped
     * and therefor it's possible to create a XSS issue when using it without escaping.
     */
    public static class Plain extends Attr {
        /**
         * Construct.
         *
         * @param selector the jquery selector
         */
        protected Plain(CharSequence selector) {
            super(selector);
        }
    }

    /**
     * quoted jquery attribute. All " and ' will be escaped.
     */
    public static class Quoted extends Attr {
        /**
         * Construct.
         *
         * @param selector the jquery selector
         */
        protected Quoted(CharSequence selector) {
            super("'" + JavaScriptUtils.escapeQuotes(selector) + "'");
        }
    }

    /**
     * jquery attribute the value and type will be detect automatically.
     * <p/>
     * <pre>
     *     new Auto("value").toString(); // = 'value'
     *     new Auto(1234).toString(); // = 1234
     *     new Auto(Json.parse("{key:1234}")).toString(); // = {key:1234}
     * </pre>
     */
    public static class Auto extends Attr {
        /**
         * Construct.
         *
         * @param value the attribute value
         */
        protected Auto(Object value) {
            super(new CharSequenceWrapper(value));
        }
    }
}
