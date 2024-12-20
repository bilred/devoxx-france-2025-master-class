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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * This set of exercises covers the collect() terminal operation with the basic collectors.
 * The collectors used in this class can be built from the factory methods of the Collectors
 * factory class. This collectors are the simplest ones: they do not take another, so-called
 * downstream collector as a parameter.
 */
public class N_SimpleCollectors {

    /**
     * Count the number of elements on that list.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector01() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        long count = input.stream().collect(Collectors.counting());

        assertThat(count).isEqualTo(10L);
    }

    /**
     * Compute the max element of that list, in the alphabetical order.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector02() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        String max = input.stream().collect(Collectors.maxBy(Comparator.naturalOrder())).get();

        assertThat(max).isEqualTo("two");
    }

    /**
     * Compute the min length of the elements of that list.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector03() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        //int minLength = input.stream().collect(Collectors.minBy(Comparator.comparingInt(String::length))).get().length();
        //int minLength = input.stream().collect(Collectors.mapping(String::length, Collectors.minBy(Comparator.naturalOrder()))).orElse(0);
        int minLength = input.stream().map(String::length).min(Comparator.naturalOrder()).orElse(0);

        assertThat(minLength).isEqualTo(3);
    }

    /**
     * Compute the average length of the elements of that list.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector04() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        double averageLength = input.stream().collect(Collectors.averagingDouble(String::length));

        assertThat(averageLength).isEqualTo(3.9d);
    }

    /**
     * Compute the sum of the elements of that list.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector05() {

        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int sum = input.stream().collect(Collectors.summingInt( Integer::intValue ));
        //int sum = input.stream().mapToInt(Integer::intValue).sum();

        assertThat(sum).isEqualTo(45);
    }

    /**
     * Concatenate the elements of that list in a String, separated by a space.
     * Try to use a collector from the Collectors factory class.
     */
    @Test
    public void n_simpleCollector06() {

        List<String> input = Arrays.asList(
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        String result = input.stream().collect(Collectors.joining(" "));
        //String result = String.join(" ", input);

        assertThat(result).isEqualTo("one two three four five six seven eight nine ten");
    }
}
