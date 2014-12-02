package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.SimpleConfig;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ConfigurableFunctionTest extends Assert {

    @Test
    public void one() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"integer\":1,\"string\":\"1\"})")));
    }

    @Test
    public void two() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig(), new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"integer\":1,\"string\":\"1\"},{\"integer\":1,\"string\":\"1\"})")));
    }

    @Test
    public void three() {
        ConfigurableFunction function = new ConfigurableFunction("fName", new SimpleConfig(), new SimpleConfig(), new SimpleConfig());
        assertThat(function.build(), is(equalTo("fName({\"integer\":1,\"string\":\"1\"},{\"integer\":1,\"string\":\"1\"},{\"integer\":1,\"string\":\"1\"})")));
    }
}
