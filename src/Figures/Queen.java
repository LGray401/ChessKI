package Figures;
// import Figures.Figure;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen  extends Figure {

    public Queen(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(90);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 56, // up, down, left, right
                9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49))); // diagonal
    }

    static int[] helperNS = {-8,8};
    static int[] helperNwSw = {-9,7};
    static int[] helperNoSo = {-7,9};
    static int[] leftBorder = {0,8,16,24,32,40,48,56};
    static int[] rightBorder = {7,15,23,31,39,47,55,63};

}