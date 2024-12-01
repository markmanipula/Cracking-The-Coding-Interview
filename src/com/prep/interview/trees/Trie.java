package com.prep.interview.trees;

public class Trie {

    TrieNode root;

    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!current.children.containsKey(currentChar)) {
                current.children.put(currentChar, new TrieNode());
            }
            current = current.children.get(currentChar);
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!current.children.containsKey(currentChar)) {
                return false;
            }
            current = current.children.get(currentChar);
        }
        return current.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!current.children.containsKey(currentChar)) {
                return false;
            }
            current = current.children.get(currentChar);
        }
        return true;
    }

}
