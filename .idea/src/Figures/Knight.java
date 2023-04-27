package Figures;


import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Figure{

    public Knight(boolean isblack, int position) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves = new ArrayList<>(Arrays.asList(6, 10, 15, 17, 19, -6, -10, -15, -17 - 19));
    }
}
