package de.agilecoders.wicket.jquery.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

/**
 * tests the {@link de.agilecoders.wicket.jquery.util.Strings2} class
 *
 * @author miha
 */
public class Strings2Test {

	@Test
	public void escapeCharacters() {
		assertThat(
				Strings2.escapeMarkupId("!\"#$%&'()*+,./:;<=>?@[\\]^`{|}~").toString(),
				is("\\\\!\\\\\"\\\\#\\\\$\\\\%\\\\&\\\\'\\\\(\\\\)\\\\*\\\\+\\\\,\\\\.\\\\/\\\\:\\\\;\\\\<\\\\=\\\\>\\\\?\\\\@\\\\[\\\\\\\\\\]\\\\^\\\\`\\\\{\\\\|\\\\}\\\\~"));

		assertThat(Strings2.escapeMarkupId("my.custom.markup-id").toString(), is("my\\\\.custom\\\\.markup-id"));
	}

}
