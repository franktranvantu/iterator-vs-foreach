package com.franktranvantu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ListIterationTest {
    List<String> programmingLanguages = new ArrayList<>(List.of("Java", "Groovy", "C++"));
    List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));

    @Test
    public void givenStringList_whenAddElementWithListIterator_thenModifiedList() {
        ListIterator<String> listIterator = programmingLanguages.listIterator();
        while (listIterator.hasNext()) {
            String language = listIterator.next();
            if (language.equals("Groovy")) {
                listIterator.add("JavaScript");
            }
        }

        assertIterableEquals(Arrays.asList("Java", "Groovy", "JavaScript", "C++"), programmingLanguages);
    }

    @Test
    public void givenNumericalList_whenMultiplyElementWithListIterator_thenModifiedList() {
        ListIterator<Integer> listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            int num = listIterator.next();
            if (num == 2) {
                listIterator.add(num * 10);
            }
        }
        assertIterableEquals(Arrays.asList(1, 2, 20, 3), numbers);
    }

    @Test
    public void givenStringList_whenAddElementWithEnhancedForLoopAndCopy_thenModifiedList() {
        List<String> copyOfWords = new ArrayList<>(programmingLanguages);
        for (String word : copyOfWords) {
            programmingLanguages.add(word.toUpperCase()); // Modified: Convert to uppercase
        }
        assertIterableEquals(Arrays.asList("Java", "Groovy", "C++", "JAVA", "GROOVY", "C++"), programmingLanguages);
    }

    @Test
    public void givenNumericalList_whenMultiplyElementWithEnhancedForLoopAndCopy_thenModifiedList() {
        List<Integer> copyOfNumbers = new ArrayList<>(numbers);
        for (int num : copyOfNumbers) {
            numbers.add(num * 2);
        }
        assertIterableEquals(Arrays.asList(1, 2, 3, 2, 4, 6), numbers);
    }

    @Test
    public void givenStringList_whenConvertToUpperCaseWithJava8Stream_thenModifiedList() {
        programmingLanguages = programmingLanguages.stream().map(String::toUpperCase).collect(Collectors.toList());
        assertIterableEquals(Arrays.asList("JAVA", "GROOVY", "C++"), programmingLanguages);
    }

    @Test
    public void givenNumericalList_whenMultiplyByThreeWithJava8Stream_thenModifiedList() {
        numbers = numbers.stream().map(num -> num * 3).collect(Collectors.toList());
        assertIterableEquals(Arrays.asList(3, 6, 9), numbers);
    }
}
