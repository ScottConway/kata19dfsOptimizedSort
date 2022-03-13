package kata19dfs;


import java.util.*;

public class WordNode {
    public static final int NOT_FOUND = Integer.MAX_VALUE;

    private String word;
    private int depth;
    private Set<String> lineage = new HashSet<>();
    private List<WordNode> children = new ArrayList<>();

    public WordNode(String word) {
        this.word = word;
        this.depth = 1;
        lineage.add(word);
    }

    private WordNode(String childWord, Set<String> parentLinage, int parentDepth) {
        this.word = childWord;
        lineage.add(childWord);
        lineage.addAll(parentLinage);
        depth = parentDepth + 1;
    }

    public String getWord() {
        return word;
    }

    public int getDepth() {
        return depth;
    }

    public Collection<String> getLineage() {
        return Collections.unmodifiableCollection(lineage);
    }

    public List<WordNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(String childWord) {

        if (lineage.contains(childWord)) {
            return;
        }

        WordNode childNode = new WordNode(childWord, lineage, depth);

        if (!children.contains(childNode)) {
            children.add(childNode);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordNode)) return false;
        WordNode wordNode = (WordNode) o;
        return depth == wordNode.depth &&
                Objects.equals(word, wordNode.word) &&
                Objects.equals(lineage, wordNode.lineage) &&
                Objects.equals(children, wordNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, depth, lineage, children);
    }

    public List<List<String>> flattenTree() {
        List<List<String>> flattenedTree = new ArrayList<>();
        if (children.isEmpty()) {
            List<String> leafNode = new ArrayList<>();
            leafNode.add(word);
            flattenedTree.add(leafNode);
        } else {
            for (WordNode wordNode : children) {
                for (List<String> list : wordNode.flattenTree()) {
                    list.add(0, word);
                    flattenedTree.add(list);
                }
            }
        }

        return flattenedTree;
    }

    public void pruneTree(int maxDepth, String targetWord) {
        if (word.equals(targetWord)) {
            return;
        }
        if (depth >= maxDepth) {
            children.clear();
            return;
        }
        Iterator<WordNode> iterator = children.iterator();

        while (iterator.hasNext()) {
            WordNode node = iterator.next();
            node.pruneTree(maxDepth, targetWord);
            if (node.findDepthForWord(targetWord) > maxDepth) {
                iterator.remove();
            }
        }

    }

    public int findDepthForWord(String targetWord) {
        if (word.equals(targetWord)) {
            return depth;
        }

        if (children.isEmpty()) {
            return NOT_FOUND;
        }

        return children.stream()
                .mapToInt(wordNode -> wordNode.findDepthForWord(targetWord))
                .min()
                .getAsInt();
    }
}
