package Figures;

public class EmptyField extends Figure{
    public EmptyField(int position) {
        setPosition(position);
        this.setEmptyField(true);
    }

    @Override
    public int getTypeAsInt() {
        return 100;
    }
}
