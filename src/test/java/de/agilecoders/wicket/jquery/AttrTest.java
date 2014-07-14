package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.util.Json;
import org.junit.Test;

import static de.agilecoders.wicket.jquery.JQuery.auto;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
    public void plain() throws Exception {
        assertThat(new Attr.Plain("value").toString(), is("value"));
    }
}