package de.agilecoders.wicket.jquery.function;

import de.agilecoders.wicket.jquery.JQuery;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ClosestJqueryFunctionTest {

    @Test
    public void withSelector() {
        ClosestJqueryFunction function = new ClosestJqueryFunction(JQuery.quoted(".selector"));
        assertThat(function.build(), is(equalTo("closest('.selector')")));
    }
}
