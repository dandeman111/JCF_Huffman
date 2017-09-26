package com.company;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileOperations fileOperations = new FileOperations("text.txt");
        HuffmanTree huffmanTree = new HuffmanTree(fileOperations.getCharacters());
        huffmanTree.toString();
    }

}
