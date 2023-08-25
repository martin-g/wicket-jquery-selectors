package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.SimpleConfig;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ConfigurableFunctionTest {

    @Test
    public void one() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"string\":\"1\",\"integer\":1})")));
    }

    @Test
    public void two() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig(), new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"string\":\"1\",\"integer\":1},{\"string\":\"1\",\"integer\":1})")));
    }

    @Test
    public void three() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig(), new SimpleConfig(), new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"string\":\"1\",\"integer\":1},{\"string\":\"1\",\"integer\":1},{\"string\":\"1\",\"integer\":1})")));
    }
}
