package kata19dfs;

import java.util.Locale;

public class Word {
    private final String word;

    public Word(String word) {
        this.word = word.toLowerCase(Locale.ROOT);
    }

    public String getWord() {
        return this.word;
    }

    public boolean sameWord(Word word2) {
        return this.word.equals(word2.getWord());
    }

    public static int letterMatches(String s1, String s2) {
        Word w1 = new Word(s1);
        return w1.exactLetterMatches(s2);
    }

    public int exactLetterMatches(String word2) {
        if (this.word.length() != word2.length()) {
            return -1;
        }
        String lcWord2 = word2.toLowerCase(Locale.ROOT);
        int numMatches = 0;
        for (int i = 0; i < this.word.length(); i++) {
            if (this.word.charAt(i) == lcWord2.charAt(i)) {
                numMatches++;
            }
        }
        return numMatches;
    }
    public int exactLetterMatches(Word word2) {
        return exactLetterMatches(word2.getWord());
    }
}
