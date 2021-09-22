package MiniProjet2;

import java.util.ArrayList;

public class Graph {
    private int [][] graph;

    /**
     * Vérifie si le sous ensebmle donné est une zone dense du graphe
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
        Graph graph = new Graph();
        graph.pointToDenseArea(2);
    }

    /**
     *
     * @param point
     * @return
     */
    public ArrayList<Integer> pointToDenseArea(int point){
        ArrayList<Integer> summits = new ArrayList<>();
        for(int i=0; i<graph.length; i++){
                if (graph[i][point] == 1)
                    summits.add(i);

        }
        return summits;
    }

}
