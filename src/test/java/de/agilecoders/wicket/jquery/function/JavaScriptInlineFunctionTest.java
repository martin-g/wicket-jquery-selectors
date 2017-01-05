package de.agilecoders.wicket.jquery.function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class JavaScriptInlineFunctionTest extends Assert {

	@Test
	public void javascriptInlineFunctionWithAddParameter() {
		JavaScriptInlineFunction function = new JavaScriptInlineFunction("alert(event);");
		function.addParameter("event");
		assertThat(function.build(), is(equalTo("function(event){alert(event);}")));
	}
}
