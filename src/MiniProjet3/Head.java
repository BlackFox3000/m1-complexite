package MiniProjet3;

public class Head {
    private int position;

/*
    L'appel au constructeur initialise la position de la tete de lecture à la position initial souhaitée.
 */
    public Head(){
        position = Machine.Initial_Position;
    }


    public int getPosition(){
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void advance(){
        position = position+1;
    }

    public void back(){
        position = position-1;
    }

}
