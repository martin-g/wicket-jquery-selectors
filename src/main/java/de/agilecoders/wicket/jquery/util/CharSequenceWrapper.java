package de.agilecoders.wicket.jquery.util;

import com.fasterxml.jackson.databind.JsonNode;
import de.agilecoders.wicket.jquery.Attr;
import de.agilecoders.wicket.jquery.JQuery;
import org.apache.wicket.util.time.Duration;

/**
 * cast given value to a {@link java.lang.CharSequence} and provides same interface.
 */
public class CharSequenceWrapper implements CharSequence {

    private CharSequence casted = "";

    /**
     * Construct.
     *
     * @param raw the raw value
     */
    public CharSequenceWrapper(Object raw) {
        this.casted = toParameterValue(raw);
    }

    @Override
    public int length() {
        return casted.length();
    }

    @Override
    public char charAt(int index) {
        return casted.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return casted.subSequence(start, end);
    }

    @Override
    public String toString() {
        return casted.toString();
    }

    @Override
    public int hashCode() {
        return casted.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return casted.equals(obj);
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final Object value) {
        if (value instanceof Long) {
            return toParameterValue((Long) value);
        } else if (value instanceof Integer) {
            return toParameterValue((Integer) value);
        } else if (value instanceof Boolean) {
            return toParameterValue((Boolean) value);
        } else if (value instanceof Float) {
            return toParameterValue((Float) value);
        } else if (value instanceof JQuery.JavaScriptInlineFunction) {
            return toParameterValue((JQuery.JavaScriptInlineFunction) value);
        } else if (value instanceof Duration) {
            return String.valueOf(((Duration) value).getMilliseconds());
        } else if (value instanceof Attr) {
            return value.toString();
        } else if (value instanceof JsonNode) {
            return Json.stringify(value);
        }

        return value != null ? "'" + String.valueOf(value) + "'" : Attr.nullValue();
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final JQuery.JavaScriptInlineFunction value) {
        return value != null ? value.build() : Attr.nullValue();
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final Long value) {
        return value != null ? Long.toString(value) : Attr.nullValue();
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final Integer value) {
        return value != null ? Integer.toString(value) : Attr.nullValue();
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final Float value) {
        return value != null ? Float.toString(value) : Attr.nullValue();
    }

    /**
     * transform given value to a javascript parameter value
     *
     * @param value The value to transform
     * @return value as string
     */
    public static CharSequence toParameterValue(final Boolean value) {
        return value != null ? Boolean.toString(value) : Attr.nullValue();
    }
}
