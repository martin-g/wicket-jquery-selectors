package de.agilecoders.wicket.jquery;

import com.fasterxml.jackson.databind.JsonNode;
import de.agilecoders.wicket.jquery.util.Json;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MutableConfigTest {
    private static Key<String> key1 = new Key<String>("key1", "defaultValue");
    private static Key<String> key2 = new Key<String>("key2", "defaultValue2");
    private static Key<String> key3 = new Key<String>("key3");
    private static Key<String> key4 = new Key<String>("key4", "defaultValue4");
    private static Key<String> key5 = new Key<String>("key5", "defaultValue5");
    private static Key<String> key6 = new Key<String>("key6", "defaultValue6");

    private CombinableConfig base;

    private Config fallback;

    private Config combined;

    @Before
    public void setUp() throws Exception {
        base = new MutableConfig()
                .put(key1, "value1")
                .put(key2, "defaultValue2")
                .put(key3, "value3")
                .put(key4, "value4");

        fallback = new MutableConfig()
                .put(key5, "value5")
                .put(key3, "value3b")
                .put(key6, "defaultValue6");

        combined = base.withFallback(fallback);
    }

    @Test
    public void removeRemovesFromBoth() throws Exception {
        assertThat(combined.remove(key3), is("value3"));
        assertThat(combined.contains(key3), is(false));
        assertThat(fallback.contains(key3), is(false));
        assertThat(base.contains(key3), is(false));
    }

    @Test
    public void putDefaultRemovesKey() throws Exception {
        combined.put(key3, key3.getDefaultValue());

        assertThat(combined.contains(key3), is(false));
        assertThat(fallback.contains(key3), is(false));
        assertThat(base.contains(key3), is(false));
    }

    @Test
    public void putNonDefaultUpdatesBoth() throws Exception {
        combined.put(key3, "newValue3");
        assertThat(combined.get(key3), is("newValue3"));
        assertThat(fallback.get(key3), is("newValue3"));
        assertThat(base.get(key3), is("newValue3"));

        combined.put(key5, "newValue5");
        assertThat(combined.get(key5), is("newValue5"));
        assertThat(fallback.get(key5), is("newValue5"));
        assertThat(base.get(key5), is("newValue5"));

        combined.put(key4, "newValue4");
        assertThat(combined.get(key4), is("newValue4"));
        assertThat(base.get(key4), is("newValue4"));
        assertThat(fallback.get(key4), is(key4.getDefaultValue()));
    }

    @Test
    public void getReturnsValueFromBase() throws Exception {
        assertThat(combined.get(key1), is("value1"));
        assertThat(combined.get(key2), is("defaultValue2"));
        assertThat(combined.get(key3), is("value3"));
        assertThat(combined.get(key4), is("value4"));
    }

    @Test
    public void getReturnsValueFromFallback() throws Exception {
        assertThat(combined.get(key5), is("value5"));
        assertThat(combined.get(key6), is("defaultValue6"));
    }

    @Test
    public void toJsonContainsOnlyNonDefaultValues() throws Exception {
        JsonNode json = Json.parse(combined.toJsonString());

        assertThat(json.get(key1.key()).asText(), is("value1"));
        assertThat(json.get(key3.key()).asText(), is("value3"));
        assertThat(json.get(key4.key()).asText(), is("value4"));
        assertThat(json.get(key5.key()).asText(), is("value5"));

        assertThat(json.has(key2.key()), is(false));
        assertThat(json.has(key6.key()), is(false));
    }
}