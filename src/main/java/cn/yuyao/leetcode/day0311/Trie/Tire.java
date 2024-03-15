package cn.yuyao.leetcode.day0311.Trie;

public class Tire {

    private TrieNode root;

    public Tire() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.length() <= 0) return;
        TrieNode parent = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (parent.children[index] == null) {
                parent.children[index] = new TrieNode();
            }
            parent = parent.children[index];
        }
        parent.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = find(word);
        return current != null && current.isWord;
    }

    //是否以prefix为前缀
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private TrieNode find(String str) {
        TrieNode parent = root;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
           if ((parent = parent.children[index]) == null) {
               return null;
           }
        }
        return parent;
    }

}
