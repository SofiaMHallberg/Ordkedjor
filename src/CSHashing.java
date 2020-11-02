import java.util.HashMap;
import java.util.LinkedList;

public class CSHashing {

    private HashMap<String, LinkedList<Integer>> csHashMap;

    public CSHashing() {
        csHashMap = new HashMap<>();
    }

    public void put(String key, int vertex) {
        if (csHashMap.containsKey(key)) {
            csHashMap.get(key).add(vertex);
//            System.out.println("nmbr of Neighbours "+ csHashMap.get(key).size());
        } else {
            LinkedList neighbourList = new LinkedList();
            neighbourList.add(vertex);
            csHashMap.put(key, neighbourList);

        }
    }

    public LinkedList<Integer> get(String key) {
        return csHashMap.get(key);
    }

    public boolean containsKey(String key) {
        return csHashMap.containsKey(key);
    }

}