package de.agilecoders.wicket.jquery.util;

import de.agilecoders.wicket.jquery.SimpleConfig;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Test for CharSequenceWrapper
 */
public class CharSequenceWrapperTest extends Assert {

    @Test
    public void config() {
        CharSequence cs = new CharSequenceWrapper(new SimpleConfig());
        assertThat(cs.toString(), is(equalTo("{\"integer\":1,\"string\":\"1\"}")));
    }
}
