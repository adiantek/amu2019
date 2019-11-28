package pl.edu.amu.advjava;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class StreamExcercises {

    /*
                 ZADANIE: dla podanej kolekcji zwróć sumę opakowanych liczb.
             */
    static int sum(Collection<CustomNumber> numbers) {
        return numbers.stream().mapToInt(CustomNumber::getNumber).sum();
    }

    /*
         ZADANIE: dla podanej kolekcji zwróć tylko te nieujemne.
     */
    static List<CustomNumber> positiveNumbers(Collection<CustomNumber> numbers) {
        return numbers.stream().filter(CustomNumber::isPositive).collect(Collectors.toList());
    }

    /*
         ZADANIE: dla podanej kolekcji zwróć kolekcję zawierającą kwadraty opakowanych liczb.
     */
    static List<Integer> squares(Collection<CustomNumber> numbers) {
        return numbers.stream().map(CustomNumber::getNumber).map(x -> x * x).collect(Collectors.toList());
    }

    /*
         ZADANIE: dla podanej kolekcji zwróć sumę liczb ujemnych z kolekcji.
     */
    static int sumOfNegativeNumbers(Collection<CustomNumber> numbers) {
        return numbers.stream().filter(CustomNumber::isNegative).mapToInt(CustomNumber::getNumber).sum();
    }

    /*
         ZADANIE: dla podanej kolekcji iloczyn liczb z wszystkich kolekcji.
     */
    static long productOfNumbers(Collection<CustomNumberCollection> customNumberCollection) {
        return customNumberCollection.stream().flatMapToLong(n -> n.getCustomNumbers().stream().mapToLong(CustomNumber::getNumber)).reduce(1, (a, b) -> a * b);
    }

    /*
         ZADANIE: zwracana mapa powinna zawierać jako klucz kwadrat liczby z kolekcji, a jako wartość - liczby,
         które są pierwiastkami kwadratowymi tej liczby.
     */
    private static final BinaryOperator<Set<Integer>> mergeTwoSets = (a, b) -> Stream.concat(a.stream(), b.stream()).collect(Collectors.toSet());

    static Map<Integer, Set<Integer>> squareRoots(Collection<CustomNumber> numbers) {
        return numbers
                .stream()
                .mapToInt(CustomNumber::getNumber)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i * i,
                        Collections::singleton,
                        mergeTwoSets));
    }

    static final class CustomNumber {
        int number;

        CustomNumber(int number) {
            this.number = number;
        }

        boolean isPositive() {
            return number >= 0;
        }

        boolean isNegative() {
            return !isPositive();
        }

        int getNumber() {
            return number;
        }
    }

    static final class CustomNumberCollection {
        private Collection<CustomNumber> customNumbers;

        CustomNumberCollection(Collection<CustomNumber> customNumbers) {
            this.customNumbers = customNumbers;
        }

        Collection<CustomNumber> getCustomNumbers() {
            return customNumbers;
        }
    }
}
