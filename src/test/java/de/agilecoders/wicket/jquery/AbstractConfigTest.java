package de.agilecoders.wicket.jquery;

import com.fasterxml.jackson.databind.JsonNode;
import de.agilecoders.wicket.jquery.util.Json;
import org.junit.Assert;
import org.junit.Test;

import static de.agilecoders.wicket.jquery.JQuery.$;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for serializing AbstractConfig to JSON
 */
public class AbstractConfigTest extends Assert {

    @Test
    public void simpleConfig() {
        String value = new SimpleConfig().toJsonString();

        assertThat(value, containsString("\"string\":\"1\""));
        assertThat(value, containsString("\"integer\":1"));
    }

    @Test
    public void nestedConfigs() {
        String value = new NestedConfig().toJsonString();

        JsonNode json = Json.parse(value);
        assertThat(json.get("testConfig").get("string").asText(), is("1"));
        assertThat(json.get("testConfig").get("integer").asInt(), is(1));
        assertThat(json.get("string").asText(), is("2"));
    }

    @Test
    public void rawValue() {
        assertEquals("{\"raw\":Hogan}", new RawValueConfig().toJsonString());
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
    }

    @Test
    public void emptyConfig() {
        EmptyConfig configOne = new EmptyConfig();
        SimpleConfig configTwo = new SimpleConfig();
        String script = $(".foo").chain("foo", configOne, configTwo).get();

        assertThat(script, startsWith("$('.foo').foo({},{"));
        assertThat(script, endsWith("});"));
        assertThat(script, containsString("\"integer\":1"));
        assertThat(script, containsString("\"string\":\"1\""));
    }

    private static class RawValueConfig extends AbstractConfig {
        private static final IKey<Json.RawValue> raw = newKey("raw", null);

        private RawValueConfig() {
            put(raw, new Json.RawValue("Hogan"));
        }
    }

    static class SimpleConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", null);
        private static final IKey<Integer> integer = newKey("integer", null);

        SimpleConfig() {
            put(string, "1");
            put(integer, 1);
        }
    }

    static class EmptyConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", null);
        private static final IKey<Integer> integer = newKey("integer", null);
    }

    private static class NestedConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", "1");
        private static final IKey<SimpleConfig> testConfig = newKey("testConfig", new SimpleConfig());

        private NestedConfig() {
            put(string, "2");
            put(testConfig, new SimpleConfig());
        }
    }

}
