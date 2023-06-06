package Figures;

import Main.Board;

import java.util.List;

public class EmptyField extends Figure{
    public EmptyField(int position) {
        setPosition(position);
        this.setEmptyField(true);
    }
}
