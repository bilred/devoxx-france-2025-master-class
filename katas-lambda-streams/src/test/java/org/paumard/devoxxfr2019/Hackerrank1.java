package org.paumard.devoxxfr2019;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Hackerrank1 {

    Predicate<Integer> devBy3 = p -> p % 3 == 0;
    Predicate<Integer> devBy5 = p -> p % 5 == 0;

    default String test() {
        return "";
    }

    static void main(String... args) {
        //Hackerrank1.test();
        Hackerrank1.fizzBuzz(15);
    }

    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void fizzBuzz(int n) {
        // Write your code here
        IntStream.rangeClosed(1, n).forEachOrdered( v -> {

                if( devBy3.and(devBy5).test(v) ) {
                    System.out.println("FizzBuzz");
                }
                else if( devBy3.and(devBy5.negate()).test(v) ) {
                    System.out.println("Fizz");
                }
                else if( devBy3.negate().and(devBy5).test(v) ) {
                    System.out.println("Buzz");
                }
                else {
                    System.out.println(v);
                }

        });
    }
}
