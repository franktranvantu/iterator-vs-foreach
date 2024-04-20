package com.franktranvantu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class IteratorVsForeachTest {
    private static Stream<Arguments> listValues() {
        return Stream.of(
                Arguments.of(
                        List.of("Value 1", "Value 2", "Unknown"),
                        List.of("Value 1", "Value 2")
                )
        );
    }

    @Test
    public void givenEmptyCollection_whenUsingForEach_thenNoElementsAreIterated() {
        List<String> names = Collections.emptyList();
        StringBuilder stringBuilder = new StringBuilder();
        names.forEach(stringBuilder::append);
        assertEquals("", stringBuilder.toString());
    }

    @ParameterizedTest
    @MethodSource("listValues")
    public void givenCollectionWithElements_whenRemovingElementDuringForEachIteration_thenElementIsRemoved(List<String> input, List<String> expected) {
        List<String> mutableList = new ArrayList<>(input);
        // Separate collection for items to be removed
        List<String> toRemove = new ArrayList<>();

        // Using forEach to identify items to remove
        input.forEach(item -> {
            if (item.equals("Unknown")) {
                toRemove.add(item);
            }
        });

        // Removing the identified items from the original list
        mutableList.removeAll(toRemove);
        assertIterableEquals(expected, mutableList);
    }

    @ParameterizedTest
    @MethodSource("listValues")
    public void givenCollectionWithElements_whenRemovingElementDuringIteratorIteration_thenElementIsRemoved(List<String> input, List<String> expected) {
        List<String> mutableList = new ArrayList<>(input);
        Iterator<String> it = mutableList.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("Unknown")) {
                it.remove(); // Safely remove item
            }
        }
        assertIterableEquals(expected, mutableList);
    }
}
