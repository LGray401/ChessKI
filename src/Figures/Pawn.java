package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Figure {

    boolean hasMoved = false;

    public Pawn(boolean isBlack, int position) {

        this.setValue(10);
        this.setBlack(isBlack);
        this.setPosition(position);
        if (isBlack){
            this.setMoveSummandList(new ArrayList<>(Arrays.asList(-7, -8, -9)));
        } else {
            this.setMoveSummandList(new ArrayList<>(Arrays.asList(7, 8, 9)));
        }
    }
}
