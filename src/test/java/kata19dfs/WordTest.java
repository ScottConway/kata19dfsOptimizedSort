package kata19dfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {

    @Test
    @DisplayName("inialize word")
    public void newWord() {
        Word word = new Word("ball");
        assertEquals("ball", word.getWord());
    }

    @ParameterizedTest
    @CsvSource({"ball,ball,true", "Ball,BALL,true", "ball,bass,false", "ball,balls,false"})
    @DisplayName("sameWord of {0} and {1} should be {2}")
    public void sameWord(String firstWord, String secondWord, String expectedMatchString) {
        Word word1 = new Word(firstWord);
        Word word2 = new Word(secondWord);
        Boolean expectedMatch = Boolean.parseBoolean(expectedMatchString);
        assertEquals(expectedMatch, word1.sameWord(word2));
        assertEquals(expectedMatch, word2.sameWord(word1));
    }

    @ParameterizedTest
    @CsvSource({"ball,ball,4", "Ball,BALL,4", "ball,bass,2", "ball,balls,-1", "ball,llab,0"})
    @DisplayName("exactLetterMatches of {0} and {1} should be {2}")
    public void exactLetterMatches(String firstWord, String secondWord, int expectedMatches) {
        Word word1 = new Word(firstWord);
        Word word2 = new Word(secondWord);
        assertEquals(expectedMatches, word1.exactLetterMatches(word2));
        assertEquals(expectedMatches, word2.exactLetterMatches(word1));
        assertEquals(expectedMatches, word1.exactLetterMatches(secondWord));
        assertEquals(expectedMatches, word2.exactLetterMatches(firstWord));
    }
 }
