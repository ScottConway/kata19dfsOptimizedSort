package kata19dfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordDistanceTest {

    private static final List<String> WORDS = Arrays.asList("aaa", "aab", "aac", "aba", "abc", "acc", "caa", "aaaa");

    @DisplayName("Calculate the distance between two words.")
    @ParameterizedTest(name = "The distance between {0} and {1} should be {2}")
    @CsvSource({"aaaaa,aaaab,1", "aaaaa,aaabb,2", "aaaaa,aabbb,3", "aaaaa,abbbb,4",
            "aaaaa,ccccc,5", "caaaa,aaaaa,1", "ccaaa,bbaaa,2", "cccaa,dddaa,3",
            "cccca,aaaaa,4", "xxxxxx,yyyyyy,6", "xxxxxx,xxxxxx,0"})
    public void findDistance(String sourceWord, String destWord, int expectedDistance) {
        assertEquals(expectedDistance, WordDistance.findDistance(sourceWord, destWord));
    }

    @DisplayName("For edge cases calculate the distance between two words.")
    @ParameterizedTest(name = "The distance between {0} and {1} should be {2}")
    @CsvSource({"null,aaaab,-1", "null,null,0", "null,aabbb,-1", "aaaaa,null,-1",
            "a,aa,-1", "aa,a,-1", "ccaaa,bbaaa,2", "cccaa,dddaa,3",
            "cccca,aaaaa,4", "xxxxxx,yyyyyy,6"})
    public void edgeCases(String sourceWord, String destWord, int expectedDistance) {
        assertEquals(convertDistance(expectedDistance), WordDistance.findDistance(convertToNull(sourceWord), convertToNull(destWord)));
    }

    @Test
    @DisplayName("Find words in a list that have a given distance.")
    public void findWordsWithGivenDistance() {
        List<String> wordsOfDistance1 = WordDistance.findWordsWithGivenDistance(WORDS, "aaa", 1);
        List<String> wordsOfDistance2 = WordDistance.findWordsWithGivenDistance(WORDS, "aaa", 2);
        List<String> wordsOfDistance3 = WordDistance.findWordsWithGivenDistance(WORDS, "aaa", 3);
        assertEquals(4, wordsOfDistance1.size());
        assertEquals(2, wordsOfDistance2.size());
        assertTrue(wordsOfDistance1.contains("aab"));
        assertTrue(wordsOfDistance1.contains("aac"));
        assertTrue(wordsOfDistance1.contains("aba"));
        assertTrue(wordsOfDistance1.contains("caa"));
        assertTrue(wordsOfDistance2.contains("abc"));
        assertTrue(wordsOfDistance2.contains("acc"));
        assertTrue(wordsOfDistance3.isEmpty());
    }

    @Test
    @DisplayName("Edge cases when finding words in a list that have a given distance.")
    public void findWordsWithGivenDistanceEdgeCases() {
        assertTrue(WordDistance.findWordsWithGivenDistance(null, null, -122).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(null, "aaa", -122).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(WORDS, null, -122).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(WORDS, "aaa", -122).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(null, null, 1).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(null, "aaa", 1).isEmpty());
        assertTrue(WordDistance.findWordsWithGivenDistance(WORDS, null, 1).isEmpty());
    }

    private String convertToNull(String word) {
        return word.equals("null") ? null : word;
    }

    private int convertDistance(int distance) {
        return distance < 0 ? WordDistance.INFINITE : distance;
    }
}
