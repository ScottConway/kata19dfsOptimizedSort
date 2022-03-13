package kata19dfs;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestDictionary extends AbstractDictionary {

    private Set<String> WORD_SET;

    public TestDictionary(List<String> wordList) {
        Set<String> wordSet;

        wordSet = wordList.stream()
                .map(line -> line.toLowerCase())
                .filter(line -> line.matches("[a-z]+"))
                .collect(Collectors.toSet());

        WORD_SET = Collections.unmodifiableSet(wordSet);
    }

    @Override
    protected Set<String> getWordSet() {
        return this.WORD_SET;
    }
}
