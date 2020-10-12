import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Arrays;

public class unweightedEdges {
    public Digraph createDiGraph(ArrayList<String> words, int x) {
        int nbrOfWords=words.size();
        Digraph digraph=new Digraph(nbrOfWords);

        for(int i=0; i<nbrOfWords;i++) {
            for(int j=i+1; j<nbrOfWords; j++) {

            }
        }

        for (int y:digraph.adj(x)) {

        }

        return digraph;
    }

    private boolean edge(String x, String y) {
        char[] xArray=new char[5];
        char[] yArray=new char[5];

        for(int i=0; i<5; i++) {
            xArray[i]=x.charAt(i);
            yArray[i]=y.charAt(i);
        }

        Arrays.sort(yArray);

        for(int i=1; i<5; i++) {
            if(xA) {

            }
        }
    }

    public ArrayList<String> createWords() {
        return null;
    }
//    BufferedReader r =
//            new BufferedReader(new InputStreamReader(new FileInputStream(fnam)));
//    ArrayList<String> words = new ArrayList<String>();
//while (true) {
//        String word = r.readLine();
//        if (word == null) { break; }
//        assert word.length() == 5;  // indatakoll, om man kör med assertions på
//        words.add(word);
//    }
//
//    BufferedReader r =
//            new BufferedReader(new InputStreamReader(new FileInputStream(fnam)));
//while (true) {
//        String line = r.readLine();
//        if (line == null) { break; }
//        assert line.length() == 11; // indatakoll, om man kör med assertions på
//        String start = line.substring(0, 5);
//        String goal = line.substring(6, 11);
//        // ... sök väg från start till goal här
//    }
}
