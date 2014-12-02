package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.function.AbstractFunction;
import de.agilecoders.wicket.jquery.function.IFunction;
import de.agilecoders.wicket.jquery.function.JavaScriptInlineFunction;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.time.Duration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static de.agilecoders.wicket.jquery.JQuery.$;
import static de.agilecoders.wicket.jquery.function.EachJqueryFunction.each;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


/**
 * Tests the {@link JQuery} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class JQueryTest {

    @Test
    public void eachIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(each(new JavaScriptInlineFunction("alert('body');"))).get(),
                   is(equalTo("$('.selector ul li.classname').each(function(){alert('body');});")));
    }

    @Test
    public void twoDifferentInstancesWithSameBodyAreEqual() {
        JavaScriptInlineFunction funcA = new JavaScriptInlineFunction("body");
        JavaScriptInlineFunction funcB = new JavaScriptInlineFunction("bo" + "dy");

        assertThat(funcA.equals(funcB), is(equalTo(true)));
        assertThat(funcA.equals("body"), is(equalTo(true)));
        assertThat(funcA.hashCode(), is(equalTo(funcB.hashCode())));
    }

    @Test
    public void twoDifferentInstancesWithDifferentBodiesAreUnequal() {
        JavaScriptInlineFunction funcA = new JavaScriptInlineFunction("body1");
        JavaScriptInlineFunction funcB = new JavaScriptInlineFunction("body2");

        assertThat(funcA.equals(funcB), is(equalTo(false)));
        assertThat(funcA.equals("body2"), is(equalTo(false)));
        assertThat(funcA.hashCode(), is(not(equalTo(funcB.hashCode()))));
    }

    @Test
    public void selectorIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").get(),
                is(equalTo("$('.selector ul li.classname');")));
    }

    @Test
    public void closestWithStringSelector() {
        assertThat($(".selector div").closest("body").get(),
                   is(equalTo("$('.selector div').closest('body');")));
    }

    @Test
    public void closestWithAttrSelector() {
        assertThat($(".selector div").closest(new Attr.Quoted("body")).get(),
                   is(equalTo("$('.selector div').closest('body');")));
        assertThat($(".selector div").closest(new Attr.Plain("document.body")).get(),
                   is(equalTo("$('.selector div').closest(document.body);")));
    }

    @Test
    public void jsFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new HelperFunction("setTimeout").addParam(new JavaScriptInlineFunction("alert('Hello');")).addParam(Duration.seconds(1))).get(),
                   is(equalTo("$('.selector ul li.classname').setTimeout(function(){alert('Hello');},1000);")));
    }

    @Test
    public void chainedFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new IFunction() {
                    @Override
                    public String build() {
                        return "function()";
                    }
                }).get(),
                   is(equalTo("$('.selector ul li.classname').function();")));
    }

    @Test
    public void chainedAbstractFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new HelperFunction("function").addParam("String").addParam(true).addParam(null).addParam(1.567f).addParam(1).addParam(Long.MAX_VALUE)).get(),
                   is(equalTo("$('.selector ul li.classname').function('String',true,null,1.567,1,9223372036854775807);")));
    }

    @Test
    public void $component() {
        WicketTester tester = new WicketTester();
        Label component = new Label("someId", "Value");
        component.setMarkupId("someMarkupId");
        assertThat($(component).get(), is(equalTo("$('#someMarkupId');")));
        tester.destroy();
    }

    @Test
    public void $componentWithAdditionalSelectors() {
        WicketTester tester = new WicketTester();
        Label component = new Label("someId", "Value");
        component.setMarkupId("someMarkupId");

        assertThat($(component, "div", "ul").get(), is(equalTo("$('#someMarkupId div ul');")));
        tester.destroy();
    }

    @Test
    public void $componentWithDotsInTheMarkupId() {
        WicketTester tester = new WicketTester();
        Label component = new Label("someId", "Value");
        component.setMarkupId("some.markup.id");
        assertThat($(component).get(), is(equalTo("$('#some\\.markup\\.id');")));
        tester.destroy();
    }

    @Test
    public void multipleConfigurations() {
        SimpleConfig configOne = new SimpleConfig();
        SimpleConfig configTwo = new SimpleConfig();
        String script = $(".foo").chain("foo", configOne, configTwo).get();
        assertThat(script, startsWith("$('.foo').foo({"));
        assertThat(script, endsWith("});"));

        assertThat(script, containsString("\"integer\":1"));
        assertThat(script, containsString("\"string\":\"1\""));
        assertThat(script, containsString("},{"));
    }

    @Test
    public void emptyConfigWithExtraConfig() {
        AbstractConfigTest.EmptyConfig emptyConfig = new AbstractConfigTest.EmptyConfig();
        SimpleConfig nonEmptyConfig = new SimpleConfig();
        String script = $(".foo").chain("foo", emptyConfig, nonEmptyConfig).get();
        assertThat(script, startsWith("$('.foo').foo({"));
        assertThat(script, endsWith("});"));

        assertThat(script, containsString("\"integer\":1"));
        assertThat(script, containsString("\"string\":\"1\""));
    }

    @Test
    public void emptyConfig() {
        AbstractConfigTest.EmptyConfig emptyConfig = new AbstractConfigTest.EmptyConfig();
        String script = $(".foo").chain("foo", emptyConfig).get();
        assertThat(script, is(equalTo("$('.foo').foo();")));
    }

    @Test
    public void findNullSelector() {
        String findNullSelector = $(".foo").find(null).get();
        assertThat(findNullSelector, is("$('.foo').find(null);"));
    }

    @Test
    public void findEmptySelectorQuoted() {
        String findEmptySelector = $(".foo").find("").get();
        assertThat(findEmptySelector, is("$('.foo').find(null);"));
    }

    @Test
    public void findEmptySelectorPlain() {
        String findEmptySelector = $(".foo").find(JQuery.plain("")).get();
        assertThat(findEmptySelector, is("$('.foo').find(null);"));
    }

    @Test
    public void findSelector() {
        String findEmptySelector = $(".foo").find(".childSelector").get();
        assertThat(findEmptySelector, is("$('.foo').find('.childSelector');"));
    }

    /**
     * helper to build an {@link IFunction}
     */
    private static final class HelperFunction extends AbstractFunction {

        /**
         * Construct.
         *
         * @param functionName The function name of this {@link IFunction} implementation
         */
        protected HelperFunction(final String functionName) {
            super(functionName);
        }

        /**
         * adds a parameter
         *
         * @param value the value to add
         * @return this instance for chaining
         */
        public HelperFunction addParam(final Object value) {
            addParameter(toParameterValue(value));
            return this;
        }
    }

}
