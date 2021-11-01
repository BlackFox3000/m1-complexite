package MiniProjet3;

public class TurringMachine {

    private int currentState;
    private String tape;
    private Head head;
    private Transition transition [][];

    /**
     * L'appel au constructeur de la machine de turing permetterai l'initialisation de cette dérniere avec une nouvelle tete de lecture
     * située à la position initiale souhaitée, une bande de lecture vide, ainsi qu'une table de transition bidimitionnel innitialement vide.
     */
    public TurringMachine(Transition[][] transition){
        this.currentState = Machine.Initial_State;
        this.tape = "";
        head = new Head();
        head.setPosition(Machine.Initial_Position);
        this.transition = transition;

    }

    private char read(){

        //pour les positions négatifs
        while (head.getPosition() < 0){
            tape = Machine.eps + tape;
            head.advance();
        }
        //pour les positions positifs
        while (head.getPosition() >= tape.length()){
            tape =  tape+ Machine.eps;

        }

        return tape.charAt(head.getPosition());
    }



    private void write(char c){
        while (head.getPosition() < 0){
            tape = Machine.eps + tape;
            head.advance();
        }
        while (head.getPosition() >= tape.length()){
            tape =  tape+ Machine.eps;
        }
        tape = tape.substring(0, head.getPosition()) + c
                + tape.substring(head.getPosition()+1,tape.length());

    }



    public void print(){
        System.out.println("    ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
        System.out.println("    Etat actuel : " + currentState);
        System.out.println("    Ruban :");
        System.out.println("       " + tape);
        System.out.print("      ");
        for (int i = 0; i < head.getPosition()+1; i++) {
            System.out.print(" ");
        }
        System.out.println("^ (Tete de lécture : " + head.getPosition() + ")");
        System.out.println("    ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
    }

    public void init(String e){
        currentState = Machine.Initial_State;
        head.setPosition(Machine.Initial_Position);
        tape = e;
    }


    public void chooseDirection(int move){
        switch(move){
            case 1:
                head.advance();
                System.out.println(" Avancer");
                break;
            case -1:
                head.back();
                System.out.println(" Reculer");
                break;
            default:
                System.out.println(" Erreur!!!!!!");
                print();
        }
    }
    public void execution() {

        print();

        while (currentState != Machine.FIN) {
            int i = currentState - 1;
            System.out.println(" Symbole d'entrée : " + read() + ",");
            int j = 0;
            switch (read()) {
                case '0':
                    j = 0;
                    break;
                case '1':
                    j = 1;
                    break;
                case Machine.eps:
                    j = 2;
                    break;
                default:
                    System.out.println("Character inconnu : " + read());
                    print();
            }
            currentState = transition[i][j].getState();
            System.out.println(" état : " + currentState + ",");
            write(transition[i][j].getCharacter());
            System.out.println(" Symbole écrit : " + transition[i][j].getCharacter() + ",");

            chooseDirection(transition[i][j].getMove());
            print();
        }
        System.out.println("Fin du traitement.");
    }








}
