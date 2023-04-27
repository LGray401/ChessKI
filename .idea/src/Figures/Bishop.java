package Figures;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Figure {

    public Bishop(boolean isblack, int position) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves =new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, - 54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49));
    }
}
