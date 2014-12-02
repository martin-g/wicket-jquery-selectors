package de.agilecoders.wicket.jquery;

/**
*
*/
public class SimpleConfig extends AbstractConfig {
    private static final IKey<String> string = newKey("string", null);
    private static final IKey<Integer> integer = newKey("integer", null);

    public SimpleConfig() {
        put(string, "1");
        put(integer, 1);
    }
}
