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

package org.paumard.devoxxfr2019.A_lambdas.c_collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This set of exercises covers the default method added to the Collection interface.
 */
public class H_Collections {

    /**
     * Remove the words that have odd lengths from the list.
     */
    @Test
    public void h_collection01() {

        List<String> list = new ArrayList<>(Arrays.asList(
                "alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        // java.util.ConcurrentModificationException
        // for (String s : list) {
        //    if(s.length() % 2 != 0) list.remove(s);
        // }

        //List<String> toRemove = list.stream().filter(s -> s.length() % 2 !=0 ).collect(Collectors.toList());
        //list.removeAll(toRemove);

        // method is designed to be used safely in a single-threaded context without causing ConcurrentModificationException
        list.removeIf( s -> s.length() % 2 !=0 );

        assertThat(list).hasSize(2);
        assertThat(list).contains("alfa", "echo");
    }
}
