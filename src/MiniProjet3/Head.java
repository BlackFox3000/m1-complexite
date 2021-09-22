package MiniProjet3;

public class Head {
    private int position;


    public Head(){
        position = Machine.Initial_Position;
    }


    public int getPosition(){
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void avancer(){
        position = position++;
    }

    public void reculer(){
        position = position--;
    }

}
