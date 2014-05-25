package de.agilecoders.wicket.jquery.util;

import org.apache.wicket.Component;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

/**
 * helper class to handle string interaction
 *
 * @author miha
 */
public final class Strings2 {

    /**
     * ensures a non null value.
     *
     * @param value string value to transform to an empty string if it's null
     * @return non null value
     */
    public static String nullToEmpty(final String value) {
        return value != null ? value : "";
    }

    /**
     * Returns a markup id that is JQuery-safe and could be used as a selector.
     *
     * @param component the component which markup id should be return
     * @return the component's markup id that is escaped so that it could be used as JQuery selector
     */
    public static CharSequence getMarkupId(final Component component) {
        Args.notNull(component, "component");
        String markupId = component.getMarkupId(true);
        return escapeMarkupId(markupId);
    }

    /**
     * Returns a markup id that is JQuery-safe and could be used as a selector.
     *
     * @param markupId the markup id to escape
     * @return the component's markup id that is escaped so that it could be used as JQuery selector
     */
    public static CharSequence escapeMarkupId(final String markupId) {
        Args.notNull(markupId, "markupId");
        return Strings.replaceAll(markupId, ".", "\\\\.");
    }

    /**
     * private constructor to prevent instantiation of util class.
     */
    private Strings2() {
        throw new UnsupportedOperationException();
    }
}
