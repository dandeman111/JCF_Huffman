package com.company;


import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        FileOperations fileOperations = new FileOperations("text.txt"); //fileoperations wordt voor alle fileoperations gebruikt
        HuffmanTree huffmanTree = new HuffmanTree(fileOperations.getCharacters());//maakt de boom aan met characters uit een textfile

        byte[] input = huffmanTree.charArraytoByteArray(huffmanTree.fileToByteArray(huffmanTree.compressFile())); //Deze
        // reeks methoden vertaalt de gegenereerde code naar een byte[] die ik weg kan schrijven
        try {
            fileOperations.writeHashmap("hashmap.ser",huffmanTree.getHufmancodes()); // schrijft de gegenereerde code's weg naar een bestand
            fileOperations.writeBinaryFile("text.ser",input); //schrijft het textfie weg als bytes
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String bits = ""; //Deze string stop ik de 0 en 1en in die ik ga lezen
        HashMap<StringBuilder, Character> hashMapCodes = new HashMap();
        try {
            bits = fileOperations.readBits("text.ser");//haalt de bytes op en vetaalt ze naar een string met bits
            hashMapCodes = fileOperations.readHashmap("hashmap.ser"); //haalt de hashmap op gebruikt om de codes te lezen

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(huffmanTree.readByteStringUsingHashmap(bits,hashMapCodes));//Deze methode leest de codes dmv de hashmap die ik net heb opgehaald


        //Om te laten zien dat ik kan lezen meot ik in de huffmantree klasse de build tree methode commente zodat de boom niet gebouwd word
    }

}
