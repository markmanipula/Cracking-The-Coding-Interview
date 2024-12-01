package com.prep.interview.trees;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Map<Character, TrieNode> children;
    boolean endOfWord;

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
    }

    public TrieNode(Map<Character, TrieNode> children, boolean endOfWord) {
        this.children = children;
        this.endOfWord = endOfWord;
    }

}
