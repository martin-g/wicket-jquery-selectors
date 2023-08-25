package de.agilecoders.wicket.jquery.util;

import de.agilecoders.wicket.jquery.SimpleConfig;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test for CharSequenceWrapper
 */
public class CharSequenceWrapperTest {

    @Test
    public void config() {
        CharSequence cs = new CharSequenceWrapper(new SimpleConfig());
        assertThat(cs.toString(), is(equalTo("{\"string\":\"1\",\"integer\":1}")));
    }
}
