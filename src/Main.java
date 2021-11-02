import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        map.put(1,new ArrayList<>());
        map.put(3,new ArrayList<>());
        map.put(6,new ArrayList<>());
        map.get(1).add(12);
        map.get(1).add(15);
        map.get(1).add(18);
        ArrayList<Integer> copie = new ArrayList<>();
        copie.add(12);
        copie.add(12);
        copie.add(18);

        System.out.println(copie.equals(map.get(1)));
        System.out.println(map.keySet());
        ArrayList<Integer> test = (ArrayList<Integer>) map.get(1).clone();
        test.add(50);
        for (int key : test) {
            System.out.println(key);
        }for (int key : map.get(1)) {
            System.out.println(key);
        }
    }
}
