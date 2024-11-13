/*
 * Copyright (C) 2019 José Paumard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.paumard.devoxxfr2019.B_streams;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * This set of exercises covers simple simpleStream pipelines,
 * including intermediate operations and basic collectors.
 */
public class K_SimpleStreams {

    /**
     * Given a list of words, create an output list that contains the elements
     * converted to upper case.
     * Use the collect(Collectors.toList()) pattern to create the output list.
     */
    @Test
    public void k_simpleStream01() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        List<String> result = input.stream().map(String::toUpperCase).collect(Collectors.toList());

        assertThat(result)
                .containsExactly("ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN");
    }

    /**
     * Given a list of words, create an output list that contains
     * only the odd-length words, converted to upper case.
     * Use the collect(Collectors.toList()) pattern to create the output list.
     */
    @Test
    public void k_simpleStream02() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        List<String> result = input.stream().filter(s -> s.length() % 2 != 0).map(String::toUpperCase).collect(Collectors.toList());

        assertThat(result)
                .containsExactly("ONE", "TWO", "THREE", "SIX", "SEVEN", "EIGHT", "TEN");
    }

    /**
     * Given a list of words, create an output list that contains
     * only words from 3 to 8.
     * Use the collect(Collectors.toList()) pattern to create the output list.
     */
    @Test
    public void k_simpleStream03() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

//        List<String> result = input.stream()
//                .filter(s -> s.length() >= 3 && s.length() <= 8)
//                .collect(Collectors.toList());

        //  issue with the code is that it's only selecting one word for each length
//        List<String> result = IntStream.rangeClosed(3, 8)
//                .mapToObj(len -> input.stream()
//                        .filter(s -> s.length() == len)
//                        .findFirst()
//                        .orElse(null))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());

        List<String> result = IntStream.range(2, 8)
                .filter(i -> {
                    String word = input.get(i);
                    return word.length() >= 3 && word.length() <= 8;
                })
                .mapToObj(input::get)
                .collect(Collectors.toList());

        assertThat(result).containsExactly("three", "four", "five", "six", "seven", "eight");
    }

    /**
     * Given a list of words, create an output list that contains
     * only the first letters of all the words, without any double.
     * Use the collect(Collectors.toList()) pattern to create the output list.
     */
    @Test
    public void k_simpleStream04() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        List<String> result = input.stream()
                .map(s -> s.charAt(0))
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.toList());

        assertThat(result)
                .containsExactly("o", "t", "f", "s", "e", "n");
    }

    /**
     * Given a list of words, create an output list that contains
     * only the first letters of all the words, without any double, sorted in the alphabetical order.
     * Use the collect(Collectors.toList()) pattern to create the output list.
     */
    @Test
    public void k_simpleStream05() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        List<String> result = input.stream()
                .map(s -> s.charAt(0))
                .distinct()
                .map(String::valueOf)
                .sorted() // same as Comparator.naturalOrder()
                .collect(Collectors.toList());

        assertThat(result)
                .containsExactly("e", "f", "n", "o", "s", "t");
    }

    /**
     * Given a list of words, count the number of different lengths of those words.
     * Try not to create any list.
     */
    @Test
    public void k_simpleStream06() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

//        long result = input.stream()
//                .flatMapToInt(String::codePoints)
//                .mapToObj(c -> (char) c)
//                .count();

        long result = input.stream()
                           .mapToInt(String::length)
                           .distinct()
                           .count();

        assertThat(result).isEqualTo(3L);
    }

    /**
     * Create the following list: {"+", "+", "+", "+", "+"}.
     * Try to use a factory method from Stream.
     */
    @Test
    public void k_simpleStream07() {

        //List<String> result = IntStream.range(0, 5).mapToObj(n -> "+").collect(Collectors.toList());
        List<String> result = Stream.generate(() -> "+").limit(5).collect(Collectors.toList());

        assertThat(result).containsExactly("+", "+", "+", "+", "+");
    }

    /**
     * Create the following list: {"+++", "++++", "+++++", "+++++++", "++++++++"}.
     * Try to use a factory method from Stream.
     */
    @Test
    public void k_simpleStream08() {

        List<String> result = Stream.iterate(3, i -> i + 1)
                .limit(5)
                .map("+"::repeat)
                .collect(Collectors.toList());

        assertThat(result).containsExactly("+++", "++++", "+++++", "++++++", "+++++++");
    }

    /**
     * Create the following list: {1, 2, 3, 4, 5}.
     * Try to avoid the boxing of integers until the list is created.
     */
    @Test
    @Ignore
    public void k_simpleStream09() {

        List<Integer> result = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());

        assertThat(result).containsExactly(1, 2, 3, 4, 5);
    }

    /**
     * Create a function that take a String as a parameter and returns
     * the list of the letters of that string, in lower case, ordered in alphabetical order
     * and with no doubles.
     */
    @Test
    @Ignore
    public void k_simpleStream10() {

        Function<String, List<String>> wordToLetters = null; // TODO

        assertThat(wordToLetters.apply("Hello world")).containsExactly("d", "e", "h", "l", "o", "r", "w");
    }
}
