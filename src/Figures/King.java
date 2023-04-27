package Figures;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Figure {

    public King(boolean isblack, int position) {
        this.value = 900;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,-1,8,-8));
    }
}
