package Figures;

import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Figure {

    public Pawn(boolean isblack, int position, ArrayList<Integer> possiblemoves) {
        this.value = 10;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves = possiblemoves; // am Anfang 16?
    }
}
