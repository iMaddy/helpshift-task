package com.helpshift.tries;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gitanjali on 17/03/17.
 */
public class Tries
{
    TrieNode root;

    public Tries()
    {
        root = new TrieNode(' '); // root node
        root.setWord("");
    }

    /*
        insert new word in tries if word is not present
     */
    public void insert(String word)
    {

        if(word == null || isWordPresent(word))
            return;

        word = word.toLowerCase();
        TrieNode current = root;
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.getChild(ch);
            if (child != null)
                current = child;
            else
            {
                TrieNode newChild = new TrieNode(ch);
                newChild.setParent(current);
                newChild.setWord(current.word+ch); // set word to prefix at current node
                current.childrens.put(String.valueOf(ch),newChild);
                current = current.getChild(ch);
            }
        }
        current.setEnd();

    }

    /*
        checks if word is present in tries
     */
    private boolean isWordPresent(String word)
    {
        if(word == null)
            return false;

        word = word.toLowerCase();
        TrieNode current = root;
        for (char ch : word.toCharArray() )
        {
            if (current.getChild(ch) == null)
                return false;
            else
                current = current.getChild(ch);
        }

        if (current.isEnd() == true)
            return true;
        return false;
    }

    /*
        search for words with prefix and returns all words with matching prefix
        or empty list of no matching word found
     */
    public List<String> search(String prefix)
    {
        if(prefix == null)
            return new LinkedList<String>();

        prefix = prefix.toLowerCase();

        TrieNode current = root;

        for (char ch : prefix.toCharArray() )
        {
            if (current.getChild(ch) == null)
            {
                return new LinkedList<String>(); // return empty list if prefix don't match
            }
            else
            {
                current = current.getChild(ch);
            }
        }
        // once we matched prefix, list all subsequent possible words
        return getSubsequentWords(current);
    }

    /*
        method returns current and all subsequent words formed down the current tries node
     */
    private List<String> getSubsequentWords(TrieNode node)
    {
        List<String> list = new LinkedList<String>();

        if (node.isEnd()) // word is complete, add it to list
        {
            list.add(node.getWord());
        }

        Set<String> keys = node.childrens.keySet();
        for (String key:keys)
        {
            TrieNode child = node.childrens.get(key);
            list.addAll(getSubsequentWords(child));
        }

        return list;
    }

//    public static void main(String[] args)
//    {
//        Tries tt = new Tries();
//        tt.insert("How are you");
//        tt.insert("How");
//        tt.insert("Hmm Iam good");
//        tt.insert("I am best");
//        tt.insert("I am worst");
//
//        System.out.println(tt.search("How"));
//    }
}
