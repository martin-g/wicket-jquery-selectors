package de.agilecoders.wicket.jquery.function;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EachJqueryFunctionTest extends Assert {

    @Test
    public void each() {
        EachJqueryFunction function = new EachJqueryFunction(new JavaScriptInlineFunction("debugger;"));
        assertThat(function.build(), is(equalTo("each(function(){debugger;})")));
    }
}
