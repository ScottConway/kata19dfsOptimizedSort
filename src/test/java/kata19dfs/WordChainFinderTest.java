package kata19dfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WordChainFinderTest {
    private WordChainFinder finder;
    private AbstractDictionary testDictionary;

    @Test
    @DisplayName("Find the word chain between lead and gold.")
    public void leadToGold() {
        List<String> wordList = new ArrayList<>();
        wordList.add("mead");
        wordList.add("lead");
        wordList.add("head");
        wordList.add("gold");
        wordList.add("hold");
        wordList.add("mold");
        wordList.add("moan");
        wordList.add("loan");
        wordList.add("goad");
        wordList.add("goat");
        wordList.add("horn");
        wordList.add("load");
        wordList.add("told");


        testDictionary = new TestDictionary(wordList);
        finder = new WordChainFinder("lead", "gold", testDictionary);
        finder.changeDictionary(testDictionary);

        List<List<String>> wordChains = finder.findShortestChains();
        assertEquals(1, wordChains.size());

        List<String> wordChain = wordChains.get(0);
        assertEquals(4, wordChain.size());
        assertEquals(Arrays.asList("lead", "load", "goad", "gold"), wordChain);
    }
}
