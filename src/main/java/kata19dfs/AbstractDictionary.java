package kata19dfs;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractDictionary {
    protected static Set<String> WORD_SET;

    public List<String> retrieveWordsOfLength(int length) {
        List<String> subList = getWordSet().stream()
                .filter(word -> word.length() == length)
                .collect(Collectors.toList());
        return subList;
    }

    protected Set<String> getWordSet() {
        return WORD_SET;
    }
}
