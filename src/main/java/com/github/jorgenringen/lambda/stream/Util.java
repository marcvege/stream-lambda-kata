package com.github.jorgenringen.lambda.stream;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Util {

    public static List<String> mapToUppercase(List<String> input) {
        return input.stream().
                map(String::toUpperCase).
                collect(toList());
    }

    public static List<String> removeElementsWithMoreThanThreeCharacters(List<String> input) {
        return input.stream().
                filter(s-> !(s.length() > 3)).
                collect(toList());
    }

    public static List<String> sortStrings(List<String> input) {
        return input.stream().
                sorted().
                collect(toList());
    }

    public static List<Integer> sortIntegers(List<String> input) {
        return input.stream().
                map(Integer::valueOf).
                sorted().
                collect(toList());
    }

    public static List<Integer> sortIntegersDescending(List<String> input) {
        return input.stream().
                map(Integer::valueOf).
                sorted(Comparator.<Integer>reverseOrder()).
                collect(toList());
    }

    public static Integer sum(List<Integer> numbers) {
        return numbers.
                stream().
                mapToInt(Integer::intValue).
                sum();
    }

    public static List<String> flattenToSingleCollection(List<List<String>> input) {
        return input.
                stream().
                flatMap(l -> l.stream()).
                collect(toList());
    }

    public static String separateNamesByComma(List<Person> input) {
        return input.stream().
                map(p->p.getName()).
                collect(Collectors.joining(", ","Names: ","." ));
    }

    public static String findNameOfOldestPerson(List<Person> input) {
        return input.stream().
                max(Comparator.comparing(Person::getAge)).
                get().getName();
    }

    public static List<String> filterPeopleLessThan18YearsOld(List<Person> input) {
        return input.stream().
                filter(p->p.getAge()<18).
                map(p->p.getName()).
                collect(toList());
    }

    public static IntSummaryStatistics getSummaryStatisticsForAge(List<Person> input) {
        return input.stream().
                collect(Collectors.summarizingInt(p->p.getAge()));
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> input) {
        return input.stream().
                collect(Collectors.groupingBy(p-> p.getAge()>18));

    }

    public static Map<String, List<Person>> partitionByNationality(List<Person> input) {
        return input.stream().
                collect(Collectors.groupingBy(p->p.getCountry()));

    }
}
