package MiniProjet3;

public class TurringMachine {

    private int state;
    private String band;
    private Head head;
    private Transition transition [][];

    /**
     * L'appel au constructeur de la machine de turing permetterai l'initialisation de cette dérniere avec une nouvelle tete de lecture
     * située à la position initiale souhaitée, une bande de lecture vide, ainsi qu'une table de transition bidimitionnel innitialement vide.
     *
     *
     */
    public TurringMachine(Transition[][] transition){
        this.state = Machine.Initial_State;
        this.band = "";
        head.setPosition(Machine.Initial_Position);
        this.transition = transition;

    }

    private char read(){
        while (head.getPosition()<0){
            band += Machine.eps;
            head.avancer();
        }
        while (head.getPosition()>=band.length()){
            band += Machine.eps;

        }
        return band.charAt(head.getPosition());
    }



    private void write(){

    }

    public String toString(){

        return null;
    }



}
