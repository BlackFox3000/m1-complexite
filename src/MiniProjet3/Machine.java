package MiniProjet3;

import java.util.Scanner;

public class Machine {
    public static final char eps = 'B';
    public static final int Initial_Position = 0;
    public static final int Initial_State = 1;
    public static final int FIN = 0;



    public static void main(String[] args) {

        /*
        Cette machine permet de savoir s'il y'a un nombre paire de 1 ou non s'il s'agit d'une instance positif(pair) on écrit un '1' à la fin du mot dans le ruban
        et donc un '0' dans le cas contraire.
        */


        Transition[][] tab = {
                {new Transition(1, '0', +1), new Transition(2, '1', +1), new Transition(3, eps, +1)},
                {new Transition(2, '0', +1), new Transition(1, '1', +1), new Transition(4, eps, +1)},
                {new Transition(3,'0', -1), new Transition(3, '1', -1), new Transition(FIN, '1', -1)},
                {new Transition(4,'0', -1), new Transition(4, '1', -1), new Transition(FIN, '0', -1)},

        };



        TurringMachine machine = new TurringMachine(tab);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ecrire un nombre en base 2 ");
        machine.init(scanner.nextLine());
        machine.execution();


    }
}
