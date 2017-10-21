package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanTree {
    private final TreeMap<Character, Integer> charTreeSorted;
    private final LinkedList<Node> huffmanNodeList;
    private final HashMap hufmancodes;
    private final HashMap reverseHufmancodes;
    private final char[] characters;


    public HashMap getHufmancodes() {
        return hufmancodes;
    }

    public HuffmanTree(char[] Characters) {
        characters = Characters;
        int charCount = Characters.length;
        charTreeSorted = frequency(characters);
        huffmanNodeList = new LinkedList<>();
        hufmancodes = new HashMap<StringBuilder, Character>();
        buildTree();
        reverseHufmancodes = reverseHashmap(hufmancodes);
    }

    private void buildTree() {
        for (Map.Entry<Character, Integer> entry : charTreeSorted.entrySet()) { //zorgt dat alle letters een node hebben
            sortedFrequencyInsert(new Node((entry)));//maakt nieuwe node aan met entry als parameter de functie zet hem op de goede plaats in de lijst
        }

        int maxNodeValue = 0;//#todo kijk naar waar de node count word gezet want er is nog twijfel over hoe
        Node node1;
        Node node2;
        //huffmanLeafNodeList = huffmanNodeList;
        while (huffmanNodeList.size() > 1) { //maxNodeValue != charCount De frequentie van alle nodes opgeteld is gelijk aan het aantal woorden in de tekst.
            node1 = huffmanNodeList.get(0);
            node2 = huffmanNodeList.get(1);
            huffmanNodeList.remove(0);//dit moet 2 keer want de index schuift op
            huffmanNodeList.remove(0);
            Node newNode = new Node(node1, node2);
            sortedFrequencyInsert(newNode);
        }

        hufmancodes.putAll(huffmanNodeList.get(0).toMap());

    }

    private TreeMap<Character, Integer> frequency(char[] characters) { //https://stackoverflow.com/questions/6712587/how-to-count-frequency-of-characters-in-a-string
        HashMap<Character, Integer> unsorted = new HashMap();
        for (Character c : characters) {
            if (unsorted.get(c) == null) {
                unsorted.put(c, 1);
            } else {
                unsorted.put(c, unsorted.get(c) + 1);
            }
        }
        return sortByValue(unsorted);
    }

    private TreeMap<Character, Integer> sortByValue(HashMap<Character, Integer> map) {
        Comparator<Character> comparator = new ValueComparator(map);
        TreeMap<Character, Integer> sortedMap = new TreeMap<Character, Integer>(comparator);
        sortedMap.putAll(map);
        return sortedMap;
    }

    class ValueComparator implements Comparator<Character> {
        final HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        ValueComparator(HashMap<Character, Integer> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(Character s1, Character s2) {
            if (map.get(s1) <= map.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }

    }

    private void sortedFrequencyInsert(Node node) {

        for (int i = 0; i <= huffmanNodeList.size(); i++) {
            if (huffmanNodeList.size() != 0 && node.frequency <= huffmanNodeList.get(i).frequency) { //volgorde van de statements is belangrijk anders index out of bounds
                huffmanNodeList.add(i, node);
                break;
            } else {
                huffmanNodeList.addLast(node);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String x = "Boom: ";
        x += hufmancodes.toString();
        return x;
    }

    public String readByteStringUsingTree(String string) {
        Node currentNode = huffmanNodeList.get(0);
        String out = "";
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '0') {
                currentNode = currentNode.leftNode;
            } else {
                currentNode = currentNode.rightNode;
            }
            if (currentNode.character != null) {
                out += currentNode.character;
                currentNode = huffmanNodeList.get(0);
            }
        }
        return out;
    }

    public String readByteStringUsingHashmap(String string, HashMap<StringBuilder, Character> hashMap) {
        String out = "";
        String charcode = "";
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            charcode += c;
            if (hashMap.containsKey(charcode)) {
                out += hashMap.get(charcode);
                charcode = "";
            }
        }
        return out;
    }

    public Boolean[] stringToBoolArray(String byteCode) throws Exception {
        Boolean[] booleans = new Boolean[byteCode.length()];
        int counter = 0;
        for (char c : byteCode.toCharArray()) {
            if (c == '0') {
                booleans[counter] = false;
                counter++;
            } else if (c == '1') {
                booleans[counter] = true;
                counter++;
            } else {
                throw new Exception("Invalid string exception");
            }
        }
        return booleans;
    }

    public String compressFile() {
        String flatFile = "";
        for (int i = 0; i < characters.length; i++) {
            flatFile += reverseHufmancodes.get(characters[i]);
        }
        return flatFile;
    }

    private HashMap reverseHashmap(HashMap hashMap) {
        Map<StringBuilder, Character> map = new HashMap<>(hashMap);
        Map<Character, StringBuilder> swapped = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        return new HashMap(swapped);
    }

    public char[] fileToByteArray(String bitString) {
        char[] bitChars;
        char[] preBitChars = bitString.toCharArray();
        int bitShortage = (8 - (preBitChars.length % 8));
        bitChars = new char[preBitChars.length + bitShortage];
        System.arraycopy(preBitChars, 0, bitChars, 0, preBitChars.length);

        for (int i = 0; i < bitShortage; i++) {
            bitChars[preBitChars.length + i] = '0';
        }
        return bitChars;
    }

    public byte[] charArraytoByteArray(char[] bitChars) {
        byte[] byteArray = new byte[bitChars.length / 7 + 1];

        StringBuilder byt = new StringBuilder();
        int counter = 0;
        for (char bit : bitChars) {//gaat elk character af
            byt.append(bit); //zet het character in de stringbuilder
            if (byt.length() == 7) { //als er 8 in zitten
                byteArray[counter] = (byte) Integer.parseInt(byt.toString(), 2); //sla ze op als een byte
                byt = new StringBuilder(); //haal de stringbuilder leeg
                counter++; //verhoog de counter
            }
        }
        while (byt.length() < 7) {
            byt.append("0");
        }
        byteArray[counter] = Byte.parseByte(byt.toString(), 2);
        return byteArray;
    }
}