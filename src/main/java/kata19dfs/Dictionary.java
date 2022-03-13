package kata19dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary extends AbstractDictionary {
    private final String WORD_FILE = "/wordlist.txt";

    public Dictionary() {
        if (WORD_SET == null) {
            Set<String> wordSet;
            try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getResource(WORD_FILE).toURI()), Charset.forName("UTF-8"))) {
                wordSet = br.lines()
                        .map(line -> line.toLowerCase())
                        .filter(line -> line.matches("[a-z]+"))
                        .collect(Collectors.toSet());

                WORD_SET = Collections.unmodifiableSet(wordSet);
            } catch (URISyntaxException | IOException e) {
                System.out.println("unable to locate file: " + WORD_FILE);
                e.printStackTrace();
            }
        }
    }

}
