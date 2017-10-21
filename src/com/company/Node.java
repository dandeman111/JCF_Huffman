package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Node {
    public final Character character;
    public final int frequency;
    public final Node rightNode;
    public final Node leftNode;

    public Node(Map.Entry<Character, Integer> pair) {
        this.character = pair.getKey();
        this.frequency = pair.getValue();
        rightNode = null;
        leftNode = null;
    }

    public Node(Node rightNode, Node leftNode) {
        this.rightNode = rightNode;
        this.leftNode = leftNode;
        character = null;
        frequency = rightNode.frequency + leftNode.frequency;
    }

    public HashMap< String ,Character> toMap() {
        HashMap< String ,Character> hashMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        toMap(hashMap,stringBuilder);
        return hashMap;
    }

    private void toMap(HashMap<String, Character> hashMap, StringBuilder stringBuilder) {
        if (this.character == null) {
            StringBuilder stringBuilderLeft = new StringBuilder(stringBuilder);
            stringBuilderLeft.append('0');
            StringBuilder stringBuilderRight = new StringBuilder(stringBuilder);
            stringBuilderRight.append('1');
            this.leftNode.toMap(hashMap, stringBuilderLeft);

            this.rightNode.toMap(hashMap, stringBuilderRight);
        } else {
            hashMap.put( stringBuilder.toString(), this.character);

        }


    }

}
