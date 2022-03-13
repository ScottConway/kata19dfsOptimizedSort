package kata19dfs;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class WordChainFinder {
    private AbstractDictionary dictionary;
    private final String startWord;
    private final String finishWord;
    private int maxDepth;

    ReentrantLock lock = new ReentrantLock();

    public WordChainFinder(String startWord, String finishWord) {
        this(startWord, finishWord, new Dictionary());
    }

    public WordChainFinder(String startWord, String finishWord, AbstractDictionary dictionary) {
        this.startWord = startWord.trim();
        this.finishWord = finishWord.trim();
        this.dictionary = dictionary;
        maxDepth = startWord.length() * 2;
    }

    public void changeDictionary(AbstractDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<List<String>> findShortestChains() {
        List<String> wordList = dictionary.retrieveWordsOfLength(startWord.length());

        WordNode parentNode = buildTree(new WordNode(startWord), wordList);

        parentNode.pruneTree(maxDepth, finishWord);

        return parentNode.flattenTree();
    }

    private WordNode buildTree(WordNode startNode, List<String> wordList) {

//        System.out.format("map: %d %s [%s]\n",
//                startNode.getDepth(), startNode.getWord(), Thread.currentThread().getName());

        if (startNode.getDepth() >= maxDepth) {
            return startNode;
        }

        List<String> unsortedChildren = WordDistance.findWordsWithGivenDistance(wordList, startNode.getWord(), 1);
        List<String> children = WordDistance.orderList(unsortedChildren, finishWord);

        if (children.contains(finishWord)) {
            startNode.addChild(finishWord);
            if (startNode.getDepth() + 1 < maxDepth) {
                updateMaxDepth(startNode.getDepth() + 1);
            }
            return startNode;
        }

        children.forEach(startNode::addChild);
        startNode.getChildren().forEach(childNode -> buildTree(childNode, wordList));
//        if (startNode.getDepth() == 1) {
//            startNode.getChildren().parallelStream().forEach(childNode -> buildTree(childNode, wordList));
//        } else {
//            startNode.getChildren().forEach(childNode -> buildTree(childNode, wordList));
//        }

        return startNode;
    }

    private void updateMaxDepth(int newDepth) {
        lock.lock();
        try {
            if (maxDepth > newDepth) {
                maxDepth = newDepth;
            }
        } finally {
            lock.unlock();
            System.out.println("New MaxDepth is: " + maxDepth);
        }
    }
}
