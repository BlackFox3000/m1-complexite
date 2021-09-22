package MiniProjet3;

public class Transition {
    private int state;
    private char character;
    private int move;


    public Transition(int state, char character, int move){
        this.state =state;
        this.character = character;
        this.move = move;
    }

    public char getCharacter() {
        return character;
    }

    public int getMove() {
        return move;
    }

    public int getState() {
        return state;
    }
}
