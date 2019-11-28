package pl.edu.amu.advjava;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    ZADANIE: zmodyfikuj kod, stosując programowanie funkcyjne tam, gdzie to możliwe
     */
final class DataStructuresExercises {

    private Map<String, Set<String>> wordsIndex = new HashMap<>();

    void indexWords(List<String> words, String fileName) {
        Objects.requireNonNull(words);
        Objects.requireNonNull(fileName);
        words.forEach(word -> {
/*            if (!wordsIndex.containsKey(word)) {
                wordsIndex.put(word, new HashSet<>());
            }
            wordsIndex.get(word).add(fileName);*/
            wordsIndex.computeIfAbsent(word, (key) -> Collections.emptySet());
            wordsIndex.computeIfPresent(word, (key, val) -> Stream.concat(val.stream(), Stream.of(fileName)).collect(Collectors.toSet()));
        });
    }

    Set<String> getMatchingFiles(String word) {
        return Stream.of(word).map(wordsIndex::get).filter(Objects::nonNull).findAny().orElse(Collections.emptySet());
    }

    void indexAll(Map<String, Set<String>> otherIndex) {
        otherIndex.forEach((word, v) -> {
            wordsIndex.computeIfAbsent(word, (key) -> Collections.emptySet());
            wordsIndex.get(word).addAll(v);
        });
    }

    void fileDeleted(String fileName) {
        Set<Map.Entry<String, Set<String>>> mapEntries = wordsIndex.entrySet();
        Iterator<Map.Entry<String, Set<String>>> entriesIterator = mapEntries.iterator();
        while (entriesIterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = entriesIterator.next();

            entry.getValue().remove(fileName);
            if (entry.getValue().isEmpty()) {
                entriesIterator.remove();
            }
        }

    }

}
