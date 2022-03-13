package kata19dfs;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    Dictionary dictionary = new Dictionary();

    @Test
    @DisplayName("Validate words of specified length are returned.")
    public void getWordList() {
        List<String> words = dictionary.retrieveWordsOfLength(6);
        assertFalse(words.isEmpty());
        words.forEach(word -> assertEquals(6, word.length(), "Expected words of only length 6 to be read but result also included: " + word));
    }

    @Test
    @DisplayName("Validate words only contain alphabetic characters.")
    public void wordsOnlyContainCharacters() {
        List<String> words = dictionary.retrieveWordsOfLength(4);
        assertFalse(words.isEmpty());
        String forbiddenWord = words.stream()
                .filter(word -> !StringUtils.isAlpha(word))
                .findFirst()
                .orElse(null);
        assertNull(forbiddenWord, "All words were supposed to contain only letters but the following word was found: " + forbiddenWord);
    }
}
