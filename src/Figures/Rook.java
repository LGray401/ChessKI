package Figures;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Figure {

    public Rook (boolean isblack, int position) {
        this.value = 50;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,-1,-2,-3,-4,-5,-6,-7,-8,-16,-24,-32,-40,-48,-56, 8, 16, 24, 32, 40, 48, 57));
    }
}
