package string;

import java.util.HashMap;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        if (s==null) return;
        TrieNode curNode = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (curNode.getNode(c) == null) {
                curNode.addNode(c);
            }
            curNode = curNode.getNode(c);
        }
        curNode.makeEndOfWord();
    }

    public boolean exists(String s) {
        if (s==null) throw new IllegalArgumentException("input attribute is null");
        TrieNode currentNode = root;
        for (int i = 0; i < s.length(); i++) {
            currentNode = currentNode.getNode(s.charAt(i));
            if (currentNode == null) {
                return false;
            }
        }
        return currentNode.isEndOfWord();
    }

    private class TrieNode {
        private HashMap<Object, TrieNode> childNodes;
        private boolean isEndOfWord;

        public TrieNode() {
            childNodes = new HashMap<>();
            isEndOfWord = false;
        }

        public TrieNode getNode(Object letter) {
            return childNodes.getOrDefault(letter, null);
        }

        public void makeEndOfWord() {
            this.isEndOfWord = true;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void addNode(Object letter) {
            childNodes.put(letter, new TrieNode());
        }
    }

}