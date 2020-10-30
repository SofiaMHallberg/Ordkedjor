import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DirectedUnweightedGraph {
    private SeparateChainingHashST<String, Integer> hashTable;

    public DirectedUnweightedGraph() {
        hashTable=new SeparateChainingHashST();
    }

    /**
     * Method that creates an ArrayList and fills it with strings from a text file
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

        ArrayList<String> words = new ArrayList<String>();

        while (true) {
            String word = null;
            try {
                word = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (word == null) { break; }
            assert word.length() == 5;
            words.add(word);
        }

        return words;
    }

    public void createHashTable(ArrayList<String> words) {

        for (int i=0; i<words.size(); i++) {
            String word=words.get(i);
            hashTable.put(word.substring(0,3), i);                              // plocka bort bokstav nr 4
            hashTable.put((word.substring(0,2)+word.substring(4,4)), i);        // plocka bort bokstav nr 3
            hashTable.put((word.substring(0,1)+word.substring(3,4)), i);        // plocka bort bokstav nr 2
            hashTable.put((word.substring(0,0)+word.substring(2,4)), i);        // plocka bort bokstav nr 1
            hashTable.put(word.substring(1,4), i);                              // plocka bort bokstav nr 0
        }
    }

    public void test(String fileName, Digraph wordGraph, ArrayList<String> words) {
        BufferedReader r =
                null;
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

            bsfTest(wordGraph,s,v);
        }

    }

    /**
     * Method that creates a digraph from an ArrayList with strings
     * @param words an ArrayList with words
     * @return a digraph
     */
    public Digraph createGraph(ArrayList<String> words) {
       Digraph wordGraph=new Digraph(words.size());

       for(int i=0; i<words.size(); i++) {
           for(int j=0; j<words.size(); j++) {
               if(compareWords(words.get(i), words.get(j))) {
                   wordGraph.addEdge(i, j);
               }
           }
       }
       return wordGraph;
    }

    public Digraph createGraphWithHashTable(ArrayList<String> words) {
        Digraph wordGraph=new Digraph(words.size());

        int hashValue;

        for(int i=0; i<words.size(); i++) {
            hashValue=hashTable.get(words.get(i).substring(1,4));
            HashMap<String, Integer> matchingVertices=hashTable.get(words.get(i).substring(1,4));

        }
        return wordGraph;
    }

    /**
     * Method that compares two words and returns true if they fulfills the requirement, otherwise return false
     * @param word1 the first word
     * @param word2 the second word to be compared with word1
     * @return true if the requirement is fulfilled otherwise false
     */
    public boolean compareWords(String word1, String word2) {
        for(int i=1; i<5; i++) {
            if(word2.indexOf(word1.charAt(i))>=0) {
                word2.replace(word1.charAt(i), ' ');
            }
            else
                return false;
        }

        return true;
    }

    /**
     * Creates bfs-object for all possible source nodes that computes the shortest paths to all other vertices
     * @param wordGraph the digraph for which the shortest paths should be computed
     */
    public void shortestPaths(Digraph wordGraph) {
        for(int sourceNode=0; sourceNode<wordGraph.V(); sourceNode++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordGraph, sourceNode);

            for (int goalNode = 0; goalNode < wordGraph.V(); goalNode++) {
                Iterable<Integer> shortestPath=bfs.pathTo(goalNode);
                int pathLength=-1;
                if(shortestPath != null) {
                    for (int edge : shortestPath) {
                        pathLength++;
                    }
                }
                System.out.println(pathLength);
            }
        }
    }

    public void bsfTest(Digraph wordGraph, int s, int v) {

        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordGraph, s);
        Iterable<Integer> shortestPath=bfs.pathTo(v);

        int pathLength=-1;
        if(shortestPath != null) {
            for (int edge : shortestPath) {
                pathLength++;
            }
        }
        System.out.println(pathLength);
    }

    public static void main(String[] args) {
        DirectedUnweightedGraph test=new DirectedUnweightedGraph();
        ArrayList<String> words = test.readWords("files/13Words");
        test.createHashTable(words);
        Digraph digraph=test.createGraph(words);
        //test.shortestPaths(digraph);
        test.test("files/3Words",digraph,words);
    }
}
