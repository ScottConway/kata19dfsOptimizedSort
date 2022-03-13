package kata19dfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordNodeTest {

    private static final String WORD = "SomeString";
    private static final String CHILD1 = "oldestChild";
    private static final String CHILD2 = "secondChild";

    private static final int FIRST_LEVEL = 1;
    private static final int SECOND_LEVEL = 2;

    private WordNode parentNode;

    @BeforeEach
    public void createParentNode() {
        parentNode = new WordNode(WORD);
    }

    @Test
    @DisplayName("Validate initial state of a WordNode.")
    public void initialState() {
        assertEquals(WORD, parentNode.getWord());
        assertEquals(FIRST_LEVEL, parentNode.getDepth());
        assertEquals(1, parentNode.getLineage().size());
        assertTrue(parentNode.getLineage().contains(WORD));
        assertTrue(parentNode.getChildren().isEmpty());
    }

    @Test
    @DisplayName("Validate values of children added.")
    public void addChildren() {
        parentNode.addChild(CHILD1);
        parentNode.addChild(CHILD2);

        List<WordNode> children = parentNode.getChildren();
        assertEquals(2, children.size());

        WordNode firstChild = children.get(0);
        assertEquals(CHILD1, firstChild.getWord());
        Collection<String> lineage = firstChild.getLineage();
        assertEquals(2, lineage.size());
        assertTrue(lineage.contains(parentNode.getWord()));
        assertTrue(lineage.contains(CHILD1));
        assertEquals(2, firstChild.getDepth());
        assertTrue(firstChild.getChildren().isEmpty());

        WordNode secondChild = children.get(1);
        assertEquals(CHILD2, secondChild.getWord());
        lineage = secondChild.getLineage();
        assertEquals(2, lineage.size());
        assertTrue(lineage.contains(parentNode.getWord()));
        assertTrue(lineage.contains(CHILD2));
        assertEquals(2, secondChild.getDepth());
        assertTrue(secondChild.getChildren().isEmpty());
    }

    @Test
    @DisplayName("You cannot add a word that has been used by the ancestors.")
    public void addAncestorAsChild() {
        parentNode.addChild(CHILD1);
        parentNode.addChild(WORD);

        List<WordNode> children = parentNode.getChildren();
        assertEquals(1, children.size());
        assertEquals(CHILD1, children.get(0).getWord());
    }

    @Test
    @DisplayName("You cannot add the same word twice.")
    public void addSameChild() {
        parentNode.addChild(CHILD2);
        parentNode.addChild(CHILD2);

        List<WordNode> children = parentNode.getChildren();
        assertEquals(1, children.size());
        assertEquals(CHILD2, children.get(0).getWord());
    }

    @Test
    @DisplayName("Flatten tree into a List of List of Strings.")
    public void flattenTree() {
        List<String> firstList = Arrays.asList(WORD, CHILD1, CHILD2);
        List<String> secondList = Arrays.asList(WORD, CHILD2);

        parentNode.addChild(CHILD1);
        parentNode.addChild(CHILD2);

        WordNode child1Node = parentNode.getChildren().stream()
                .filter(node -> node.getWord().equals(CHILD1))
                .findFirst()
                .get();

        child1Node.addChild(CHILD2);

        List<List<String>> allSolutions = parentNode.flattenTree();
        assertTrue(allSolutions.contains(firstList));
        assertTrue(allSolutions.contains(secondList));
    }

    @Test
    @DisplayName("Find shortest depth that the target word is found.")
    public void findDepthForWord() {
        parentNode.addChild(CHILD1);
        parentNode.addChild(CHILD2);

        WordNode child1Node = parentNode.getChildren().stream()
                .filter(node -> node.getWord().equals(CHILD1))
                .findFirst()
                .get();

        child1Node.addChild(CHILD2);

        assertEquals(2, parentNode.findDepthForWord(CHILD2));
    }

    //@Disabled("Need to determine depth at which an answer is found first.")
    @Test
    @DisplayName("Prune tree to depth two.")
    public void pruneTree() {
        List<String> firstList = Arrays.asList(WORD, CHILD1, CHILD2);
        List<String> secondList = Arrays.asList(WORD, CHILD2);

        parentNode.addChild(CHILD1);
        parentNode.addChild(CHILD2);

        WordNode child1Node = parentNode.getChildren().stream()
                .filter(node -> node.getWord().equals(CHILD1))
                .findFirst()
                .get();

        child1Node.addChild(CHILD2);

        parentNode.pruneTree(2, CHILD2);
        List<List<String>> allSolutions = parentNode.flattenTree();
        assertEquals(1, allSolutions.size());
        assertTrue(allSolutions.contains(secondList));
    }
}
