package de.agilecoders.wicket.jquery.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * helper class to handle creation/transformation/filtering of collections.
 *
 * This class is used to wrap guava. Future releases of bootstrap won't depend
 * on guava anymore.
 *
 * @author miha
 */
public final class Generics2 {

    /**
     * creates a new {@link java.util.ArrayList} from given array of elements.
     *
     * @param elements the elements to add to new {@link java.util.ArrayList}
     * @param <T>      the type of all elements inside the list
     * @return new {@link java.util.ArrayList} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements.
     *
     * @param elements the elements that the list should contain, in order
     * @return a new {@code ArrayList} containing those elements
     */
    public static <T> ArrayList<T> newArrayList(Iterator<? extends T> elements) {
        ArrayList<T> list = new ArrayList();
        elements.forEachRemaining(element -> list.add(element));
        return list;
    }

    /**
     * creates a new {@link java.util.ArrayList} from given array of elements.
     *
     * @param elements the elements to add to new {@link java.util.ArrayList}
     * @param <T>      the type of all elements inside the list
     * @return new {@link java.util.ArrayList} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> ArrayList<T> newArrayList(Iterable<? extends T> elements) {
        ArrayList<T> list = new ArrayList();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * creates a new {@link java.util.LinkedHashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link java.util.LinkedHashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link java.util.LinkedHashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newLinkedHashSet(Iterable<? extends T> elements) {
        LinkedHashSet<T> set = new LinkedHashSet();
        for (T element : elements) {
            set.add(element);
        }
        return set;
    }

    /**
     * creates a new {@link java.util.HashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link java.util.HashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link java.util.HashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newHashSet(T... elements) {
        HashSet<T> set = new HashSet();
        for (T element : elements) {
            set.add(element);
        }
        return set;
    }

    /**
     * joins all given elements with a special separator
     *
     * @param elements elements to join
     * @param separator separator to use
     * @return elements as string
     */
    public static String join(final Iterable<?> elements, final char separator) {
        final StringJoiner joiner = new StringJoiner("" + separator);
        joiner.setEmptyValue("");
        for (Object element : elements)
        {
            joiner.add(element.toString());
        }
        return joiner.toString();
    }

    /**
     * transform an array of elements into a list of elements
     *
     * @param elements the elements to add to list
     * @param <T>      the type of elements
     * @return new list of elements
     */
    public static <T> List<T> transform(final T[] elements) {
        return newArrayList(elements);
    }

    /**
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> List<R> transform(List<P> elements, Function<P, R> transformer) {
        return elements.stream().map(transformer).collect(Collectors.toList());
    }

    /**
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> Set<R> transform(Set<P> elements, Function<P, R> transformer) {
        return elements.stream().map(transformer).collect(Collectors.toSet());
    }

    /**
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> List<R> transform(P[] elements, Function<P, R> transformer) {
        return Arrays.stream(elements).map(transformer).collect(Collectors.toList());
    }

    /**
     * Returns the elements of {@code unfiltered} that satisfy given {@code filter}. The
     * resulting iterable's iterator does not support {@code remove()}.
     */
    public static <T> List<T> filter(final Iterable<T> unfiltered, final Predicate<T> filter) {
        return newArrayList(unfiltered).stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * splits a {@link CharSequence} by given separator.
     *
     * @param value the value to split
     * @param separator the separator to use to split value
     * @return list of values
     */
    public static List<String> split(final CharSequence value, final String separator) {
        final StringTokenizer tokenizer = new StringTokenizer(value.toString(), separator);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens())
        {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    /**
     * private constructor to prevent instantiation of util class.
     */
    private Generics2() {
        throw new UnsupportedOperationException();
    }
}
