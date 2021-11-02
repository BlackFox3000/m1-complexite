package MiniProjet2;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public int [][] graph;

    public Graph(int[][] graph) {
        this.graph = graph;
    }

    /**
     * Question 1
     * Vérifie si le sous ensemble donné est une zone dense du graphe
     * complexité : n^2
     * @param sousEnsemble
     * @return boolean;
     */
    public boolean estDense(ArrayList<Integer> sousEnsemble ) {
        for(Integer i : sousEnsemble){
            for (Integer j : sousEnsemble) {
                if (graph[i][j] != 1)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // x A B C D E F G
        // A 1 1 1 1 1 0 0
        // B 1 1 1 1 0 1 1
        // C 1 1 1 1 0 0 0
        // D 1 1 1 1 1 0 0
        // E 1 0 0 1 1 0 0
        // F 0 1 0 0 0 1 0
        // G 0 1 0 0 0 1 0
        int [][] matrix =
                {
                        {1,1,1,1,1,0,0},
                        {1,1,1,1,0,1,1},
                        {1,1,1,1,0,0,0},
                        {1,1,1,1,1,0,0},
                        {1,0,0,1,1,0,0},
                        {0,1,0,0,0,1,0},
                        {0,1,0,0,0,1,0}
                };
        Graph graph = new Graph(matrix);
        int letter = 0;
        ArrayList<Integer> pointToDenseArea = graph.getLinks(letter,new HashMap<>(), false);
        graph.printGraph();

        System.out.println("pointToDenseArea of "+graph.getCharForNumber(letter)+" :");
        for (int i=0; i<pointToDenseArea.size(); i++) {
            System.out.println(graph.getCharForNumber(pointToDenseArea.get(i)));
        }
        System.out.println("============");
        ArrayList<Integer> results = graph.foundDenseGroup(letter);
        for (int result : results)
            System.out.println(graph.getCharForNumber(result));
    }

    /**
     * Retourne un ensemble de points liés au point donnés et stock les informations de voisinnage dans notre hashmap
     * complexité 2n
     * @param point
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getLinks(int point, HashMap<Integer,ArrayList<Integer>> hashMap, boolean stock ){
        ArrayList<Integer> summits = new ArrayList<>();
        for(int i=0; i<graph.length; i++){
                if (graph[point][i] == 1) {
                    summits.add(i);
                    if(stock) {
                        if (!hashMap.containsKey(i)) {
                            hashMap.put(i, new ArrayList<>()); // stock < voisin , point >
                            hashMap.get(i).add(i);
                        }
                        if(! hashMap.get(point).contains(i))
                            hashMap.get(point).add(i);
                    }
                }
        }
        return summits;
    }

    /**
     *Question 2
     */
    public ArrayList<Integer> foundDenseGroup(int point) { // O(n^6)
        HashMap<Integer, ArrayList<Integer>> stock = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(point);

        // On cherche les voisins du point initiale
        ArrayList<Integer> voisins = getLinks(point, stock, true);

        if(voisins.isEmpty())
            return result ;

        //Pour chaque voisin   O( 2n^2 )
        for(int voisin : voisins) { // complexité n
            getLinks(voisin, stock, true); // complexité 2n
        }

        //test Hashmap
        System.out.println(" Création Map : ");
        printStock(stock);

        System.out.println(" Methode de recherche :");
        //O(2n^6)
        while (! zoneEstDense(stock)){  // complexité n^3
            ArrayList<Integer> minus= getMinus(stock); // O(n)
            stock = removeMinus(stock, minus); // O(n^2)
        }
        result.addAll(stock.keySet());
        return result;
        //===================
    }

    /**
     * Debug
     * @param stock
     */
    private void printStock(HashMap<Integer, ArrayList<Integer>> stock) {
        for(int key : stock.keySet()){
            StringBuilder string = new StringBuilder(getCharForNumber(key) + " | ");
            for (int value : stock.get(key)){
                string.append(getCharForNumber(value)).append(" ");
            }
            System.out.println(string);
        }
    }

    private ArrayList<Integer> getMinus(HashMap<Integer, ArrayList<Integer>> stock) { // O(n)
        System.out.println(" •• Search Minus ••");


        int min = -1;
        for(int key : stock.keySet()) { // O(n+2)
            System.out.println("key:"+getCharForNumber(key) + " size: "+ stock.get(key).size());
            if (min == -1)
                min = stock.get(key).size();
            if (min > stock.get(key).size())
                min = stock.get(key).size();
        }


        ArrayList<Integer> minus = new ArrayList<>();
        String stringMinus = " ";
        for (int key : stock.keySet()) // O(n)
            if(min == stock.get(key).size()) {
                stringMinus += getCharForNumber(key) + " ";
                minus.add(key);
            }
        System.out.println(stringMinus);
        return minus;
    }

    private HashMap<Integer, ArrayList<Integer>> removeMinus(HashMap<Integer, ArrayList<Integer>> stock, ArrayList<Integer> minus) {
        System.out.println(" ** Remove Minus **");
        //Pour chaque valeur min O(n^2)
        for(int min : minus){ // O(n)
            System.out.println("min:"+getCharForNumber(min));
            for(int key : stock.keySet()) { // O(n)
                System.out.println("- key:" + getCharForNumber(key));
                System.out.println("contain : "+stock.get(key).contains(min)+ " && "+ (min != key));
                if (stock.get(key).contains(min) && min != key) {
                    System.out.println("Remove>"+getCharForNumber(min));
                    stock.get(key).remove( stock.get(key).lastIndexOf(min));
                }
                printStock(stock);
            }
            if(stock.keySet().contains(min)) {
                stock.remove(min);
                System.out.println("Remove>"+getCharForNumber(min));
            }
        }
        return stock;
    }

    /**
     * Méthode très couteuse ( à améliorer )
     * Complecité nxnxn
     * @param stock
     * @return
     */
    private boolean zoneEstDense(HashMap<Integer, ArrayList<Integer>> stock) {
        System.out.println(" -- Test zoneEstDense --");
        printStock(stock);
        for(int key : stock.keySet()) {
            for (int key2 : stock.keySet()) {
                //On élimine directement la comparaison si les tailles sont différentes
                if (stock.get(key2).size() != stock.get(key).size()) {
                    System.out.println("=> non size =/=");
                    return false;
                }
                for (int num1 : stock.get(key)) {

                    System.out.println("test si: " + getCharForNumber(key2) + " contient " + getCharForNumber(num1));

                    //On cherche ici si les de sous ensembles comportent les même points qu'importe son ordre
                    if (!stock.get(key2).contains(num1)) {
                        System.out.println("=> non contain");
                        return false;
                    }
                }
            }

        }
        System.out.println("=> oui");
        return true;
    }


    public void printGraph(){
        StringBuilder line = new StringBuilder();
        for (int[] ints : graph) {
            for (Integer j : ints) {
                line.append(ints[j]).append(" ");

            }
            System.out.println(line);
            line = new StringBuilder();
        }
    }

    private String getCharForNumber(int i) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        if (i > 25) {
            return null;
        }
        return Character.toString(alphabet[i]);
    }

}
