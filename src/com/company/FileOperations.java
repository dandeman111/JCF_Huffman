package com.company;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileOperations {
    private String roughText;
    private final String[] words;
    private final char[] characters;

    public FileOperations(String fileAdress) {
        try {
            roughText = readTextFile(fileAdress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        words = roughText.toLowerCase().split("\\s+");
        characters = roughText.toCharArray();

        for (int i = 0; i < words.length; i++) {
            //Removes all non letters
            words[i] = words[i].replaceAll("[^\\w]", "");
        }
    }

    private String readTextFile(String adress) throws IOException {
        return new String(Files.readAllBytes(Paths.get(adress)));
    }

    public String[] getWords() {
        return words;
    }
    public char[] getCharacters(){
        return characters;
    }

    public void writeBinaryFile(String filename,byte[] byteArray) throws IOException {
        Files.write(new File(filename).toPath(), byteArray);
    }
    public byte[] readBinaryFile(String fileName) throws IOException {
        return Files.readAllBytes(FileSystems.getDefault().getPath( fileName));
    }
    public void writeHashmap(String filename, HashMap hashMap) throws IOException {
        FileOutputStream fos = new FileOutputStream("hashmap.ser");
        ObjectOutputStream objOut = new ObjectOutputStream(fos);
        objOut.writeObject(hashMap);
    }
    public HashMap readHashmap(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (HashMap) ois.readObject();
    }

    public String readBits(String fileName) throws IOException {
        byte[] savedByte = null;
        Path path = Paths.get(fileName);
        StringBuilder bitString = new StringBuilder();

        savedByte = Files.readAllBytes(path);
        if (savedByte != null) {
            for (byte b : savedByte) {
                StringBuilder bytestring = new StringBuilder();
                bytestring.append(Integer.toBinaryString(b & 0xFF));
                while (bytestring.length() < 7) {
                    bytestring.insert(0, "0");
                }
                bitString.append(bytestring);
            }
        }
        return bitString.toString();
    }

}

