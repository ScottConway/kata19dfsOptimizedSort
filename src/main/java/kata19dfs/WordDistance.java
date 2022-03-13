package kata19dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordDistance {
    public static final int INFINITE = Integer.MAX_VALUE;

    public static int findDistance(String sourceWord, String destWord) {
        if (sourceWord.equals(destWord)) {
            return 0;
        }

        if (sourceWord == null || destWord == null) {
            return WordDistance.INFINITE;
        }

        if (sourceWord.length() != destWord.length()) {
            return WordDistance.INFINITE;
        }

        int distance = 0;
        for (int i = 0; i < sourceWord.length(); i++) {
            if (sourceWord.charAt(i) != destWord.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public static List<String> findWordsWithGivenDistance(List<String> words, String source, int distance) {
        if (words == null) {
            return Collections.emptyList();
        }

        return words.stream()
                .filter(word -> findDistance(source, word) == distance)
                .collect(Collectors.toList());
    }

    public static List<String> orderList(List<String> list, String desiredWord) {
        List<String> orderedList = new ArrayList<>(list);
        orderedList.sort((s1, s2) -> {
            if (Word.letterMatches(desiredWord,s1) == Word.letterMatches(desiredWord,s2)) {
                return s1.compareTo(s2);
            } else {
                return Word.letterMatches(desiredWord,s2) - Word.letterMatches(desiredWord,s1);
            }
        });
        return orderedList;
    }
}
