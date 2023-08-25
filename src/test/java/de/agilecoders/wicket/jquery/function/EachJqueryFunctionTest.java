package de.agilecoders.wicket.jquery.function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class EachJqueryFunctionTest {

    @Test
    public void each() {
        EachJqueryFunction function = new EachJqueryFunction(new JavaScriptInlineFunction("debugger;"));
        assertThat(function.build(), is(equalTo("each(function(){debugger;})")));
    }
}
