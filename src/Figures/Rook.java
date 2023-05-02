package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Figure {

    public Rook (boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(50);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 57)));
    }
}
