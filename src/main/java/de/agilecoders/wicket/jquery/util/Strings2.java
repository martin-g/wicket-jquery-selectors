package de.agilecoders.wicket.jquery.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.apache.wicket.Component;
import org.apache.wicket.util.lang.Args;

/**
 * helper class to handle string interaction
 *
 * @author miha
 */
public final class Strings2 {
	private static char[] ESCAPE_CHARS = new char[] { '!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '.', '/', ':', ';', '<', '>', '=', '?', '@', '[',
			']', '\\', '^', '`', '{', '}', '|', '~', '\'' };

	/**
	 * ensures a non null value.
	 *
	 * @param value
	 *            string value to transform to an empty string if it's null
	 * @return non null value
	 */
	public static String nullToEmpty(final String value) {
		return value != null ? value : "";
	}

	/**
	 * Returns a markup id that is JQuery-safe and could be used as a selector.
	 *
	 * @param component
	 *            the component which markup id should be return
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
	 * @param markupId
	 *            the markup id to escape
	 * @return the component's markup id that is escaped so that it could be used as JQuery selector
	 */
	public static CharSequence escapeMarkupId(final CharSequence markupId) {
		Args.notNull(markupId, "markupId");

		// create pattern for: !"#$%&'()*+,./:;<=>?@[\]^`{|}~
		final StringCharacterIterator iterator = new StringCharacterIterator(markupId.toString());
		final StringBuilder result = new StringBuilder((int) (markupId.length() * 1.5));
		final String escape = "\\\\";

		char c = iterator.current();
		while (c != CharacterIterator.DONE) {
			boolean escaped = false;
			for (char x : ESCAPE_CHARS) {
				if (x == c) {
					result.append(escape).append(c);
					escaped = true;
					break;
				}
			}

			if (!escaped) {
				result.append(c);
			}

			c = iterator.next();
		}

		return result.toString();
	}

	/**
	 * private constructor to prevent instantiation of util class.
	 */
	private Strings2() {
		throw new UnsupportedOperationException();
	}
}
