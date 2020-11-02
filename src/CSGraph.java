import edu.princeton.cs.algs4.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class CSGraph {

    private CSHashing hashTable;
    private ArrayList<String> words;
    private Digraph wordGraph;

    public CSGraph(String fileName) {
        hashTable = new CSHashing();
        words = readWords(fileName);

//        createGraph();                // Minimal lösning

        createHashTable();              // Frivillig bra lösning
        createGraphWithHashTable();     // Frivillig bra lösning

//      shortestPaths();

        testPairs("files/7Pairs");
    }


    /**
     * Method that creates an ArrayList and fills it with strings from a text file
     *
     * @param fileName a file with strings
     * @return an ArrayList with strings
     */
    public ArrayList<String> readWords(String fileName) {
        BufferedReader r = null;

        try {
            r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        words = new ArrayList<String>();

        while (true) {
            String word = null;
            try {
                word = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (word == null) {
                break;
            }
            assert word.length() == 5;
            words.add(word);
        }

        return words;
    }

    public void createHashTable() {
        String[] tempArray = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            char[] word = words.get(i).toCharArray();   // char[] snarf
            Arrays.sort(word);                          // char[] afnrs
            tempArray[i] = toString(word);       // String afnrs

        }

        for (int i = 0; i < words.size(); i++) {
            String word = tempArray[i];
            hashTable.put(word.substring(0, 4), i);                                 // plocka bort bokstav nr 5
            String subString4 = (word.substring(0, 3) + word.substring(4, 5));      // plocka bort bokstav nr 4
            hashTable.put(subString4, i);
            String subString3 = (word.substring(0, 2) + word.substring(3, 5));            // plocka bort bokstav nr 3
            hashTable.put(subString3, i);
            String subString2 = (word.substring(0, 1) + word.substring(2, 5));            // plocka bort bokstav nr 2
            hashTable.put(subString2, i);
            hashTable.put(word.substring(1, 5), i);                                 // plocka bort bokstav nr 1

        }
    }

    public Digraph createGraphWithHashTable() {
        wordGraph = new Digraph(words.size());

        for (int node = 0; node < words.size(); node++) {
            char[] lastFourChars = words.get(node).substring(1, 5).toCharArray();
            Arrays.sort(lastFourChars);
            String subStringedWord = toString(lastFourChars);
            LinkedList<Integer> neighbours = hashTable.get(subStringedWord);
            if (neighbours != null) {
                for (int neighbour : neighbours) {
                    wordGraph.addEdge(node, neighbour);

                }
            }
        }
        return wordGraph;
    }

    public static String toString(char[] a) {
        String string = new String(a);
        return string;
    }

    /**
     * Method that creates a digraph from an ArrayList with strings.
     *
     * @return a digraph
     */
    public Digraph createGraph() { // skicka en huvudnod för att bygga graf.
        wordGraph = new Digraph(words.size());

        for (int v = 0; v < words.size(); v++) { // v-nod = v, w-grannar = j.
            for (int w = 0; w < words.size(); w++) {
                if (compareWords(words.get(v), words.get(w))) {
                    wordGraph.addEdge(w, v); // bygger graf ifrån grannen till huvudnod.
                }
            }
        }
        return wordGraph;
    }

    /**
     * Method that compares two words and returns true if they fulfills the requirement, otherwise return false
     *
     * @param word1 the first word
     * @param word2 the second word to be compared with word1
     * @return true if the requirement is fulfilled otherwise false
     */
    public boolean compareWords(String word1, String word2) {
        for (int i = 1; i < 5; i++) {
            if (word2.indexOf(word1.charAt(i)) >= 0) {
                word2.replace(word1.charAt(i), ' ');
            } else
                return false;
        }

        return true;
    }

    /**
     * Creates bfs-object for all possible source nodes that computes the shortest paths to all other vertices
     */
    public void shortestPaths() {
        for (int sourceNode = 0; sourceNode < wordGraph.V(); sourceNode++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordGraph, sourceNode);

            for (int goalNode = 0; goalNode < wordGraph.V(); goalNode++) {
                Iterable<Integer> shortestPath = bfs.pathTo(goalNode);
                int pathLength = -1;
                if (shortestPath != null) {
                    for (int edge : shortestPath) {
                        pathLength++;
                    }
                }
                if (pathLength != -1) {
                }
                System.out.println(pathLength);
            }
        }
    }

    public void testPairs(String fileName) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            String line = null;
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);

            int s = words.indexOf(start);
            int v = words.indexOf(goal);

            bsfTest(s, v);
        }

    }

    public void bsfTest(int s, int v) {

        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordGraph, s);
        Iterable<Integer> shortestPath = bfs.pathTo(v);

        int pathLength = -1;
        if (shortestPath != null) {
            for (int edge : shortestPath) {
                pathLength++;
            }
        }
        System.out.println(pathLength);
    }

    public static void main(String[] args) {
        new CSGraph("files/13Words");
    }
}
