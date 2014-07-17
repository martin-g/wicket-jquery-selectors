package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.util.Json;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import static de.agilecoders.wicket.jquery.JQuery.auto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AttrTest {

    @Test
    public void autoCastsValue() throws Exception {
        assertThat(auto(1).toString(), is("1"));
        assertThat(auto("abcd").toString(), is("'abcd'"));
        assertThat(auto(Json.parse("{key:1,asString:'1'}")).toString(), is("{\"key\":1,\"asString\":\"1\"}"));
    }

    @Test
    public void nullValue() throws Exception {
        assertThat(Attr.nullValue().toString(), is("null"));
    }

    @Test
    public void quoted() throws Exception {
        assertThat(new Attr.Quoted("value").toString(), is("'value'"));
    }

    @Test
    public void markupId() throws Exception {
        assertThat(new Attr.MarkupId("value").quoted().toString(), is("'#value'"));

        WicketTester tester = new WicketTester();
        Label component = new Label("someId", "Value");
        component.setMarkupId("someMarkupId");
        assertThat(new Attr.MarkupId(component).quoted().toString(), is("'#someMarkupId'"));
        tester.destroy();
    }

    @Test
    public void markupIdDefault() throws Exception {
        assertThat(new Attr.MarkupId("value").toString(), is("#value"));

        WicketTester tester = new WicketTester();
        Label component = new Label("someId", "Value");
        component.setMarkupId("someMarkupId");
        assertThat(new Attr.MarkupId(component).toString(), is("#someMarkupId"));
        tester.destroy();
    }

    @Test
    public void plain() throws Exception {
        assertThat(new Attr.Plain("value").toString(), is("value"));
    }
}