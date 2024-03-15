package cn.yuyao.leetcode.day0311.Trie;

public class TrieNode {

    public boolean isWord;

    public TrieNode[] children;

    public TrieNode() {
        isWord = false;
        children = new TrieNode[26];
    }
}
