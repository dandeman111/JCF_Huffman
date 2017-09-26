package com.company;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.*;

public class HuffmanTree {
    TreeMap<String,Integer> treeMap;
    LinkedList<Node> huffmanNodeTree; //#todo dit moet een gesorteerde lijst zijn
    int wordCountTotal;
    String[] characters;

    public HuffmanTree(String[] Characters) {
        wordCountTotal =characters.length;
        treeMap = frequency(characters);
        huffmanNodeTree = new LinkedList<>();
        characters = Characters;
        buildTree();
    }
    private void buildTree(){
        for(Map.Entry<String,Integer> entry : treeMap.entrySet()) { //zorgt dat alle letters een node hebben
            sortedFrequencyInsert(new Node((entry)),huffmanNodeTree);//maakt nieuwe node aan met entry als parameter de functie zet hem op de goede plaats in de lijst
        }
        int maxNodeValue = 0;//#todo kijk naar waar de node count word gezet want er is nog twijfel over hoe
        Node node1;
        Node node2;
        while(maxNodeValue != wordCountTotal){ //De frequentie van alle nodes opgeteld is gelijk aan het aantal woorden in de tekst.
            node1 = huffmanNodeTree.get(0);
            node2 = huffmanNodeTree.get(1);
            huffmanNodeTree.remove(0);//dit moet 2 keer want de index schuift op
            huffmanNodeTree.remove(0);
            huffmanNodeTree.add(new Node(node1,node2)); //maakt die niewe node in de lijst en heeft de oude als branches

            maxNodeValue = node1.frequency+node2.frequency; //zet de maximale value van de nodes om te kijken of de tree klaar is
        }

    }
    private TreeMap<String,Integer> frequency(String[] words){
        HashMap<String,Integer> unsorted = new HashMap();
        for(String w: words){
            Integer n = unsorted.get(w);
            if(n==null){
                n =1;
            }else{
                n = n+1;
            }
            unsorted.put(w,n);
        }
        return sortByValue(unsorted);
    }
    public  TreeMap<String,Integer> sortByValue(HashMap<String,Integer> map) {
        Comparator<String> comparator = new ValueComparator(map);
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>( comparator);
        sortedMap.putAll(map);
        return sortedMap;
    }

    class ValueComparator implements  Comparator<String>{
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        public ValueComparator(HashMap<String,Integer> map) {
            this.map.putAll(map);
        }
        @Override
        public int compare(String s1,  String s2) {
            if(map.get(s1) <= map.get(s2)){
                return -1;
            }else{
                return 1;
            }
        }

    }


    private void sortedFrequencyInsert(Node node,LinkedList<Node> linkedList){

        for (int i = 0; i <= linkedList.size() ; i++) {
            if(linkedList.size() != 0&&node.frequency <= linkedList.get(i).frequency){ //volgorde van de statements is belangrijk anders index out of bounds
                linkedList.add(i,node);
                break;
            }else{
                linkedList.addLast(node);
                break;
            }
        }
    }

    @Override
    public String toString(){
        String x = "Boom: ";

        for (int i = 0; i < huffmanNodeTree.size() ; i++) {
            x += huffmanNodeTree.get(i).frequency;
        }
        return x;
    }

    public String readByteString(String string){
        Node currentNode = huffmanNodeTree.get(0);
        String out = "";
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if(currentNode.character == null){
                if(c == 0){
                    currentNode = currentNode.leftNode;
                }else {
                    currentNode = currentNode.rightnode;
                }
            }else{
                out += currentNode.character;
            }
        }
        return out;
    }

}
