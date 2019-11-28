package pl.edu.amu.advjava;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

final class FibonacciNumbersExercise {

    /*
         ZADANIE: dopasuj implementację klasy FibonacciSupplier tak, żeby generowała kolejne liczby ciągu
         Fibonacciego.
     */
    static int[] generateFibonacciNumbers(int n) {
        return IntStream.generate(new FibonacciSupplier()).limit(n).toArray();
    }

    /*
     *   ZADANIE: dopasuj implementację wyrażenia lambda tak, żeby generowało kolejne liczby ciągu Fibonacciego.
     */
    static int[] generateFibonacciNumbersWithLambda(int n) {
        final int[] tab = new int[]{0, 1};
        return IntStream.generate(() -> {
            int sum = tab[0] + tab[1];
            tab[0] = tab[1];
            tab[1] = sum;
            return tab[0];
        }).limit(n).toArray();
    }
}

final class FibonacciSupplier implements IntSupplier {

    private int a = 0, b = 1;

    @Override
    public int getAsInt() {
        int sum = a + b;
        a = b;
        b = sum;
        return a;
    }
}

