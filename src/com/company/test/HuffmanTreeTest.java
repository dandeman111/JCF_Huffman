package com.company.test;

import com.company.HuffmanTree;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeTest {
    HuffmanTree huffmanTestTree;
    HashMap hashMap1;
    String stringBit;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        char[] chars = new char[]{'t','e','s','t','e','n'};
         huffmanTestTree = new HuffmanTree(chars);
         hashMap1 = new HashMap<StringBuilder, Character>();
         hashMap1.put("00",'e');
         hashMap1.put("011",'s');
         hashMap1.put("1",'t');
         hashMap1.put("010",'n');

        stringBit = "100011100010";



    }

    @org.junit.jupiter.api.Test
    void getHufmancodes() {
        System.out.println(huffmanTestTree.getHufmancodes().toString());
        assertEquals('e',huffmanTestTree.getHufmancodes().get("00"));
        assertEquals('s',huffmanTestTree.getHufmancodes().get("011"));
        assertEquals('t',huffmanTestTree.getHufmancodes().get("1"));
        assertEquals('n',huffmanTestTree.getHufmancodes().get("010"));

    }

    @org.junit.jupiter.api.Test
    void readByteStringUsingHashmap() {
        String expected = "testen";
        String actual = huffmanTestTree.readByteStringUsingHashmap(stringBit,hashMap1);
        assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void readByteStringUsingTree() {
        String expected = "testen";
        String actual = huffmanTestTree.readByteStringUsingTree(stringBit);
        assertEquals(expected,actual);
    }




}