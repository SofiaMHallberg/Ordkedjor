import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.io.*;
import java.util.ArrayList;

public class DirectedUnweightedGraph {

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
            assert word.length() == 5;  // indatakoll, om man kör med assertions på
            words.add(word);
        }

        return words;
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
            // ... sök väg från start till goal här

            int s = words.indexOf(start);
            int v = words.indexOf(goal);

            bsfTest(wordGraph,s,v);
        }

    }

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

    public void shortestPaths(Digraph wordGraph, int size) {
        for(int i=0; i<size; i++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(wordGraph, i);

            for (int j = 0; j < size; j++) {
                Iterable<Integer> shortestPath=bfs.pathTo(j);
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
        Digraph digraph=test.createGraph(words);
        //test.shortestPaths(digraph, words.size());
        test.test("files/3Words",digraph,words);
    }
}
