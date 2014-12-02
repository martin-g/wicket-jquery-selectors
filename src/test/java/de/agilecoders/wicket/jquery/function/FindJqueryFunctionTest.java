package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.JQuery;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindJqueryFunctionTest extends Assert {

    @Test
    public void find() {
        FindJqueryFunction function = new FindJqueryFunction(JQuery.quoted(".selector"));
        assertThat(function.build(), is(equalTo("find('.selector')")));
    }
}
