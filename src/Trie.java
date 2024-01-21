import java.lang.reflect.Array;
import java.util.ArrayList;

public class Trie {

    // Alphabet size (# of symbols)
    int ALPHABET_SIZE;

    int wordsCount; // How many full words are in the list

    Trie(int size) {
        ALPHABET_SIZE = size;
        root = new TrieNode();
        wordsCount = 0;
    }

    private class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node, 
    // just marks leaf node
    public void insert(String key) {
        TrieNode current = root;

        for (int level = 0; level < key.length(); level++) {
            // In order for the list to start from 0, not from 97, ASCII 'a' value is subtracted
            int index = key.charAt(level) - 'a';
            if (current.children[index] == null)
                current.children[index] = new TrieNode();

            current = current.children[index];
        }

        // Mark last node as leaf
        current.isEndOfWord = true;
        wordsCount++;
    }

    private boolean isEmpty(TrieNode root)
    {
        for (int i = 0; i < ALPHABET_SIZE; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }

    public TrieNode remove (String key) {
        return remove(root, key, 0);
    }

    private TrieNode remove(TrieNode root, String key, int depth)
    {
        // If tree is empty
        if (root == null)
            return null;

        // If last character of key is being processed
        if (depth == key.length()) {

            // This node is no more end of word after
            // removal of given key
            if (root.isEndOfWord) {
                root.isEndOfWord = false;
                wordsCount--;
            }

            // If given is not prefix of any other word
            if (isEmpty(root)) {
                root = null;
            }

            return root;
        }

        // If not last character, recur for the child
        // obtained using ASCII value
        int index = key.charAt(depth) - 'a';
        root.children[index] =
                remove(root.children[index], key, depth + 1);

        // If root does not have any child (its only child got
        // deleted), and it is not end of another word.
        if (isEmpty(root) && !root.isEndOfWord){
            root = null;
        }

        return root;
    }

    public int getCount() {
        return wordsCount;
    }

    // Returns true if key presents in trie, else false
    public boolean search(String key) {
        TrieNode current = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';

            if (current.children[index] == null)
                return false;

            current = current.children[index];
        }

        return (current.isEndOfWord);
    }

    // -1 = Value does not exist
    // 0 = Part of the word
    // 1 = Full word
    public int multiSearch(String key) {
        TrieNode current = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';

            if (current.children[index] == null) {
                return -1; // Word starting with this prefix doesn't exist
            }

            current = current.children[index];
        }

        if (current.isEndOfWord) {
            return 1;
        }
        else {
            return 0;
        }
    }
}