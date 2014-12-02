package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.Attr;
import de.agilecoders.wicket.jquery.JQuery;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ClosestJqueryFunctionTest extends Assert {

    @Test
    public void withSelector() {
        ClosestJqueryFunction function = new ClosestJqueryFunction(JQuery.quoted(".selector"));
        assertThat(function.build(), is(equalTo("closest('.selector')")));
    }
}
