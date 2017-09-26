package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperations {
    private String roughText;
    private String[] words;
    private String[] characters;

    public FileOperations(String fileAdress) {
        try {
            roughText = readTextFile(fileAdress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        words = roughText.toLowerCase().split("\\s+");
        characters = roughText.split("");

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
    public String[] getCharacters(){
        return characters;
    }
}

