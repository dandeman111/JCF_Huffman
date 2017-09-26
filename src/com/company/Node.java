package com.company;

import javafx.util.Pair;

import java.util.Map;

public class Node {
    public String character;
    public int frequency;
    public Node rightnode;
    public Node leftNode;

    public Node(Map.Entry<String,Integer> pair) {
        this.character = pair.getKey();
        this.frequency = pair.getValue();
        rightnode = null;
        leftNode = null;
    }

    public Node(Node rightnode, Node leftNode) {
        this.rightnode = rightnode;
        this.leftNode = leftNode;
        character = null;
        frequency = rightnode.frequency+ leftNode.frequency;
    }
}
