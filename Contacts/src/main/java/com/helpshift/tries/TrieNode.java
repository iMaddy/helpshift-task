package com.helpshift.tries;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by gitanjali on 17/03/17.
 */
public class TrieNode
{
    char ch;
    Map<String, TrieNode> childrens; // pointers to child nodes
    TrieNode parent;
    String word;
    boolean end; //if it is leaf node of tries - i.e. word is complete here

    public TrieNode(char c)
    {
        ch = c;
        childrens = new Hashtable<String, TrieNode>();
        parent = null;
        end = false;
        word = "";
    }

    public void setEnd()
    {
        end = true;
    }

    public void setParent(TrieNode p)
    {
        parent = p;
    }

    public TrieNode getChild(char ch)
    {
        return childrens.get(String.valueOf(ch));
    }

    public boolean isEnd()
    {
        return end;
    }

    public void setWord(String str)
    {
        word = str;
    }

    public String getWord()
    {
        return word;
    }
}
