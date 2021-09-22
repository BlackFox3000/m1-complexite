package MiniProjet1;

public class Fibonacci {
    private static int[][] defaultMatrix= {{0,1},{1,1}};
    private static int[][] fibonacci = {{0},{1}};
    static int[][] voidMatrix = {{0,0},{0,0}};
    public int[][] M1=defaultMatrix, M2=defaultMatrix;

    public static void main(String[] args) {
        int n = 10;
        Fibonacci fibonacci = new Fibonacci();

        fibonacci.printMatrix( fibonacci.get(5) );
        //fibonacci.print();
        int[][] a ={{0,1},{1,1}}, b={{1,1},{1,2}};
        //fibonacci.printMatrix(fibonacci.matrix_merge(a,b));
        //fibonacci.printMatrix(fibonacci.matrix_merge(a,b));

        //System.out.println(recurcive(n));
        //System.out.println(iterative(n));
    }

    static int recurcive(int n){
        if(n == 0){
            return 0;
        }
        else if(n == 1){
            return 1;
        }
        else{
            return recurcive(n-1) + recurcive(n-2);
        }
    }

    static int iterative(int n){
        int nbr =0, nbr1=0, nbr2=1;

        if(n == 0){
            nbr= nbr1;
        }
        else if (n ==1){
            nbr = nbr2;
        }
        else {

            for (int i = 2; i <= n; i++) {
                nbr = nbr1 + nbr2;
                nbr1 = nbr2;
                nbr2 = nbr;
            }
        }
        return nbr;
    }

    public int[][] matrix_merge(int[][] m1, int[][] m2) {
        int[][] M = {{0,0},{0,0}};
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<2 ;k++)
                {
                    M[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return M;
    }

    public void printMatrix(int[][] M){
        System.out.println(M[0][0]+" "+M[0][1]);
        System.out.println(M[1][0]+" "+M[1][1]+"\n");
    }

    public int[][] mergeImpaire(int n, int[][] matrix){
        if (n == 1)
            return matrix;
        System.out.println("{");
        int[][] oldMatrix = get(n-1);

        System.out.println("}");
        System.out.println("======================");
        printMatrix(matrix);
        printMatrix(oldMatrix);
        System.out.println("======================");
        return matrix_merge(matrix, oldMatrix);
    }
    public int[][] mergePaire(int[][] matrix){
        return matrix_merge(matrix, matrix);
    }

    private int[][] get(int n) {
        int[][] matrix=defaultMatrix, oldMatrix=defaultMatrix;
        int[][] result = voidMatrix;
        while(n!=0) {
            System.out.println("n:"+n);
            printMatrix(matrix);
            //on récupère f(n+1) par exemple pour fibonacci(3) => matrice(2) => f(n+1)
            if (n % 2 == 0) {
                System.out.println("matrice paire=>");
                matrix = mergePaire(matrix);
                n = n / 2;
            } else {
                System.out.println("matrice impaire=>");
                matrix = mergeImpaire(n,matrix);
                System.out.println("n:"+n);
                printMatrix(matrix);
                n=0;
            }
        }
        System.out.println("result:");
        printMatrix(matrix);
        return matrix;
    }
}
